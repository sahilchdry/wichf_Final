<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>
<script language="JavaScript">
var currentdate = new Date();
var datetime = currentdate.getMonth()+"/"+ currentdate.getDay()
				+"/" +currentdate.getFullYear()
				+"/" +currentdate.getMinutes()
				+" " +currentdate.getHours()
				+":" + currentdate.getMinutes()
				+" " +currentdate.
 				;
TargetDate = "12/31/2020 5:00 AM";
BackColor = "white";
ForeColor = "navy";
CountActive = true;
CountStepper = -1;
LeadingZero = true;
DisplayFormat = "%%D%% Days, %%H%% Hours, %%M%% Minutes, %%S%% Seconds.";
FinishMessage = "It is finally here!";
</script>
<script language="JavaScript" src="http://scripts.hashemian.com/js/countdown.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Count down time</title>
</head>
<body>
<%
   Date date = new Date();
   out.print( "<h2 align=\"center\">" +date.toString()+"</h2>");
%>
</body>
</html>