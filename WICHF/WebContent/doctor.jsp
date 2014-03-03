<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Doctor</title>
</head>
<body>
<center><h2>Doctor Registration</h2></center>
	<form action="addDoctor">
		   
		
<TABLE align="center">
  
  <TR>
    <TD> Doctor Name:</TD>
    <TD><INPUT TYPE="TEXT" NAME="doctorName" value=" " SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>Doctor Status:</TD>
    <TD><INPUT TYPE="TEXT" NAME="status" value="Active" SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>User Id:</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.userId" value="User Id" SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>Password</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.password" value="Password" SIZE="20"></TD>
  </TR>
  
</TABLE>
		    <INPUT align="middle" TYPE="SUBMIT" VALUE="Add Doctor" NAME="B1">
		
		   </form>
   		
<%@ include file="footer.jsp" %>