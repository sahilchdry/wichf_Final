package com.sdm.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.sdm.model.Doctor;
import com.sdm.model.Room;
import com.sdm.model.User;
import com.sdm.util.HibernateUtil;

public class RoomDAO {
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
	public void addRoom(Room room)
	   {
		try{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			session.save(room);
			System.out.println("Room with User is saved..");
		}
		catch(Exception e){
			if (transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		transaction.commit();
	   }
	public Room getRoomById(int roomId)	
	   {
	      Room room=null;
	      session = HibernateUtil.getSessionFactory().getCurrentSession();
	      String hql = "FROM ROOM WHERE room_id= :roomId";
	      transaction = session.beginTransaction();
	      try
	      {
	         Query query = session.createQuery(hql);
	         query.setParameter("roomId", roomId);
	         room = (query.list() != null)? (Room)query.list().get(0):null;
	      }
	      catch(Exception e)
	      {
	         e.printStackTrace();
	      }
	      transaction.commit();
	      return room;
	   }
	
	
}
