package com.sdm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.AppointmentDAO;
import com.sdm.DAO.RoomDAO;
import com.sdm.DAO.UserDAO;
import com.sdm.model.Appointment;
import com.sdm.model.Doctor;
import com.sdm.model.Payment;
import com.sdm.model.Room;
import com.sdm.model.User;
import com.sdm.model.VisitType;

public class AppointmentAction extends ActionSupport 
			implements ModelDriven<Appointment>, SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Appointment appointment = new Appointment();
	private User user = new User();
	private Doctor doctor = new Doctor();
	private Room room = new Room();
	private Payment payment = new Payment();
	private SessionMap<String,Object> sessionMap;
	
	private VisitType visitType = new VisitType();
	private List<Appointment> appointmentList = new ArrayList<Appointment>();
	private AppointmentDAO appointmentDAO = new AppointmentDAO();
	private UserDAO userDAO = new UserDAO();
	private RoomDAO roomDAO = new RoomDAO();
	@Override
	public Appointment getModel() {
		return appointment;
	}
	
	 public String makeAppointment()
	   {
		 System.out.println("*********");
		 //appointment.setAppointmentId();
		 appointment.setActive(1);
		 appointment.setBookedDate(null);
		 appointment.setAppointmentDate(null);
		 appointment.setBookedThrough("Nurse");
		 doctor.setDoctorId(1);
		 appointment.setDoctor(doctor);
		
		 System.out.println(doctor.getDoctorId());
		 room.setRoomId(2);
		 
		 //room = roomDAO.getRoomById(1);
		 appointment.setRoom(room); 
		
		 appointment.setTimeSlot(20);
		 visitType.setVisitTypeId(1);
		 appointment.setVisitType(visitType); //need to put visit type id object
		 user.setUserId("asdasdf");
		 appointment.setUser(user);
		 appointment.setParentAppointmentId(null);
		 appointmentDAO.saveAppointment(appointment);
		 System.out.println("Successful");
	      return "success";
	   }
	 
	
	 
	 public String ConfirmInactiveAppointment(){
		 String result = "failure";
		 try{
			 appointmentDAO.cancelOrUpdateAppointment(appointment.getAppointmentId(), 0);
			 sessionMap.put("setActiveAppointmentId", appointment.getAppointmentId());
			 result = "success";
			 
		 } catch (Exception e) {
			result="failure";
		 }
		 return result;
		 
	 }
	 public String getAppointmentsForUser(){
		 String userId = "swapnil";
		 user.setUserId("swapnil");
		 appointmentList = appointmentDAO.getAppointments(user);
		 return "displayAppointments";
	 }
	 
	 public String cancelAppointment()
	 {
		 System.out.println("*********");
		 try {
			appointmentDAO.cancelOrUpdateAppointment(appointment.getAppointmentId(),0);
		} catch (Exception e) {
			
			return "failure";
		}
		 return "success";
		 
	 }
	 
//	 public String updateAppointment(int appointmentId)
//	 {
//		 System.out.println("*********");
//		 appointmentDAO.updateAppointment(appointment, 
//			 appointment.getAppointmentDate(),
//				 appointmentId, appointment.getTimeSlot(), 
//				 appointment.getDoctorId());
//		return "success";
//	 }

	private int id() {
		
		int number = (int) Math.floor(Math.random() * 90000) + 10000;
		return number;
	}

	public String listAppointments()
	   {
		  appointmentList = appointmentDAO.getAppointments(user);
	      return "success";
	   }


	public User getUser() {
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	
	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
	
}
