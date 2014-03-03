package com.sdm.DAO;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.sdm.model.Appointment;
import com.sdm.model.Doctor;
import com.sdm.util.HibernateUtil;

public class DoctorDAO {
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public void addDoctor(Doctor doctor)
	   {
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(doctor);
			System.out.println("Doctor with User is saved..");
		}
		catch(Exception e){
			if (transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		transaction.commit();
	   }

	public List<Doctor> getAvailableDoctors(String selectedDate) {
	
		List<Doctor> doctorList = new ArrayList<Doctor>();
		String hql;
		hql = "FROM Doctor " +
				" WHERE doctorId NOT IN ("+
				" SELECT doctor.doctorId FROM DoctorNonAvailability " +
				" WHERE doctor_non_available_date = :dateToCheck " +
				" )" 
				//+" AND status= :status"
				;
				try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			query.setParameter("dateToCheck", selectedDate);
			//query.setParameter(":status", "Active");
	    	doctorList = (query.list()!=null)?(List<Doctor>) query.list() :null;
			
		}
		catch(Exception e){
			if (transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		transaction.commit();
		return doctorList;
	}
	
	
	private Date getStringToDate(String strDate){
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
	    Date startDate = new Date(0, 0, 0);
	    try {
	        startDate = (Date) df.parse(strDate);
	       // String newDateString = df.format(startDate);
	       // System.out.println(newDateString);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return startDate;
	}
	
	
}
