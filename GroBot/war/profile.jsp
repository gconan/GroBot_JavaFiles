<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ page import="groBot.entity.User" %>
<%@ page import="groBot.entity.Schedule" %>
<%@ page import="groBot.entity.GroBots" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
								
						<%
							String email = (String)session.getAttribute("GroBotEmail");
						%>
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
						<li><a href="status.jsp">Status</a></li>
						<li class="current_page_item"><a href="#">Profile</a></li>
						<li><a href="/logout">Logout</a></li>
					</ul>
				</div>
				<table style="border-collapse: collapse;">
					<tr>
						<td>
							<div id="outsideL" style="height:1200px;"></div>
							<div id="outsideR" style="height:1200px;"></div>
					  		<div id="content" style="height:1200px;">
								<br>
				<%
					if(session.getAttribute("GroBotEmail") != null){
						String em = (String)session.getAttribute("GroBotEmail");
						String name = (String)session.getAttribute("name");
						String last = (String)session.getAttribute("last");
					}%>
						<h2>${fn:escapeXml(name)} ${fn:escapeXml(last)}</h2>
						<br>

						
				<%
					if(session.getAttribute("GroBotEmail") != null){
							String emailadd1 = (String)session.getAttribute("GroBotEmail");
							ArrayList<GroBots> bots = (UserDAO.INSTANCE.getUserByEmail(emailadd1)).getAllBots();
					%>
						<div style="margin: 120px 15px 15px 0px;"><br><h2>Connect To a Different GroBot</h2><br>

								<form action="/changeBot" method="post">
										<select name="botList" autofocus>
											<%
												for(int j=0; j<bots.size(); j++){
													pageContext.setAttribute("botid", ""+(bots.get(j)).getId());
													pageContext.setAttribute("bName", (bots.get(j)).getName());

											%>		<option value="${fn:escapeXml(botid)}">${fn:escapeXml(bName)}</option>
											<% } 
										}
											%>
											
									  	</select>
										<input class="btn" type="submit" name="button" value="Connect Now!"/>
									</form>
							</div>

						<%
							if(session.getAttribute("GroBotEmail") != null){
								String emailadd = (String)session.getAttribute("GroBotEmail");
								ArrayList<Long> scheds = (UserDAO.INSTANCE.getUserByEmail(emailadd)).getSchedules();
								String size = ""+(scheds.size()+1);
								ArrayList<Schedule> schedules = new ArrayList<Schedule>();
								
								for(int i=0; i<scheds.size(); i++){
									schedules.add(UserDAO.INSTANCE.getSchedule(scheds.get(i)));
								}
						%>
							<div style="margin: 120px 15px 15px 0px;"><br><h2>Edit Your Schedules</h2><br>
								<form action="/editSched" method="post">
										<input type="hidden" name="size" value="${fn:escapeXml(size)}"/>
										<select name="schedule">
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
										<input class="btn" type="submit" name="button" value="Edit Schedule"/>
									</form>
							</div>
									<p>
										<div style="margin: 120px 15px 15px 0px;"><br><h2>Add a New GroBot</h2><br>
											<form action="/addGB" method="post">
												<input type="hidden" name="GroBotEmail" value="${fn:escapeXml(em)}"/>
													MacAddress: <br><input type="text" name="mac" id="mac" /><br><br>
													Name Your GroBot: <br><input type="text" name="nameofbot" id="nameofbot"/>
												
												<br>
												<br>
												<input class="btn" type="submit" name="submit" id="submit" value="Add My GroBot" />
											</form>
											<br>
											<br>
										</div>
									</p>
								


									<p>
										<div style="margin: 120px 15px 15px 0px;"><br><h2>Change Your Password</h2><br>
											<form action="/changep" method="post">
												Current Password: <input type="text" name="currPassw" id="currPassw" /><br>
												New Password: <input type="password" name="newPassw" id="newPassw" /><br>
												Verify New Password: <input type="password" name="verifNewPassw" id="currPassw" /><br><br>
												<input class="btn" type="submit" name="submit" id="submit" value="Change Password" />
											</form>

											<br>
											<br>
											<br>
											<br>
											
											<br>
											<div style="margin: 120px 15px 15px 0px;"><br><h2>Don't Leave Us :(</h2><br>
												<form action="/deleteAccount" method="post">
													<input class="btn" type="submit" name="delete" id="delete" value="Delete Account" />
												</form>
											</div>
											<br>
										</div>
									</p>
								
								</p>
								<br>
							</div>	
							<br>
						</div>
			</div>
		</td>
	</tr>
	<tr>		
		<td>	
			<div id="footer" style="margin-top: 1250px;">
					<div id="footer-content">
					<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
				</div>
			</div>
		</td>
	</tr>
		
</html>