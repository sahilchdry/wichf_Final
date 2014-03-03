package com.sdm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.AppointmentDAO;
import com.sdm.DAO.DoctorDAO;
import com.sdm.DAO.RoomDAO;
import com.sdm.DAO.UserDAO;
import com.sdm.model.Appointment;
import com.sdm.model.AvailableTimeSlotGrid;
import com.sdm.model.Doctor;
import com.sdm.model.Payment;
import com.sdm.model.Room;
import com.sdm.model.User;
import com.sdm.model.VisitType;

public class AppointmentAction extends ActionSupport 
			implements ModelDriven<Appointment>, SessionAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Appointment appointment = new Appointment();
	private User user = new User();
	private Doctor doctor = new Doctor();
	private Room room = new Room();
	private Payment payment = new Payment();
	private SessionMap<String,Object> sessionMap;
	
	private VisitType visitType = new VisitType();
	private List<Appointment> appointmentList = new ArrayList<Appointment>();
	private AppointmentDAO appointmentDAO = new AppointmentDAO();
	private UserDAO userDAO = new UserDAO();
	private RoomDAO roomDAO = new RoomDAO();
	private DoctorDAO doctorDAO = new DoctorDAO();
	
	@Override
	public Appointment getModel() {
		return appointment;
	}
	
	 public String makeAppointment()
	   {
		 System.out.println("*********");
		 //appointment.setAppointmentId();
		 appointment.setActive(0);
		 appointment.setBookedDate(null);
		 appointment.setAppointmentDate(null);
		 appointment.setBookedThrough("Nurse");
		 doctor.setDoctorId(1);
		 appointment.setDoctor(doctor);
		
		 System.out.println(doctor.getDoctorId());
		 room.setRoomId(2);
		 
		 //room = roomDAO.getRoomById(1);
		 appointment.setRoom(room); 
		 java.util.Calendar calendar = java.util.Calendar.getInstance();
		 appointment.setTimeSlot(20);
		 appointment.setStartTimeStr("2014-03-03 09:40:00");
		 
		 visitType.setVisitTypeId(1);
		 appointment.setVisitType(visitType); //need to put visit type id object
		 
		 user.setUserId("swapnil");
		 appointment.setUser(user);
		 appointment.setParentAppointmentId(null);
		 appointmentDAO.saveAppointment(appointment);
		 System.out.println("Successful");
	      return "success";
	   }
	 
	
	 
	 public String ConfirmInactiveAppointment(){
		 String result = "failure";
		 try{
			 appointmentDAO.cancelOrUpdateAppointment(appointment.getAppointmentId(), 0);
			 sessionMap.put("setActiveAppointmentId", appointment.getAppointmentId());
			 result = "success";
			 
		 } catch (Exception e) {
			result="failure";
		 }
		 return result;
		 
	 }
	 public String getAppointmentsForUser(){

		 user.setUserId("swapnil");
		 appointmentList = appointmentDAO.getAppointments(user);
		 return "displayAppointments";
	 }
	 
	 public String cancelAppointment()
	 {
		 System.out.println("*********");
		 try {
			appointmentDAO.cancelOrUpdateAppointment(appointment.getAppointmentId(),0);
		} catch (Exception e) {
			
			return "failure";
		}
		 return "success";
		 
	 }
	 
