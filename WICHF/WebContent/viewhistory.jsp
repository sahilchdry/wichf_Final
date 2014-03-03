<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="header.jsp" %>
<html>
<title>History</title>

<body>
<center><h3>History of Appointments</h3></center>
	
	
	<h5>Saved Appointments</h5>
	<table border="3" align="center">
	
		<tr> 
		<th> Appointment Id </th>
		<th> Appointment Date & Time</th>
		<th> Visit Type </th>
		<th> Doctor Name</th>
		</tr>	
			
	
		<tr> 
		<s:iterator value="tempAppointmentList">
			<td><s:property value="appointmentId"/><br></td>
			<td><s:property value="startTimeStr"/><br></td>
			<td><s:property value="visitType.visitType"/><br></td>
			<td><s:property value="doctor.doctorName"/><br></td>
		</s:iterator>
		</tr> 
	</table>
	
	
	<table border="3" align="center">
		<tr> 
		<th> Appointment Id </th>
		<th> Appointment Date & Time</th>
		<th> Visit Type </th>
		<th> Doctor Name</th>
		</tr>	
			
		<s:iterator value="appointmentHistory">
		<tr>
			<td><s:property value="appointmentId"/><br></td>
			<td><s:property value="startTimeStr"/><br></td>
			<td><s:property value="visitType.visitType"/><br></td>
			<td><s:property value="doctor.doctorName"/><br></td>
		</tr>
		</s:iterator>
		 
	</table>
	
	
</body>
</html>