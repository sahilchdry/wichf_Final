<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Doctor</title>
</head>
<body>
	<form action="addPatient" method="post">
			 Health Card Number: <input type="text" name="healthCardNumber" />
			 Birth Date(yyyy-mm-dd): <input type="text" class="date" name="birthDate" value="" />
			 Gender(M/F):<input type="text" name="gender" value="" />
			 Name:<input type="text" name="name" value="" />
			 Phone No:<input type="text" name="phoneNumber" value="" />
			 Annual CheckUp Done(Y/N): <input type="text" name="isAnnualCheckUpDone" value="" /> 
     	      <input type="text" name="user.userId" value="User Id"/>
		      <input type="password" name="user.password" value="Password"/>
		      <input type="submit" value="Register"/>
		   </form>
   		
   		<hr/>
</body>
</html>