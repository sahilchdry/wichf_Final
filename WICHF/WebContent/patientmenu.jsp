<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ include file="header.jsp" %>
<center><h1> Welcome User!</h1></center>
       <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr> 
          <td width="19">&nbsp;</td>
          <td width="100%">&nbsp;</td>
        </tr>
        
        <tr> 
          
          
          <td  width="19"><img src="images/appointment.png" height = "50" width = "50"></td>
          <td width="100%"><a href="calendar.jsp">Book Appointment</a></td>
        </tr>
        
        <tr> 
          
          
          <td  width="19"><img src="images/history.png" height = "50" width = "50"></td>
          <td width="100%"><a href="/getAppointments">View History</a></td>
        </tr>
        
        
        
        </table>


<%@ include file="footer.jsp" %>