package com.sdm.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.model.User;
import com.sdm.util.HibernateUtil;


public class UserDAO {
	
	HttpServletRequest request;
	
		@SessionTarget
		Session session;
		
		@TransactionTarget
		Transaction transaction;
		
		Map m;
		@SuppressWarnings("unchecked")
		public List<User> getUsers()	
		   {
		      List<User> users = new ArrayList<User>();
		      session = HibernateUtil.getSessionFactory().getCurrentSession();
		      transaction = session.beginTransaction();
		      try
		      {
		         users = session.createQuery("from User").list();
		      }
		      catch(Exception e)
		      {
		         e.printStackTrace();
		      }
		      transaction.commit();
		      return users;
		   }
		
		public User getUserById(String userId)	
		   {

		      User user=null;
		      session = HibernateUtil.getSessionFactory().openSession();
	          user =  (User) session.get(User.class, userId);
	         
		      return user;
		   }
		
		public User addUser(User user)
		   {
			try{
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				transaction = session.beginTransaction();
				session.save(user);
			}
			catch(Exception e){
				if (transaction!=null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
			transaction.commit();
			return user;
		   }
		
		

		public void getSessionVars() {
			HttpSession session=ServletActionContext.getRequest().getSession(false);  
			 if(session==null || session.getAttribute("userId")==null){  
			System.out.println("Null m");}
			else{
			System.out.println("User Id: "+session.getAttribute("userId"));
			}
		}

		
		
		
}
