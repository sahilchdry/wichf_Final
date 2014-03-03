<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Registration Form</title>
<SCRIPT LANGUAGE=javascript>

	

	function validateForm(formObj) {
		//TO DO Add Java Script Validation Code Here

		if (formObj.userid.value.length == 0) {
			alert("Please enter User Id!");
			formObj.userid.focus();
			return false;
		}

		if (formObj.password.value.length == 0) {
			alert("Please enter Password!");
			formObj.password.focus();
			return false;
		}
		
		if (formObj.password.value == formObj.Spassword.value) {
			alert("Password do not match!");
			formObj.password.focus();
			return false;
		}

		if (formObj.birthDate.value.length == 0) {
			alert("Please select valid Birth Date");
			formObj.birthDate.focus();
			return false;
		}
		if (formObj.name.value.length == 0) {
			alert("Please enter First Name!");
			formObj.name.focus();
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
		
		if (formObj.birthDate.value.length == 0) {
			alert("Please select valid Birth Date");
			formObj.birthDate.focus();
			return false;
		}
		

		alert("Hello");
		return true;
	}

</SCRIPT>
</head>
<body>
<center><h3>Registration</h3></center>
	
	<form action="addPatient" 	method="post" onsubmit="return validateForm(this);" >
<TABLE align="center">
  <TR>
    <TD>Name</TD>
    <TD><INPUT TYPE="TEXT" NAME="name" value=" " SIZE="20"></TD>
          
  </TR>
  <TR>
    <TD>Health Card Number</TD>
    <TD><INPUT TYPE="TEXT" NAME="healthCardNumber" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Birth Date(yyyy-mm-dd)</TD>
    <TD><INPUT TYPE="TEXT" NAME="birthDate" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Contact Number</TD>
    <TD><INPUT TYPE="TEXT" NAME="phone" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Gender</TD>
    
    <td><input type="radio" name="gender" value="M">Male
    <input type="radio" name="gender" value="F">Female</td>
  </TR>
  <TR>
    <TD>Annual CheckUp Done</TD>
    
    <td><input type="radio" name="isAnnualCheckUpDone" value="Y">Yes
    <input type="radio" name="isAnnualCheckUpDone" value="N">No</td>
  </TR>
  <TR>
    <TD>UserId</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.userId" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Password</TD>
    <TD><INPUT TYPE="TEXT" NAME="user.password" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Re Enter Password</TD>
    <TD><INPUT TYPE="TEXT" NAME="Spassword" value=" " SIZE="20"></TD>
  </TR>
  
</TABLE>
<center><INPUT align="middle" TYPE="SUBMIT" VALUE="Register" NAME="B1"><input type="button" onclick="location.href('home.jsp');" value="cancel"></center>
</form>
<%@ include file="footer.jsp" %>