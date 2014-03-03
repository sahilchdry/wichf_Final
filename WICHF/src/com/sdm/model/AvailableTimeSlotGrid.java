package com.sdm.model;


public class AvailableTimeSlotGrid {
	
	private String timeSlot;
	
	private String availableDoctorIds;
	
	private String availableRoomIds;
	
	private int count;
	
	public AvailableTimeSlotGrid( String availableDocs, String availableRooms, int maxSlotsAvailable){
		this.availableDoctorIds = availableDocs;
		this.availableRoomIds = availableRooms;
		this.count = maxSlotsAvailable;
		timeSlot="";
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
	
	
}
