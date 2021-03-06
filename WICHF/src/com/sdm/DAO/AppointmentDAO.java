package com.sdm.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.sdm.model.Appointment;
import com.sdm.model.User;
import com.sdm.util.HibernateUtil;


public class AppointmentDAO {
		@SessionTarget
		Session session;
		
		@TransactionTarget
		Transaction transaction;
		
//		@SuppressWarnings("unchecked")
//		public List<User> getUsers()	
//		   {
//		      List<User> users = new ArrayList<User>();
//		      session = HibernateUtil.getSessionFactory().getCurrentSession();
//		      try
//		      {
//		         users = session.createQuery("from User").list();
//		      }
//		      catch(Exception e)
//		      {
//		         e.printStackTrace();
//		      }
//		      return users;
//		   }
		
//		public User getUserById(String userId)	
//		   {
//		      User user=null;
//		      session = HibernateUtil.getSessionFactory().getCurrentSession();
//		      String hql = "FROM USER WHERE USER_ID= :userId";
//		      try
//		      {
//		         Query query = session.createQuery(hql);
//		         query.setParameter("userId", userId);
//		         user = (query.list() != null)? (User)query.list().get(0):null;
//		      }
//		      catch(Exception e)
//		      {
//		         e.printStackTrace();
//		      }
//		      return user;
//		   }
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
		public Appointment saveAppointment(Appointment appointment)
		   {
			initializeTransaction();
//			DetachedCriteria c = DetachedCriteria.forClass(Appointment.class);
//			c.add(Restrictions.eq(String.valueOf(appointment.getDoctor().getDoctorId()), 45));
//			c.getExecutableCriteria(session).;
			// System.out.println("Appointment Room Id:"+appointment.getRoom().getRoomId());
			 try{
				 session.save(appointment);
				// System.out.println("Saved with id:"+appointment.getAppointmentId());
				transaction.commit();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
			return appointment;
		   }
		
		public int cancelOrUpdateAppointment(int appointmentId, int isActive) throws Exception
		   {	
			initializeTransaction();
			String hql;
			int result =0;
			hql = "Update Appointment SET active = :active" +
					" WHERE appointment_id = :appointmentId";
			
				Query query = session.createQuery(hql);
				query.setParameter("active", isActive);
			//	query.setParameter("booked_through", "test" );
		    	query.setParameter("appointmentId", appointmentId);
		    	result = query.executeUpdate();
			transaction.commit();
			return result;
		   }
		
		public void updateAppointment(Appointment appointment){
			initializeTransaction();
			session.merge(appointment);
			
		}
		
		public Appointment checkAppointment(Appointment appointment)
		   {
			initializeTransaction();
			session.save(appointment);
			return appointment;
		   }
		
		public List<Appointment> getAppointments(User user) {
			//List obj
			//Get all active appointments
			List<Appointment> appointmentList = new ArrayList<Appointment>();
			initializeTransaction();
			String hql;
			hql = "FROM Appointment WHERE active = :active and user_id = :userId";
			try
			{
				Query query = session.createQuery(hql);
				query.setParameter("active", true);
		    	  query.setParameter("userId", user.getUserId());
		    	  appointmentList = (query.list()!=null)?(List<Appointment>) query.list() :null;
		    	  
		    	  //appointmentList = (List<Appointment>) session.createQuery("from Appointment where user_id =").setParameter("", arg1)
		    	  transaction.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return appointmentList;
		}
		
		
		public List<Appointment> getAppointments(String userId) {
			//List obj
			//Get all active appointments
			List<Appointment> appointmentList = new ArrayList<Appointment>();
			initializeTransaction();
			String hql;
			hql = "FROM Appointment WHERE active = :active and user_id = :userId";
			try
			{
				Query query = session.createQuery(hql);
				query.setParameter("active", 0);
		    	  query.setParameter("userId",userId);
		    	  appointmentList = (query.list()!=null)?(List<Appointment>) query.list() :null;
		    	  
		    	  //appointmentList = (List<Appointment>) session.createQuery("from Appointment where user_id =").setParameter("", arg1)
		    	  transaction.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return appointmentList;
		}
		
		public List<Appointment> getAppointmentsForTimeSlot(java.util.Date date) {
			//List obj
			//Get all active appointments
			List<Appointment> appointmentList = new ArrayList<Appointment>();
			initializeTransaction();
			String hql;
			hql = "FROM Appointment WHERE active = :active and appointmentDate = :aptDate";
			try
			{
				Query query = session.createQuery(hql);
				query.setParameter("active", 0);
		    	query.setParameter("aptDate", date);
		    	appointmentList = (query.list()!=null)?(List<Appointment>) query.list() :null;
		    	transaction.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return appointmentList;
		}
		
	
		
		
}
