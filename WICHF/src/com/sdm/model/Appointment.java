package com.sdm.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	@Column(name="appointment_id")
	private int appointmentId;
	
	@Column(name="time_slot")
	private int timeSlot;
	
	@Column(name="booked_through")
	private String bookedThrough;
	
	@Column(name="active")
	private int active;
	
	@Column(name="appointment_date")
	private Date appointmentDate;
	
	@Column(name="booked_date")
	private Date bookedDate;
	
	@ManyToOne(targetEntity = Doctor.class)
	@JoinColumn(name="doctor_id", referencedColumnName="doctor_id")
	private Doctor doctor;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private User user;
	
	@ManyToOne(targetEntity = Room.class)
	@JoinColumn(name="room_id", referencedColumnName="room_id" )
	private Room room;
	
	@ManyToOne(targetEntity = Appointment.class)
	@JoinColumn(name="parent_appointment_id", referencedColumnName="appointment_id")
	private Appointment parentAppointmentId;
	
	@Column(name="start_time")
	private String startTimeStr;
	
	@Transient
	private java.util.Date startTime;
	
	@ManyToOne(targetEntity = VisitType.class)
    @JoinColumn(name="visit_type_id", referencedColumnName="visit_type_id")
	private VisitType visitType;

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getBookedThrough() {
		return bookedThrough;
	}

	public void setBookedThrough(String bookedThrough) {
		this.bookedThrough = bookedThrough;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}



	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Appointment getParentAppointmentId() {
		return parentAppointmentId;
	}

	public void setParentAppointmentId(Appointment parentAppointmentId) {
		this.parentAppointmentId = parentAppointmentId;
	}

	public java.util.Date getStartTime() {
		Calendar cal = new GregorianCalendar(Integer.parseInt(startTimeStr.substring(0, 4)),
				 Integer.parseInt(startTimeStr.substring(5, 7)),
				 Integer.parseInt(startTimeStr.substring(8, 10)),
				 Integer.parseInt(startTimeStr.substring(11, 13)),
				 Integer.parseInt(startTimeStr.substring(14, 16)),
				 Integer.parseInt(startTimeStr.substring(17, 19)));
		this.startTime = cal.getTime();
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStartTimeStr() {
		System.out.println(startTimeStr);

		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;

	}
	
	


}
