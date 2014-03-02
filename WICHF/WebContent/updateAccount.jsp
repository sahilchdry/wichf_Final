<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Information Update Form</title>
<SCRIPT LANGUAGE=javascript>

	

	function validateForm(formObj) {
		//TO DO Add Java Script Validation Code Here

		if (formObj.userid.value.length == 0) {
			alert("Please enter User Id!");
			formObj.userid.focus();
			return false;
		}
		
		if (formObj.phone.value.length == 0) {
			alert("Please enter Phone!");
			formObj.phone.focus();
			return false;
		}

		if (!formObj.phone.value.match(/^\d+$/)) {
			alert("Please enter correct Phone No.!");
			formObj.phone.focus();
			return false;
		}

		if (formObj.healthCardNumber.value.length == 0) {
			alert("Please select valid Health Card Number");
			formObj.healthCardNumber.focus();
			return false;
		}
		
		alert("Hello");
		return true;
	}

</SCRIPT>
</head>
<body>
<center><h3>Update Personal Information</h3></center>
	
	<form action="updateAccount" 	method="post" onsubmit="return validateForm(this);" >
<TABLE align="center">
  
  <TR>
    <TD>Health Card Number</TD>
    <TD><INPUT TYPE="TEXT" NAME="healthCardNumber" value=" " SIZE="20"></TD>
  </TR>
  
  
  <TR>
    <TD>Contact Number</TD>
    <TD><INPUT TYPE="TEXT" NAME="phone" value=" " SIZE="20"></TD>
  </TR>
  
  <TR>
    <TD>UserId</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.userId" value=" " SIZE="20"></TD>
  </TR>
  
    <INPUT align="middle" TYPE="SUBMIT" VALUE="Update Information" NAME="B1">
  </td>
  </tr>
</TABLE>

</form>
<%@ include file="footer.jsp" %>