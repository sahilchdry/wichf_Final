package com.sdm.model;


public class AvailableTimeSlotGrid {
	
	private String timeSlot;
	
	private String availableDoctorIds;
	
	private String availableRoomIds;
	
	private int count;
	
	private String slotValue;
	
	public AvailableTimeSlotGrid( String availableDocs, String availableRooms, int maxSlotsAvailable){
		this.availableDoctorIds = availableDocs;
		this.availableRoomIds = availableRooms;
		this.count = maxSlotsAvailable;
		timeSlot="";
		slotValue =timeSlot +"|"+availableDoctorIds+"|"+availableRoomIds+"|"+count;
	}

	
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getAvailableDoctorIds() {
		return availableDoctorIds;
	}

	public void setAvailableDoctorIds(String availableDoctorIds) {
		this.availableDoctorIds = availableDoctorIds;
	}

	public String getAvailableRoomIds() {
		return availableRoomIds;
	}

	public void setAvailableRoomIds(String availableRoomIds) {
		this.availableRoomIds = availableRoomIds;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public String getSlotValue() {
		slotValue =timeSlot +"_"+availableDoctorIds+"_"+availableRoomIds+"_"+count;
		return slotValue;
	}


	public void setSlotValue(String slotValue) {
		this.slotValue = slotValue;
	}

	
	
}
