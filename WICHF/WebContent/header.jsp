<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Venture</title>

<!-- SET: FAVICON -->
<link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" />
<!-- END: FAVICON -->

<!-- SET: STYLESHEET -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- END: STYLESHEET -->

<!-- SET: SCRIPTS -->
<script type="text/javascript"></script>
<!-- END: SCRIPTS -->
<!--[if lt IE 7]>
<style type="text/css">
.lorem p{ line-height:18px; }
</style>
<![endif]-->


<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function() {
        $.active = false;
        $('body').bind('click keypress', function() { $.active = true; });
        checkActivity(480000, 10000, 0); // timeout = 2 minutes, interval = 1 minute.
    });

    function checkActivity(timeout, interval, elapsed) {
        if ($.active) {
            elapsed = 0;
            $.active = false;
            $.get('heartbeat');
        }
        if (elapsed < timeout) {
            elapsed += interval;
            setTimeout(function() {
                checkActivity(timeout, interval, elapsed);
            }, interval);
        } else {
            window.location = '/WICHF'; // Redirect to "session expired" page.
        }
    }
</script>
</head>

<body>

<!-- wrapper starts -->
<div class="wrapper"> 
  
  <!-- container starts -->
  <div class="container"> 
    
    <!-- Header Starts -->
    <div class="header">
      <div class="logo">
        <h2><a href="home.jsp"><em>Walk-in hassle clinics</em></a><span>Health Experts</span></h2>
      </div>
      
      
      
      <div class="header_nav">
        <ul>
          <li><a href="home.jsp">Home</a></li>
          
          <%if((String)session.getAttribute("userId") != null){ %>
				
				<li><a href="/WICHF/logout">Logout</a></li>
				
				<li><a href="updateAccount.jsp">Update Account</a></li>
				
				<li><a href="patientmenu.jsp">My Account</a></li>
		  
		  <%}else{%>
			    <li><a href="login.jsp">Login</a></li>
         
         		<li><a href="RegisterPatient.jsp">Registration</a></li>
          
         		<li><a href="admin.jsp">Admin</a></li>
          
         		<li class="last"><a href="developers.jsp">Developers</a></li>
		  <%}%>
          
          
        </ul>
        <div class="clear"></div>
      </div>
      <div class="clear"></div>
    </div>
   <!-- <div class="banner"><img src="images/bannernew.jpg" width="1000" height="330" alt="banar" /></div>  --> 
    <!-- Header ends --> 
    
    <!-- maincontent Starts -->
    <div class="main_content">
      <div class="left_cont">
        <div class="content1">
          <h2><i>Welcome</i></h2>
          
        </div>
        
      
      <!-- footer starts -->
      