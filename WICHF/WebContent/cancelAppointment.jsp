<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Appointment</title>
</head>
<body>

 <form action="cancelappointment" method="post">
     	      <input type="text" name="appointmentId" value=""/>
		 
		      <input type="submit" value="Cancel"/>
		   </form>

</body>
</html>

<%@ include file="footer.jsp" %>