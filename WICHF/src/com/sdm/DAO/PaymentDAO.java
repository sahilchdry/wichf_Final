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

import com.sdm.model.Payment;
import com.sdm.util.HibernateUtil;



public class PaymentDAO {
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public void savePayment(Payment payment) throws Exception
	   {
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(payment);
			System.out.println("Payment with User is saved..");
		}
		catch(Exception e){
			if (transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		transaction.commit();
	   }

	public Payment getPaymentById(int appointmentId)	
	   {

		Payment payment = null;
		try{
		initializeTransaction();
		payment =  (Payment) session.get(Payment.class, appointmentId);
	      transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	      return payment;
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
	

	
	
	
}
