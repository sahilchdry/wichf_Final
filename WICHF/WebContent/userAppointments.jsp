<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<table>
		<tr>
			<th> Appointment Id </th>
			 
		</tr>
		<tr>
		
		<s:iterator value="appointmentList">
			<td>    
		        <s:property value="appointmentId"/><br>
		    </td>
		</s:iterator>
		</tr>
	</table>
</body>
</html>