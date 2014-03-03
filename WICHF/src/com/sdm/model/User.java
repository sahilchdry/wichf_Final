package com.sdm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="access_level")
	private String accessLevel;
	
	@Transient
	private List<Appointment> userAppointments = new ArrayList<Appointment>();
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public List<Appointment> getUserAppointments() {
		return userAppointments;
	}

	public void setUserAppointments(List<Appointment> userAppointments) {
		this.userAppointments = userAppointments;
	}
	
}
