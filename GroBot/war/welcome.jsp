<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ page import="groBot.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ page session="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>GroBot</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
		<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
		<script type="text/javascript">
			function getCookie(cname) {
			    var name = cname + "=";
			    var ca = document.cookie.split(';');
			    for(var i=0; i<ca.length; i++) {
			        var c = ca[i];
			        while (c.charAt(0)==' ') c = c.substring(1);
			        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
			    }
			    return "";
			}
			
			function checkCookie() {
			    var email=getCookie("GroBotEmail");
			    if (email=="") {
			        window.location.assign("/index.html")
			    }else{
			        
			    }
			}
		</script>
	</head>
	<body>
		<script>
			checkCookie();
		</script>
		<div id="wrapper">
			<div id="wrapper2">
				<div id="header" class="container">
					<div id="logo">
						<a href="welcome.jsp">
							<h1>GroBot</h1>
							<br/>
							<p>What you grow is not our business</p>
						</a>
					</div>
					<div id="menu">
						<ul>
							<li class="current_page_item"><a href="#">Start</a></li>
							<li><a href="connect.jsp">Connect</a></li>
							<li><a href="schedule.jsp">New Schedule</a></li>
							<li><a href="status.jsp">Status</a></li>
							<li><a href="profile.jsp">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
							
						</ul>
					</div>
				</div>
				<%
					if(session.getAttribute("email") != null){
						String em = (String)session.getAttribute("email");
						String name = (String)session.getAttribute("name");
						String botName = (String)session.getAttribute("botName");
					}
				%>
				<div id="page">
					<div id="content">
						<div class="post">
						  <span class="welcometext">Hello, ${fn:escapeXml(name)}, welcome to GroBot!</span>
						</div>
						<div id="wrapper" >
							<div style="height:200px; overflow: hidden">
								
								<br>
								<h2>Currently connected to: ${fn:escapeXml(botName)}</h2>
								<p>
									<div style="float:right">
										<form action="/growNow" method="post">
											<input type="hidden" name="itemID" value="id"/>
											<input class="lhbButton" type="submit" name="button" value="Grow Now!"/>
										</form>
										<br>
									</div>

								</p>
							</div>	
							<br>			
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer-content" valign="bottom">
			<div id="footer">
				<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
			</div>
		</div>
	</body>
</html>