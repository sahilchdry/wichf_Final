package com.sdm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.PaymentDAO;
import com.sdm.model.Appointment;
import com.sdm.model.Payment;

public class PaymentAction extends ActionSupport 
	implements ModelDriven<Payment>, SessionAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Payment payment = new Payment();
	AppointmentAction aptAction = new AppointmentAction(); 
			
	private SessionMap<String,Object> sessionMap;
	PaymentDAO paymentDAO = new PaymentDAO();

	public void makePayment(){
		
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap)map; 
		
	}
	
	public String doPayment(){
		String result = "failure";
		Appointment appointment = null;
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		// if(session==null || session.getAttribute("userId")==null){  
		try{
			//aptAction.saveSessionAppointments();
//			
//			List<Appointment> tempAppointmentList = (List<Appointment>) sessionMap.get("toSaveAptList");
//			
//			if(tempAppointmentList != null){
//				appointment = tempAppointmentList.get(0);
//			}
			System.out.println(" Payment:"+payment.getCardHolderName() +" & "+payment.getCardNumber());
			
			//Appointment appointment = new Appointment();
			//appointment.setAppointmentId(1);
			if(sessionMap.get("savedAptId") != null){
				Appointment apt = (Appointment)sessionMap.get("savedAptId") ;
				System.out.println("Payment for appointment :"+apt.getAppointmentId());
				payment.setAppointment(apt);
			}else{
				appointment = new Appointment();
				appointment.setAppointmentId(1);
			}
		
			paymentDAO.savePayment(payment);
			result = "success";
		}catch(Exception exception){
			//DO Nothing
		}
			
		
		return result;
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
