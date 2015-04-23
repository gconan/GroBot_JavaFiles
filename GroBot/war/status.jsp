<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ page import="groBot.entity.Schedule" %>
<%@ page import="groBot.entity.GroBots" %>
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
			        window.location.assign("/GroBot_index.html")
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
					<div id="outsideL"></div>
					<div id="outsideR"></div>
			  		<div id="content">
			  			<%
							if(session.getAttribute("GroBotEmail") != null){
								String em = (String)session.getAttribute("GroBotEmail");
								String name = (String)session.getAttribute("name");
								GroBots bot = UserDAO.INSTANCE.getCurrentUserBot(em);
								String botName = bot.getName();
								Schedule sched = bot.getCurrentSchedule();
								String sName = sched.getName();
								pageContext.setAttribute("sname", sName);

								String lightRemaining = "";
								if(bot.getLightOn().equalsIgnoreCase("true")){
									lightRemaining = bot.getLightTimeRemaining();
								}

								String waterRemaining = "";
								if(bot.getWaterOn().equalsIgnoreCase("true")){
									waterRemaining = bot.getWaterTimeRemaining();
								}
								
								String aux = bot.getAuxOn();
								String air = bot.getAirOn();
						%>
							<div style="height:600px; overflow: hidden">
								<br>
								<h2>Current Status Of: </h2><h3 style="color:green"> ${fn:escapeXml(botName)}</h3>
								<br>
								<br>
								<h2>Schedule Running: </h2><h3 style="color:green"> ${fn:escapeXml(sname)}</h3>

								<% 
									if( lightRemaining.equals("") && waterRemaining.equals("") ){ 
										//both off
								%>
										<p>
											both off<br>
											The lights will turn back on in ${fn:escapeXml(lightRemaining)} minutes.<br>
											The water will turn back on in ${fn:escapeXml(waterRemaining)} minutes.<br>
											The auxillary port status is: ${fn:escapeXml(aux)}<br>
											The air pump status is: ${fn:escapeXml(air)}
										</p>
								<% 
									}else if( lightRemaining.equals("") && !waterRemaining.equals("") ){ 
										//light off, water on
								%>
										<p>
											light off, water on<br>
											The lights will turn back on in ${fn:escapeXml(lightRemaining)} more minutes.<br>
											The water will be on for ${fn:escapeXml(waterRemaining)} more minutes.<br>
											The auxillary port status is: ${fn:escapeXml(aux)}<br>
											The air pump status is: ${fn:escapeXml(air)}
										</p>
								<% 
									}else if( !lightRemaining.equals("") && waterRemaining.equals("") ){ 
										//light on, water off
								%>
										<p>
											light on, water off<br>
											The lights will be on for ${fn:escapeXml(lightRemaining)} more minutes.<br>
											The water will turn back on in ${fn:escapeXml(waterRemaining)} more minutes.<br>
											The auxillary port status is: ${fn:escapeXml(aux)}<br>
											The air pump status is: ${fn:escapeXml(air)}
										</p>
								<% 
									}else if( !lightRemaining.equals("") && !waterRemaining.equals("") ){ 
										//light and water on
								%>
										<p>
											both on<br>
											The lights will be on for ${fn:escapeXml(lightRemaining)} more minutes.<br>
											The water will be on for ${fn:escapeXml(waterRemaining)} more minutes.<br>
											The auxillary port status is: ${fn:escapeXml(aux)}<br>
											The air pump status is: ${fn:escapeXml(air)}
										</p>
								<% 
									}else{ 
										//something bad happened
								%>
										<p>
											UH OH!!!
										</p>
								<% 
									}
								%>
							</div>				
						<%
							}
						%>
						
					</div>
		<div id="footer">
			<div id="footer-content">
				<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
			</div>
		</div>
</html>