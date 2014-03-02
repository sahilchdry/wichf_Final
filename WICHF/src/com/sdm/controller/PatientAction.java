package com.sdm.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.PatientDAO;
import com.sdm.DAO.UserDAO;
import com.sdm.model.Patient;

public class PatientAction extends ActionSupport implements ModelDriven<Patient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Patient patient = new Patient();
	UserDAO userDAO = new UserDAO();
	PatientDAO patientDAO = new PatientDAO();

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

//	public String listPatients() {
//		patients = patientDAO.getPatients();
//		return "success";
//	}

	
}
