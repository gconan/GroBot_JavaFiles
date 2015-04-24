<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ page import="groBot.entity.User" %>
<%@ page import="groBot.entity.Schedule" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>GroBot</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link rel="shortcut icon" href="http://the-grobot.appspot.com/war/favicon.ico">
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
							<li class="current_page_item"><a href="#">Welcome</a></li>
							<li><a href="schedule.jsp">New Schedule</a></li>
							<li><a href="status.jsp">Status</a></li>
							<li><a href="profile.jsp">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
						</ul>
					</div>
				<%
					if(session.getAttribute("GroBotEmail") != null){
						String em = (String)session.getAttribute("GroBotEmail");
						String name = (String)session.getAttribute("name");
						String botName = UserDAO.INSTANCE.getBotNameByOwner(em);
						ArrayList<Long> scheds = (UserDAO.INSTANCE.getUserByEmail(em)).getSchedules();
						ArrayList<Schedule> schedules = new ArrayList<Schedule>();
						
						for(int i=0; i<scheds.size(); i++){
							schedules.add(UserDAO.INSTANCE.getSchedule(scheds.get(i)));
						}
				%>
			
					<div id="outsideL"></div>
					<div id="outsideR"></div>
			  		<div id="content">
					
							<br>
						  <h1 class="welcometext">Hello ${fn:escapeXml(name)}, Welcome to GroBot!</h1>
							<div style="height:200px; overflow: hidden">
								
								<br>
								<h2>Currently connected to:</h2><h2 style="color:green"> ${fn:escapeXml(botName)}</h2>
								<p>
									<form action="/growNow" method="post">
										<input type="hidden" name="GroBotEmail" value="${fn:escapeXml(em)}"/>
										<select name="schedule" id="scheduleDropDown" autofocus>
											<%
												for(int i=0; i<schedules.size(); i++){
													pageContext.setAttribute("value", schedules.get(i).getValue());
													pageContext.setAttribute("sName", schedules.get(i).getName());
												%>
													<option value="${fn:escapeXml(value)}">${fn:escapeXml(sName)}</option>
												<%
												}
				}
												%>
											
									  	</select>
										<input class="btn" type="submit" name="button" value="Grow Now!"/>
									</form>
								</p>
							</div>	
							<br>			
						</div>
		<div id="footer">
			<div id="footer-content">
				<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
			</div>
		</div>
</html>