<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

<title>User Cart</title>

</head>
<body>
<center><h2>User Cart</h2></center>

 	<form id="placeholder" method="post" action="saveSessionAppointments">
	<TABLE align="center" border="2">
	
		<TR>
		    <Th>Appointment Type</Th>
		    <Th>Appointment Date and Time</Th>
		    <Th>Doctor Name</Th>
		   
		  </TR>
		  
		  		   	<s:iterator value="appointmentList">
		  		   	<tr>
  						<td><s:property value="visitType.visitType"/></td>
		 				<td><s:property value="startTimeStr"/></td>
		 				<td><s:property value="doctor.doctorName"/></td>
		 				</tr>
					</s:iterator>
		    	
		  <tr>
		  	
	  		
	  </tr>
	  
	  <tr>
	  	<td>
	  	<input type="button" onclick="location.href('/WICHF/saveSessionAppointments');" value="Save Appointment">
		 
		  
		  </td>
		  <td>
		  <input type="button" onclick="location.href('/WICHF/payment.jsp');" value="Confirm & Pay">
		  
		  
		  </td>
	  </tr>
	  
</TABLE>

</form>

</body>

</html>