//	 public String updateAppointment(int appointmentId)
//	 {
//		 System.out.println("*********");
//		 appointmentDAO.updateAppointment(appointment, 
//			 appointment.getAppointmentDate(),
//				 appointmentId, appointment.getTimeSlot(), 
//				 appointment.getDoctorId());
//		return "success";
//	 }

	 public String getAvailableTimeslots()
	 {
		 String selectedDate = "2014-03-03";
		 String[] selectedDateArr = selectedDate.split("-");
		 Calendar cal = new GregorianCalendar(Integer.parseInt(selectedDateArr[0]),
				 							(Integer.parseInt(selectedDateArr[1]) -1),
				 							Integer.parseInt(selectedDateArr[2]));
		 int timeslot = 60;
		 int noDocForDay = 0, noRoomForDay=0;
		 //Step 1: Get the available doctors
		 List<Doctor> doctorList = doctorDAO.getAvailableDoctors(selectedDate);
		 noDocForDay = doctorList.size();
		 System.out.println(doctorList.size());
		 //Step 2: Get the available rooms
		 List<Room> roomList = roomDAO.getAvailableRooms(selectedDate);
		 noRoomForDay = roomList.size();
		 System.out.println(roomList.size());
		 //Will give me how many appointments I can schedule at that day
		 
		 //Step 3: Get the booked time slots
		 System.out.println(cal.getTime());
		 List<Appointment> appointments = appointmentDAO.getAppointmentsForTimeSlot(new Date(cal.getTimeInMillis()));
		 System.out.println(appointments.size());
		 List<AvailableTimeSlotGrid> availableList = new ArrayList<AvailableTimeSlotGrid>();
		 manipulateTheGrid(doctorList,roomList,appointments,availableList,timeslot);
		 //Step 4: Fetch the remaining time slots
		 printToConsole(availableList);
		 return "success";
	 }
	 
	private void printToConsole(List<AvailableTimeSlotGrid> availableList) {
		for(AvailableTimeSlotGrid avTime : availableList){
			System.out.println(""+avTime.getTimeSlot()+"	"+avTime.getAvailableDoctorIds()
					+"	"+avTime.getAvailableRoomIds()+"	"+avTime.getCount());
		}
		
	}

	private void manipulateTheGrid(
			List<Doctor> doctorList, List<Room> roomList,
			List<Appointment> appointments, List<AvailableTimeSlotGrid> availableList, int timeslot) {
			AvailableTimeSlotGrid currentGrid = null;
			String availableDocs = getAvailableDocsStr(doctorList);
			String availableRooms = getAvailableRoomsStr(roomList);
			StringBuffer tempAvDocs = new StringBuffer();
			StringBuffer tempAvRooms = new StringBuffer();
			String appStartTime;
			int clinicStartTime = 8;
			int maxSlotsAvailable = (roomList.size() < doctorList.size())? roomList.size():doctorList.size(); 
			int count = 9*60/timeslot;
			int index;
			Calendar cal = java.util.Calendar.getInstance();
			//Create the currentGrid Object
			for(int i=0;i< count;++i){
				currentGrid = new AvailableTimeSlotGrid(availableDocs,availableRooms,maxSlotsAvailable);
				currentGrid.setTimeSlot(""+(clinicStartTime +(i /(60/timeslot)))+":"+((i % (60/timeslot))*timeslot));
				System.out.println("Time Set to:"+(clinicStartTime +(i /(60/timeslot)))+":"+((i % (60/timeslot))*timeslot));
				availableList.add(currentGrid);
			}
			//Now loop through all the appointments and reduce the time from available time slots
			for(Appointment appointment:appointments){
					index= 0;
					currentGrid = null;
					//Assign the values to Calendar instance as per Appointment start time
					//cal = java.util.Calendar.getInstance();
					cal.setTime(appointment.getStartTime());
					
					System.out.println(cal.getTime() +" and Hour of the day:"+cal.get(11)+" and Minute:"+cal.get(12));
					//System.out.println("Current Appointment Start time is:"+cal.getTime());

					tempAvDocs.delete(0, tempAvDocs.length());
					tempAvRooms.delete(0, tempAvRooms.length());
					//Get the time slot and compare it with currentGrid object
					index = ((cal.get(11) - 8)*60 + cal.get(12)) / timeslot;
					
					if(index < availableList.size() && index > 0){
						int bookedTmSlotcounter = 0;
						int currAptVisitTime = appointment.getVisitType().getVisitTime();
						if(currAptVisitTime == timeslot){
							bookedTmSlotcounter = 1; //No of loops to execute next
						}else{
							
							bookedTmSlotcounter = (currAptVisitTime > timeslot)?appointment.getVisitType().getVisitTime() / timeslot:1;
						}
						while(bookedTmSlotcounter > 0){
							currentGrid = availableList.get(index + (bookedTmSlotcounter -1));
							//Clear the buffers
							tempAvDocs.delete(0, tempAvDocs.length());
							tempAvRooms.delete(0, tempAvRooms.length());
							
							tempAvDocs.append(currentGrid.getAvailableDoctorIds()
									.replaceAll(""+appointment.getDoctor().getDoctorId()+"\\|", ""));
							tempAvRooms.append(currentGrid.getAvailableRoomIds()
									.replaceAll(""+appointment.getRoom().getRoomId()+"\\|", ""));
							//Update the entries in Current Grid
							currentGrid.setAvailableDoctorIds(tempAvDocs.toString());
							currentGrid.setAvailableRoomIds(tempAvRooms.toString());
							currentGrid.setCount(currentGrid.getCount() - 1);
							
							//Set back the object in list at index: index
							availableList.set(index + (bookedTmSlotcounter -1), currentGrid);
							--bookedTmSlotcounter;
						}
					}else{
						System.out.println("Appointment is scheduled for after Office hours.");
					}
				}
		
	}
	
	private Date getDateFromString(String strDate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date startDate = new Date(0, 0, 0);
	    try {
	        startDate = (Date) df.parse(strDate);
	       // String newDateString = df.format(startDate);
	       // System.out.println(newDateString);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return startDate;
	}

	private String getAvailableDocsStr(List<Doctor> doctorList) {
		StringBuffer doctors =new StringBuffer();
		for(Doctor doctor:doctorList){
			doctors.append(doctor.getDoctorId())
				.append("|");
		}
			
		return doctors.toString();
	}
	
	private String getAvailableRoomsStr(List<Room> roomList) {
		StringBuffer rooms =new StringBuffer();
		for(Room room:roomList){
			rooms.append(room.getRoomId())
				.append("|");
		}
			
		return rooms.toString();
	}
	
	

	public String listAppointments()
	   {
		  appointmentList = appointmentDAO.getAppointments(user);
	      return "success";
	   }


	public User getUser() {
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	
	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
	
}
