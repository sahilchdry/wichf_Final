package com.sdm.controller;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.model.Appointment;
import com.sdm.model.Payment;

public class PaymentAction extends ActionSupport 
	implements ModelDriven<Payment>, SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Payment payment = new Payment();
	private SessionMap<String,Object> sessionMap;

	public void makePayment(){
		
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap)map; 
		
	}

	@Override
	public Payment getModel() {
		return payment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
