package com.sdm.model;

public class AvailableSlots {
	
	private Doctor doctor;
	
	private Room room;
	
	private String timeslot;
	
	AvailableSlots() {
		doctor = new Doctor();
		room = new Room();
		timeslot="";
	}
	
	AvailableSlots(String inputStr){
		String[] splitString = inputStr.split("|");
		if(splitString.length == 3){
			doctor.setDoctorId(Integer.parseInt(splitString[0]));
			room.setRoomId(Integer.parseInt(splitString[1]));
			timeslot = splitString[2];
		}
	}
	
	public String getString(){
		return doctor.getDoctorId()+"|"+ room.getRoomId() +"|" + timeslot;
	}
	
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	
	
	
}
