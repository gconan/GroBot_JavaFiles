<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ page session="true" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>GroBot</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
		<link href="grobot.css" rel="stylesheet" type="text/css" media="screen" />
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
			    var email=getCookie("GroBotEmailCookie");
			    if (email=="") {
			        window.location.assign("/index.html")
			    }else{
			        
			    }
			}
		</script>
	</head>
		<script>
			checkCookie();
		</script>
				<div id="titleBox">
					<div id="logo">
						<a href="welcome.jsp">
							<h1>GroBot</h1>
							<br/>
							<p>What you grow is not our business</p>
						</a>
					</div>
				</div>
					<div id="menu">
						<ul>
							<li><a href="welcome.jsp">Welcome</a></li>
							<li><a href="schedule.jsp">New Schedule</a></li>
							<li class="current_page_item"><a href="#">Status</a></li>
							<li><a href="profile.jsp">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
							
						</ul>
					</div>
				<%
					if(session.getAttribute("GroBotEmail") != null){
						String em = (String)session.getAttribute("GroBotEmail");
						String name = (String)session.getAttribute("name");
						String botName = UserDAO.INSTANCE.getBotNameByOwner(em);
					}
				%>
				
					<div id="outsideL"></div>
					<div id="outsideR"></div>
			  		<div id="content">
							<div style="height:200px; overflow: hidden">
								<br>
								<h2>Current Status Of: ${fn:escapeXml(botName)}</h2>
							</div>				

						
					</div>
		<div id="footer">
			<div id="footer-content">
				<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
			</div>
		</div>
</html>