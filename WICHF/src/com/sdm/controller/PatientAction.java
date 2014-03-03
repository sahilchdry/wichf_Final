package com.sdm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.PatientDAO;
import com.sdm.DAO.UserDAO;
import com.sdm.model.Patient;
import com.sdm.model.User;

public class PatientAction extends ActionSupport implements ModelDriven<Patient>,SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Patient patient = new Patient();
	User user = new User();

	UserDAO userDAO = new UserDAO();
	PatientDAO patientDAO = new PatientDAO();
	
	private SessionMap<String,Object> sessionMap;

	@Override
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
	
	@Override
	public Patient getModel() {
		return patient;
	}

	
	public String addPatient() {
		String result="failure";
		
		Date date = patient.getBirthDate();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		Date sysDate = new Date();
		Calendar sysCalendar = new GregorianCalendar();
		sysCalendar.setTime(sysDate);
		int sysYear = sysCalendar.get(Calendar.YEAR);
		
		if((sysYear-year)>=18){
			patient.getUser().setAccessLevel("patient");
			userDAO.addUser(patient.getUser());
			patient = patientDAO.addPatient(patient);
			System.out.println("Patient is saved with id:"+patient.getPatientId());
			result = "success";
		}
		
		return result;
	}
	   


	public String updateAccount()
	{
		String result = "failure";
	HttpSession session=ServletActionContext.getRequest().getSession(true);
	String userId = (String)sessionMap.get("userId");
	
	//int phone = (Integer) sessionMap.get("phoneNumber");
	System.out.println("-------->>>>>>>>>"+userId);
	
	System.out.println("phone number"+patient.getPhoneNumber());
		
	patient = patientDAO.updatePatientInfo(patient,userId,patient.getPhoneNumber());	
		
		result = "success";
	
	return result;
	}

//	public String listPatients() {
//		patients = patientDAO.getPatients();
//		return "success";
//	}

	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	public Patient getPatient() {
		return patient;
	}
	
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
