package com.sdm.controller;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.AppointmentDAO;
import com.sdm.DAO.UserDAO;
import com.sdm.model.Appointment;
import com.sdm.model.Doctor;
import com.sdm.model.Room;
import com.sdm.model.User;

public class AppointmentAction extends ActionSupport 
			implements ModelDriven<Appointment>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Appointment appointment = new Appointment();
	private User user = new User();
	private Doctor doctor = new Doctor();
	private Room room = new Room();
	private List<Appointment> appointmentList = new ArrayList<Appointment>();
	private AppointmentDAO appointmentDAO = new AppointmentDAO();
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public Appointment getModel() {
		return appointment;
	}
	
	 public String makeAppointment()
	   {
		 System.out.println("*********");
		 appointment.setAppointmentId(0);
		 appointment.setActive(true);
		 appointment.setBookedDate(null);
		 appointment.setAppointmentDate(null);
		 appointment.setBookedThrough("User Id");
		 doctor.setDoctorId(1);
		 appointment.setDoctor(doctor);
		 System.out.println(doctor.getDoctorId());
		// appointment.setRoom();
		 appointment.setTimeSlot(20);
		 appointment.setVisitTypeId(1); //need to put visit type id object
		 appointment.setUser(user);

		 appointmentDAO.saveAppointment(appointment);
	      return "success";
	   }
	 
	 public String cancelAppointment(int appointmentId)
	 {
		 System.out.println("*********");
		 appointmentDAO.cancelAppointment(appointmentId);
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

	

}
