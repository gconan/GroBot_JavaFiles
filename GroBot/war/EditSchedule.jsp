<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
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
			        window.location.assign("/index.html")
			    }else{
			    }
			}
		</script>
	</head>
		<script>
			checkCookie();
		</script>
			<table cellspacing="0">
		    <tr>
		        <td background="#cdbd00">
		        	<div id="titleBox">
					<div id="logo">
						<a href="welcome.jsp">
							<h1>GroBot</h1>
							<br/>
							<p>What you grow is not our business</p>
						</a>
					</div>
				</div>

				</td>
		    </tr>
		    <tr>
		        <td>
		        	<div id="menu">
							<ul>
							<li><a href="welcome.jsp">Welcome</a></li>
							<li><a href="schedule.jsp">New Schedule</a></li>
							<li><a href="status.jsp">Status</a></li>
							<li><a href="profile.jsp">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
						</ul>
					</div>
				</td>
		    </tr>
		</table>
		<table style="border-collapse: collapse;">
		<tr>
			<td>
		<div id="outsideL" style="height:700px;"></div>
		<div id="outsideR" style="height:700px;"></div>
		<div id="content" style="height:700px;">
			<br>
			<%
				String email = (String)session.getAttribute("GroBotEmail");
			%>
			<div id="formBox">
			<form action="/newSchedule" method="post" >
				<input type="hidden" name="GroBotEmail" value="${fn:escapeXml(email)}"/>
				<h2>New Custom Schedule</h2>
				<p>Please fill out each section to complete your new schedule.</p>					
				<div class="row">
					<label class="description" for="scheduleName">Name </label>
					<input id="scheduleName" name="scheduleName" class="element text medium" type="text" maxlength="255" value=""/> 
				</div> 
							
					<div id="section_break">
						<h1>Lights</h1>
						<p></p>
					</div>
					<div class="row">
						<label class="description" for="lightsOn">Turn Lights on at: </label>
							<select class="element select small" id="lightsOn" name="lightsOn"> 
								<option value="" selected="selected"></option>
								<option value="0:am" >12am</option>
								<option value="1:am" >1am</option>
								<option value="2:am" >2am</option>
								<option value="3:am" >3am</option>
								<option value="4:am" >4am</option>
								<option value="5:am" >5am</option>
								<option value="6:am" >6am</option>
								<option value="7:am" >7am</option>
								<option value="8:am" >8am</option>
								<option value="9:am" >9am</option>
								<option value="10:am" >10am</option>
								<option value="11:am" >11am</option>
								<option value="12:pm" >12pm</option>
								<option value="1:pm" >1pm</option>
								<option value="2:pm" >2pm</option>
								<option value="3:pm" >3pm</option>
								<option value="4:pm" >4pm</option>
								<option value="5:pm" >5pm</option>
								<option value="6:pm" >6pm</option>
								<option value="7:pm" >7pm</option>
								<option value="8:pm" >8pm</option>
								<option value="9:pm" >9pm</option>
								<option value="10:pm" >10pm</option>
								<option value="11:pm" >11pm</option>
							</select>
						</div> 
					<div class="row">
						<label class="description" for="lightsOff">Turn Lights off at: </label>
							<select class="element select small" id="lightsOff" name="lightsOff"> 
								<option value="" selected="selected"></option>
								<option value="0:am" >12am</option>
								<option value="1:am" >1am</option>
								<option value="2:am" >2am</option>
								<option value="3:am" >3am</option>
								<option value="4:am" >4am</option>
								<option value="5:am" >5am</option>
								<option value="6:am" >6am</option>
								<option value="7:am" >7am</option>
								<option value="8:am" >8am</option>
								<option value="9:am" >9am</option>
								<option value="10:am" >10am</option>
								<option value="11:am" >11am</option>
								<option value="12:pm" >12pm</option>
								<option value="1:pm" >1pm</option>
								<option value="2:pm" >2pm</option>
								<option value="3:pm" >3pm</option>
								<option value="4:pm" >4pm</option>
								<option value="5:pm" >5pm</option>
								<option value="6:pm" >6pm</option>
								<option value="7:pm" >7pm</option>
								<option value="8:pm" >8pm</option>
								<option value="9:pm" >9pm</option>
								<option value="10:pm" >10pm</option>
								<option value="11:pm" >11pm</option>

							</select>
						</div>
					<div class="row">
						<Legend>Power Light Set(s):</Legend><br>
									<label for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox"><br><br>
 									<label for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"> <br><br>
					</div>
					<div id="section_break">
						<h1>Water</h1>
						<p></p>
					</div>
					<div class="row">
					<label class="description" for="waterLength">Turn Water on for: </label>
							<select class="element select small" id="waterLength" name="waterLength"> 
								<option value="" selected="selected"></option>
								<option value="15" >15 minutes</option>
								<option value="30" >30 minutes</option>
								<option value="60" >60 minutes</option>

							</select>
						</div>
					<div class="row">
						<label class="description" for="waterPeriod">Every: </label>
							<select class="element select small" id="waterPeriod" name="waterPeriod"> 
								<option value="" selected="selected"></option>
								<option value="1" >1 hour</option>
								<option value="2" >2 hours</option>
								<option value="3" >3 hours</option>
								<option value="4" >4 hours</option>
								<option value="5" >5 hours</option>
								<option value="6" >6 hours</option>

							</select>
						</div> 
					<div id="section_break">
						<h1>Fixed Ports</h1>
						<p></p>
					</div>
					<div class="row">
							<legend>Auxiliary Port</legend><br>
									<label for="AuxOn">On</label><input value="On" id="AuxOn" name="Aux" type="radio">
 									<label for="AuxOff">Off</label><input value="Off" id="AuxOff" name="Aux" type="radio">
					</div>
					<br>
					<div id="section_break">
					</div>
					<div class="row">
							<legend>Air Pump</legend><br>
									<label for="AirOn">On</label><input value="On" id="AirOn" name="Air" type="radio">
 									<label for="AirOff">Off</label><input value="Off" id="AirOff" name="Air" type="radio">
					</div>
					<br>
					<br>
					<br>
					<center>
						<input class="btn" type="submit" name="submit" value="Save Scedule" />
					</center>
			</form>	
		</div>
		</div>
	</td>
</tr>
<tr>		
<td>	
<div id="footer" style="margin-top: 740px;">
			<div id="footer-content">
				<p>The University of Texas at Austin ECE Senior Design Project, sponsored by Texas Instruments</p> <!-- end #footer -->
			</div>
		</div>
	</td>
	</tr>
		
</html>