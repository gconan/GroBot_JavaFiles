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


			function checkLightTimes(){
				 var onTime = parseInt(document.forms["scheduleForm"]["lightsOn"].value);
				 var offTime = parseInt(document.forms["scheduleForm"]["lightsOff"].value);
				 var valid;

				 if(offTime==-1 || onTime==-1){
				 	if(onTime==-1){
				 		alert("Please select a time to turn on the lights");
				 		document.getElementById("lightsonlabel").style.color = "red";
				 		valid=false;
					 }else if(offTime==-1){
					 	alert("Please select a time to turn off the lights");
					 	document.getElementById("lightsofflabel").style.color = "red";
					 	valid=false;
					 }
				 }else{
					 if(onTime >= offTime){
					 	alert("Your light \"on\" time must be less than the light \"off\" time.");
					 	document.getElementById("lightsonlabel").style.color = "red";
					 	document.getElementById("lightsofflabel").style.color = "red";
					 	valid=false;
					 }else{
					 	valid=true;
					 }
				 } 
				 return valid;
			}

			function checkWater(){
				var waterDur = parseInt(document.forms["scheduleForm"]["waterLength"].value);
				var waterPer = parseInt(document.forms["scheduleForm"]["waterPeriod"].value);

				if(waterDur==-1){
					alert("Water duration cannot be empty.");
					document.getElementById("waterdurlabel").style.color = "red";
					return false;
				}else if(waterPer==-1){
					alert("Water frequency cannot be empty.");
					document.getElementById("waterperlabel").style.color = "red";
					return false;
				}else if(waterDur==waterPer*60){
					if(confirm("This water setting will keep the water on continuously. Do you want to continue?")){
						return true;
					}
					document.getElementById("waterdurlabel").style.color = "red";
					document.getElementById("waterperlabel").style.color = "red";
					return false;
				}
				return true;

			}

			function checkNotNullEntries(){
				var set1 = document.getElementById("set1").checked;
				var set2 = document.getElementById("set2").checked;
				var aux = document.getElementsByName("Aux");
				var air = document.getElementsByName("Air");

			    if(!(set1 || set2)||(!(aux[0].checked || aux[1].checked))||(!(air[0].checked || air[1].checked))){
			    	
			    	if(!(set1 || set2)){
						alert("Please select a combination of light sets to use.");
						document.getElementById("set1label").style.color = "red";
						document.getElementById("set2label").style.color = "red";
						return false;
					}else if(!(aux[0].checked || aux[1].checked)){
						alert("Please select a setting for the auxillary port.");
						document.getElementById("auxonlabel").style.color = "red";
						document.getElementById("auxofflabel").style.color = "red";
						return false;
					}else if(!(air[0].checked || air[1].checked)){
						alert("Please select a setting for the air port.");
						document.getElementById("aironlabel").style.color = "red";
						document.getElementById("airofflabel").style.color = "red";
						return false;
					}
				}else{
					return true;
				}
			}
		
			function checkName(){
				var name = document.forms["scheduleForm"]["scheduleName"].value;
				if(name==null || name==""){
					if (confirm("Are you sure want to leave the name null?")) {
						return true;
					}else{
						document.getElementById("namelabel").style.color = "red";
						return false;
					}
				}else{
					return true;
				}

			}

			function validateForm(){
				document.getElementById("namelabel").style.color = "black";
				document.getElementById("lightsonlabel").style.color = "black";
			 	document.getElementById("lightsofflabel").style.color = "black";
				document.getElementById("waterdurlabel").style.color = "black";
				document.getElementById("waterperlabel").style.color = "black";
				document.getElementById("set1label").style.color = "black";
				document.getElementById("set2label").style.color = "black";
				document.getElementById("aironlabel").style.color = "black";
				document.getElementById("airofflabel").style.color = "black";
				document.getElementById("auxonlabel").style.color = "black";
				document.getElementById("auxofflabel").style.color = "black";
				if(checkName()){
					if(checkLightTimes()){
						if(checkWater()){
							if(checkNotNullEntries()){
								document.getElementById("scheduleForm").submit();
							}
						}
					}
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
							<li class="current_page_item"><a href="#">New Schedule</a></li>
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
				ArrayList<Long> scheds = (UserDAO.INSTANCE.getUserByEmail(email)).getSchedules();
				String size = ""+(scheds.size()+1);
			%>
			<div id="formBox">
			<form id="scheduleForm" action="/newSchedule" method="post">
				<input type="hidden" name="size" value="${fn:escapeXml(size)}"/>
				<h2>New Custom Schedule</h2>
				<p>Please fill out each section to complete your new schedule.</p>					
				<div class="row">
					<label id="namelabel" class="description" for="scheduleName">*Name </label>
					<input id="scheduleName" name="scheduleName" class="element text medium" type="text" maxlength="255" value=""/> 
				</div> 
							
					<div id="section_break">
						<h1>Lights</h1>
						<p></p>
					</div>
					<div class="row">
						<label id="lightsonlabel" class="description" for="lightsOn">*Turn Lights on at: </label>
							<select class="element select small" id="lightsOn" name="lightsOn"> 
								<option value="-1" selected="selected"></option>
								<option value="0" >12am</option>
								<option value="100" >1am</option>
								<option value="200" >2am</option>
								<option value="300" >3am</option>
								<option value="400" >4am</option>
								<option value="500" >5am</option>
								<option value="600" >6am</option>
								<option value="700" >7am</option>
								<option value="800" >8am</option>
								<option value="900" >9am</option>
								<option value="1000" >10am</option>
								<option value="1100" >11am</option>
								<option value="1200" >12pm</option>
								<option value="1300" >1pm</option>
								<option value="1400" >2pm</option>
								<option value="1500" >3pm</option>
								<option value="1600" >4pm</option>
								<option value="1700" >5pm</option>
								<option value="1800" >6pm</option>
								<option value="1900" >7pm</option>
								<option value="2000" >8pm</option>
								<option value="2100" >9pm</option>
								<option value="2200" >10pm</option>
								<option value="2300" >11pm</option>
							</select>
						</div> 
					<div class="row">
						<label id="lightsofflabel" class="description" for="lightsOff">*Turn Lights off at: </label>
							<select class="element select small" id="lightsOff" name="lightsOff"> 
								<option value="-1" selected="selected"></option>
								<option value="0" >12am</option>
								<option value="100" >1am</option>
								<option value="200" >2am</option>
								<option value="300" >3am</option>
								<option value="400" >4am</option>
								<option value="500" >5am</option>
								<option value="600" >6am</option>
								<option value="700" >7am</option>
								<option value="800" >8am</option>
								<option value="900" >9am</option>
								<option value="1000" >10am</option>
								<option value="1100" >11am</option>
								<option value="1200" >12pm</option>
								<option value="1300" >1pm</option>
								<option value="1400" >2pm</option>
								<option value="1500" >3pm</option>
								<option value="1600" >4pm</option>
								<option value="1700" >5pm</option>
								<option value="1800" >6pm</option>
								<option value="1900" >7pm</option>
								<option value="2000" >8pm</option>
								<option value="2100" >9pm</option>
								<option value="2200" >10pm</option>
								<option value="2300" >11pm</option>

							</select>
						</div>
					<div class="row">
						<Legend>*Power Light Set(s):</Legend><br>
									<label id="set1label" for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox"><br><br>
 									<label id="set2label" for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"> <br><br>
					</div>
					<div id="section_break">
						<h1>Water</h1>
						<p></p>
					</div>
					<div class="row">
					<label id="waterdurlabel" class="description" for="waterLength">*Turn Water on for: </label>
							<select class="element select small" id="waterLength" name="waterLength"> 
								<option value="-1" selected="selected"></option>
								<option value="15" >15 minutes</option>
								<option value="30" >30 minutes</option>
								<option value="60" >60 minutes</option>

							</select>
						</div>
					<div class="row">
						<label id="waterperlabel" class="description" for="waterPeriod">*Every: </label>
							<select class="element select small" id="waterPeriod" name="waterPeriod"> 
								<option value="-1" selected="selected"></option>
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
							<legend>*Auxiliary Port</legend><br>
									<label id="auxonlabel" for="AuxOn">On</label><input value="On" id="AuxOn" name="Aux" type="radio">
 									<label id="auxofflabel" for="AuxOff">Off</label><input value="Off" id="AuxOff" name="Aux" type="radio">
					</div>
					<br>
					<div id="section_break">
					</div>
					<div class="row">
							<legend>*Air Pump</legend><br>
									<label id="aironlabel" for="AirOn">On</label><input value="On" id="AirOn" name="Air" type="radio">
 									<label id="airofflabel" for="AirOff">Off</label><input value="Off" id="AirOff" name="Air" type="radio">
					</div>
					<br>
					<br>
					<br>
					<center>
						<button class="btn" type="button" onclick="validateForm()">Save Scedule</button>
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