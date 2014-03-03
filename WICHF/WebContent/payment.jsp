<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>


<center><h3>Payment</h3></center>
	
	<form action="doPayment" method="post">
<TABLE align="center">
  <TR>
    <TD>Card Holder's Name</TD>
    <TD><INPUT TYPE="TEXT" NAME="name" value=" " SIZE="20"></TD>
          
  </TR>
  <TR>
    <TD>Card Number</TD>
    <TD><INPUT TYPE="TEXT" NAME="healthCardNumber" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Expiry date(yy-mm)</TD>
    <TD><INPUT TYPE="TEXT" NAME="birthDate" value=" " SIZE="20"></TD>
  </TR>
  <TR>
    <TD>Card Type</TD>
    
    <TD><select>
  <option value="Select">Select</option>
  <option value="General">Master</option>
  <option value="Annual">Visa</option>
  
</select></TD>
  </TR>
  <tr>
  <td>
  <INPUT align="middle" TYPE="SUBMIT" VALUE="Confirm Payment" NAME="B1">
  </td>
  </tr>
  
</TABLE>

</form>


<%@ include file="footer.jsp" %>