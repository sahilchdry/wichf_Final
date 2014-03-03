package com.sdm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.sdm.model.Patient;
import com.sdm.model.User;
import com.sdm.util.HibernateUtil;


public class PatientDAO {
		@SessionTarget
		Session session;
		
		@TransactionTarget
		Transaction transaction;
		
		@SuppressWarnings("unchecked")
		public List<Patient> getPatients()
		   {
		      List<Patient> patients = new ArrayList<Patient>();
		      session = HibernateUtil.getSessionFactory().getCurrentSession();
		      try
		      {
		    	  patients = session.createQuery("from Patient").list();
		      }
		      catch(Exception e)
		      {
		         e.printStackTrace();
		      }
		      return patients;
		   }
		public void initializeTransaction()
		{
			try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
		}
		catch(Exception e){
			if (transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
		public Patient addPatient(Patient patient)
		   {
			initializeTransaction();
				session.save(patient);
			
			transaction.commit();
			return patient;
		   }
		
		public Patient getPatientById(String patientId)	
		   {

			Patient patient = null;
		      session = HibernateUtil.getSessionFactory().openSession();
		      patient =  (Patient) session.get(Patient.class, patientId);
	         
		      return patient;
		   }

		public Patient updatePatientInfo(Patient patient,String userId, int phone) {
			
			initializeTransaction();
			String hql;
			int result =0;
			hql = "UPDATE Patient SET phone_no = :phone" +
					" WHERE user_id = :userId";
			
				Query query = session.createQuery(hql);
				query.setParameter("phone", phone);
				query.setParameter("userId", userId);
				result = query.executeUpdate();
			
				transaction.commit();
				
				return patient;
			
		}
		
		
}
