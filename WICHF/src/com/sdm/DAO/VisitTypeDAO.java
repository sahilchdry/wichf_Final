package com.sdm.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.sdm.model.Patient;
import com.sdm.model.VisitType;
import com.sdm.util.HibernateUtil;


public class VisitTypeDAO {
		@SessionTarget
		Session session;
		
		@TransactionTarget
		Transaction transaction;
		
		@SuppressWarnings("unchecked")
		public List<VisitType> getVisitTypes()
		   {
		      List<VisitType> visitTypes = new ArrayList<VisitType>();
		      initializeTransaction();
		      try
		      {
		    	  visitTypes = session.createQuery("from VisitType").list();
		      }
		      catch(Exception e)
		      {
		         e.printStackTrace();
		      }
		      transaction.commit();
		      return visitTypes;
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
		
		public VisitType getVisitTypeById(int visitTypeId)	
		   {

			VisitType visitType = null;
			try{
			initializeTransaction();
		      visitType =  (VisitType) session.get(VisitType.class, visitTypeId);
		      transaction.commit();
			}catch(Exception e){
				e.printStackTrace();
			}
			  return visitType;
		   }

		
		
}
