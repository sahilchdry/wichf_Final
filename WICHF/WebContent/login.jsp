<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>

<html>
<head>
<title>Login</title>
</head>
<body>

<br><center><h3>Login</h3></center>
<FORM action="login" method="post">
<TABLE align="center" >
  <TR>
    <TD>User Name</TD>
    <TD>
      <INPUT TYPE="TEXT" NAME="userId" SIZE="20">
    </TD>
  </TR>
  <TR>
    <TD>Password</TD>
    <TD><INPUT TYPE="password" NAME="password" SIZE="20"></TD>
  </TR>
  <tr>
  <td><input type="submit" value="Login" id="login">  </td>
 <td><input type="button" onclick="location.href('home.jsp');" value="Cancel" id="cancel"></td>
  
  
  
  </tr>
</TABLE>

</FORM>
</body>
</html>

        
<%@ include file="footer.jsp" %>