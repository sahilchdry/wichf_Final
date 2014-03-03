package com.sdm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {

	@Id
	@Column(name="payment_id")
	private int paymentId;
	
	@Column(name="card_num")
	private String cardNumber;
	
	@Column(name="type_of_card")
	private String typeOfCard;
	
	
	@Column(name="card_holder_name")
	private String cardHolderName;
	
	@Column(name="expiry_date")
	private String expiryDate;
	
	@OneToOne(targetEntity = Appointment.class, cascade = CascadeType.ALL)
	@JoinColumn(name="appointment_id", referencedColumnName="appointment_id")
	private Appointment appointment;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTypeOfCard() {
		return typeOfCard;
	}

	public void setTypeOfCard(String typeOfCard) {
		this.typeOfCard = typeOfCard;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	

}
