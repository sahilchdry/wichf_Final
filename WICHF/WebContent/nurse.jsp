<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Nurse</title>
</head>
<body>
	<form action="addNurse">
			 
			 Nurse Name:  <input type="text" name="nurseName" value=""/>
			 Nurse Status: <input type="text" name="status" value="Active"/>
			  
     	      <input type="text" name="user.userId" value="User Id"/>
		      <input type="password" name="user.password" value="Password"/>
		      <input type="submit" value="Add Nurse"/>
		   </form>
   		
   		<hr/>
</body>
</html>