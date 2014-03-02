<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<center><h1> Welcome Nurse!</h1></center>
       <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr> 
          <td width="19">&nbsp;</td>
          <td width="100%">&nbsp;</td>
        </tr>
        
        <tr> 
          
          
          <td  width="19"><img src="images/nmenu.png" height = "25" width = "25"></td>
          <td width="100%">Update appointment by Health card id</a></td>
          <TD><INPUT TYPE="text" NAME="update" SIZE="20"></TD>
          <td><input type="submit" value="submit" id="update">  </td>
          
        </tr>
        
        <tr> 
          
          
          <td  width="19"><img src="images/nmenu.png" height = "25" width = "25"></td>
          <td width="100%"><a href="/getAppointments">Cancel appointment by health card id</a></td>
          <TD><INPUT TYPE="text" NAME="cancel" SIZE="20"></TD>
           <td><input type="submit" value="submit" id="cancel">  </td>
        </tr>
        
        
        
        
        </table>


<%@ include file="footer.jsp" %>