<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Nurse</title>
</head>
<body>
<center><h2>Nurse Registration</h2></center>
	
	<form action="addNurse" method="post">
		
<TABLE align="center">
  
  <TR>
    <TD> Nurse Name:</TD>
    <TD><INPUT TYPE="TEXT" NAME="nurseName" value=" " SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>Nurse Status:</TD>
    <TD><INPUT TYPE="TEXT" NAME="status" value="Active" SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>User Id:</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.userId" value="User Id" SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>Password</TD>
    <TD><INPUT TYPE="password" NAME="user.password" value="Password" SIZE="20"></TD>
  </TR>
  
</TABLE>
		     <center><INPUT align="middle" TYPE="SUBMIT" VALUE="Add Doctor" NAME="B1"><input type="button" onclick="location.href('admin.jsp');" value="cancel"></center>
		
		   </form>
   		<%@ include file="footer.jsp" %>