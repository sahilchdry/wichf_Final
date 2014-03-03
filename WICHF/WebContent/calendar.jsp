<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Minimum Required Code - Epoch DHTML Javascript Calendar</title>
<%@ include file="calendarScripts.jsp" %>
<script type="text/javascript">
/*You can also place this code in a separate file and link to it like epoch_classes.js*/
  var bas_cal,dp_cal,ms_cal;      
window.onload = function () {
  dp_cal  = new Epoch('epoch_popup','popup',document.getElementById('popup_container'));
};
</script>
</head>
<body>
<center><h2>Book Appointment</h2></center>
<s:if test="showApointments != true">
	<form id="placeholder" method="post" action="getAppointments">
 </s:if>
 <s:else>
 	<form id="placeholder" method="post" action="saveAppointment">
 </s:else>
<TABLE align="center" >
<s:if test="showApointments != true">
	<TR>
	    <TD>Choose day</TD>
	    <TD>
	      <input id="popup_container" type="text" name="datepick" value='<s:property value="visitTypeId"/>'/>
	    </TD>
	    <td> <img src="images/click.png" height = "20" width = "30"></td>
	    
	  </TR>
	  <TR>
	    <TD>Type of visit</TD>
	   
	  		<td>
	  		<select name="visitTypeSelect" >
	    	 <option value="Select">Select</option>
		    	<s:iterator value="visitTypeList">
						<option value='<s:property value="visitTypeId"/>' >   <s:property value="visitType"/></option>
				</s:iterator>
	    	</select>
	  		</td>
	  	</tr>
	  	<tr>
	  		<%if((String)session.getAttribute("accessLevel") != ("patient")){ %>
				
				<TD>UserId</TD>
   				 <TD><INPUT TYPE="TEXT" NAME="user_Id" value=" " SIZE="20"></TD>
		  
		  <%}%>
	  	</tr>
 </s:if>	
	  <tr>
	  	
  	<s:if test="showApointments">
  		<TD>Available Time slots</TD>
  		<td>
	  		
	  		<select name="selectedSlot">
	    	 <option value="Select">Select</option>
		    	<s:iterator value="availableTmSlotList">
						<option value='<s:property value="slotValue"/>'>   <s:property value="timeSlot"/></option>
				</s:iterator>
	    	</select>
	    	</td>
	  </s:if>
  		
  </tr>
  
  <tr>
  	<s:if test="showApointments">
  		<td>
	  <INPUT TYPE="SUBMIT" VALUE="Add to Cart" NAME="B1">
	  </td>
  	</s:if>
  	<s:else>
  		<td>
	  		<INPUT TYPE="SUBMIT" VALUE="Submit" NAME="B1">
	  </td>
	  </s:else>
  </tr>
  
</TABLE>

</form>

</body>

</html>