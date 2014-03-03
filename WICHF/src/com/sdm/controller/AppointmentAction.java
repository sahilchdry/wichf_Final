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
import com.sdm.DAO.VisitTypeDAO;
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
	public boolean showApointments = false;
	public String datepick,visitTypeSelect,selectedSlot;
	
	private VisitType visitType = new VisitType();
	public List<Appointment> tempAppointmentList = new ArrayList<Appointment>();
	public List<Appointment> appointmentHistory = new ArrayList<Appointment>();
	public List<VisitType> visitTypeList = new ArrayList<VisitType>();
	private AppointmentDAO appointmentDAO = new AppointmentDAO();
	private UserDAO userDAO = new UserDAO();
	private RoomDAO roomDAO = new RoomDAO();
	private DoctorDAO doctorDAO = new DoctorDAO();
	private VisitTypeDAO visitTypeDAO = new VisitTypeDAO();
	public List<AvailableTimeSlotGrid> availableTmSlotList = new ArrayList<AvailableTimeSlotGrid>();
	@Override
	public Appointment getModel() {
		return appointment;
	}
	
	 public String addToCart()
	   {
		 System.out.println("Finally:"+selectedSlot);
		 System.out.println("*********");
		 //appointment.setAppointmentId();
		 //Finally:9:40_1|2|3|4|5|6|7|_1|2|3|4|5|_5
		 String [] selectedSlotArr = selectedSlot.split("_");
		 appointment.setActive(0);
		 
		 appointment.setBookedDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		 if(sessionMap.get("aptDateSelected")!=null){
			 appointment.setAppointmentDate(getFormattedDate((String)sessionMap.get("aptDateSelected"),0,0));
		 }
		 
		 appointment.setBookedThrough("patient");
		 String[] doctorArray =  selectedSlotArr[1].split("\\|");
		 int doctorId = Integer.parseInt(doctorArray[0]);
		 String[] roomArray =  selectedSlotArr[2].split("\\|");
		 int roomId = Integer.parseInt(roomArray[0]);
		 String[] timeArray = selectedSlotArr[0].split(":");
		 int aptHourOfTheDay = Integer.parseInt(timeArray[0]);
		 int aptMinute = Integer.parseInt(timeArray[1]);
		 
		 //doctor.setDoctorId(doctorId);
		 doctor = doctorDAO.getDoctorById(doctorId);
		 appointment.setDoctor(doctor);
		
		 System.out.println(doctor.getDoctorId());
		 room.setRoomId(roomId);
		 
		 //room = roomDAO.getRoomById(1);
		 appointment.setRoom(room); 
		 
		 if(sessionMap.get("timeslot") != null){
			 int timeslt = (int)  sessionMap.get("timeslot");
					 //Integer.parseInt((String)sessionMap.get("timeslot"));
			 appointment.setTimeSlot(timeslt);
		 }
		 String str = getFormattedDate((String)sessionMap.get("aptDateSelected"),
				 aptHourOfTheDay,aptMinute).toString() + " "+timeArray[0]+":"+timeArray[1];
		 appointment.setStartTimeStr(str);
		 if( sessionMap.get("visitTypeSelect") != null){
			 visitType.setVisitTypeId(Integer.parseInt((String)sessionMap.get("visitTypeSelect")));
		 }else{
			 visitType.setVisitTypeId(1);
		 }
		 appointment.setVisitType(visitTypeDAO.getVisitTypeById(visitType.getVisitTypeId())); //need to put visit type id object
		 
		 if( sessionMap.get("userId") != null){
			 user.setUserId((String)sessionMap.get("userId"));
		 }else{
			 //User has not logged in
			 //user.setUserId("swapnil");
		 }
		 //user.setUserId("swapnil");
		 appointment.setUser(user);
		 appointment.setParentAppointmentId(null);
		 //appointmentDAO.saveAppointment(appointment);
		 
		 if(sessionMap.get("toSaveAptList") != null){
			 tempAppointmentList =  (List<Appointment>) sessionMap.get("toSaveAptList");
		 }
		 
		 tempAppointmentList.add(appointment);
		 sessionMap.put("toSaveAptList", tempAppointmentList);
		
		 System.out.println("Successful");
	      return "success";
	   }
	 
	public String userAppointmentHistory(){
		 String userId;
		//Saved 
		 if(sessionMap.get("toSaveAptList") != null){
			 tempAppointmentList =  (List<Appointment>) sessionMap.get("toSaveAptList");
		 }
		 //Get the user earlier booked history
		 if( sessionMap.get("userId") != null){
			 userId = (String)sessionMap.get("userId");
			 appointmentHistory = appointmentDAO.getAppointments(userId);
		 }
		
		return "success";
	}
	 
	 private java.sql.Date getFormattedDate(String inputDate,int hourOfTheDay, int minute) {
		 String[] selectedDateArr = inputDate.split("/");
		 Calendar cal = new GregorianCalendar(Integer.parseInt(selectedDateArr[2]),
				 					(Integer.parseInt(selectedDateArr[0]) -1),
				 						Integer.parseInt(selectedDateArr[1]),
				 						hourOfTheDay,
				 						minute
				 							);
		return new java.sql.Date(cal.getTimeInMillis());
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
		 tempAppointmentList.clear();
		 tempAppointmentList = appointmentDAO.getAppointments(user);
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

	 public String bookAppointment(){
		 
		 //Set list of available appointment
		 visitTypeList = visitTypeDAO.getVisitTypes();
		 System.out.println("Size"+visitTypeList.size());
		 showApointments = false;
		 return "success";
	 }
	 
	 public String saveSessionAppointments(){
		 String redirection="success";
		 String userId ="";
		 if( sessionMap.get("userId") != null){
			 tempAppointmentList.clear();
			 if(sessionMap.get("toSaveAptList") != null){
				 tempAppointmentList = (List<Appointment>) sessionMap.get("toSaveAptList");
				 

				 for(Appointment appointment : tempAppointmentList){
					 appointmentDAO.saveAppointment(appointment);
				 }
			 }
			 //Get the user earlier booked history
			 if( sessionMap.get("userId") != null){
				 userId = (String)sessionMap.get("userId");
				 appointmentHistory = appointmentDAO.getAppointments(userId);
			 }
			 
			 //Removing after the appointments are saved.
			 if(sessionMap.get("toSaveAptList") != null)
				 sessionMap.remove("toSaveAptList");
			 redirection = "success";
		 }else{
			 //redirection = "loginRequired";
		 }
		 return redirection;
	 }
	 
	 public String getAvailableTimeslots()
	 {
		 int timeslot=20;
		 String selectedDate = "03/03/2014";
		 System.out.println("Form values:"+datepick +"|"+visitTypeSelect+"|"+selectedSlot);
		 if(datepick !=null){
			 sessionMap.put("aptDateSelected", datepick);
			 selectedDate = datepick;
		 }else{
			 selectedDate = (String) sessionMap.get("aptDateSelected");
		 }
		 if(visitTypeSelect !=null){
			 sessionMap.put("visitTypeSelect", visitTypeSelect);
			 timeslot = (visitTypeDAO.getVisitTypeById(Integer.parseInt(visitTypeSelect))).getVisitTime();
			 sessionMap.put("timeslot", timeslot);
		 }else{
			 
			 timeslot = Integer.parseInt((String)sessionMap.get("timeslot"));
		 }
		
		 //03/04/2014
		 //MM/dd/yyyy
		System.out.println("selectedDate:"+selectedDate);
		 String[] selectedDateArr = selectedDate.split("/");
		 Calendar cal = new GregorianCalendar(Integer.parseInt(selectedDateArr[2]),
				 					(Integer.parseInt(selectedDateArr[0]) -1),
				 						Integer.parseInt(selectedDateArr[1])
				 							
				 							);
		
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
		 //List<AvailableTimeSlotGrid> availableList = new ArrayList<AvailableTimeSlotGrid>();
		 manipulateTheGrid(doctorList,roomList,appointments,availableTmSlotList,timeslot);
		 //Set Form fields
		 visitTypeList = visitTypeDAO.getVisitTypes();
		 showApointments = true;
		 //Step 4: Fetch the remaining time slots
		 printToConsole(availableTmSlotList);
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
				currentGrid.setTimeSlot(""+(clinicStartTime +(i /(60/timeslot)))
						+":"+((((i % (60/timeslot))*timeslot) > 0)? ((i % (60/timeslot))*timeslot):"00"));
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
		  tempAppointmentList = appointmentDAO.getAppointments(user);
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
		return tempAppointmentList;
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.tempAppointmentList = appointmentList;
	}

	public List<VisitType> getVisitTypeList() {
		return visitTypeList;
	}

	public void setVisitTypeList(List<VisitType> visitTypeList) {
		this.visitTypeList = visitTypeList;
	}

	public List<AvailableTimeSlotGrid> getAvailableList() {
		return availableTmSlotList;
	}

	public void setAvailableList(List<AvailableTimeSlotGrid> availableList) {
		this.availableTmSlotList = availableList;
	}

	public String getDatepick() {
		return datepick;
	}

	public void setDatepick(String datepick) {
		this.datepick = datepick;
	}

	public List<Appointment> getAppointmentHistory() {
		return appointmentHistory;
	}

	public void setAppointmentHistory(List<Appointment> appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}
	
}
