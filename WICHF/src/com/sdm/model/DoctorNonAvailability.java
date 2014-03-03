package com.sdm.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="doctor_non_availability")
public class DoctorNonAvailability {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="doctor_non_available_date")
	private Date doctorNotAvailableDate;
	
	@ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name="doctor_id", referencedColumnName="doctor_id")
	private Doctor doctor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDoctorNotAvailableDate() {
		return doctorNotAvailableDate;
	}

	public void setDoctorNotAvailableDate(Date doctorNotAvailableDate) {
		this.doctorNotAvailableDate = doctorNotAvailableDate;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	

}
