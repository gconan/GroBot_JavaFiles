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
			        window.location.assign("/GroBot_index.html")
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
				String size = (String)session.getAttribute("size");
				String schedID = (String)session.getAttribute("schedID");
				String email = (String)session.getAttribute("GroBotEmail");
				String schedName = (String)session.getAttribute("schedName");
				String lightOn = (String)session.getAttribute("lightOn");
				String lightOff = (String)session.getAttribute("lightOff");
				String pins = (String)session.getAttribute("lightPins");

				String waterDur = (String)session.getAttribute("waterDur");
				String waterPer = (String)session.getAttribute("waterPer");

				String aux = (String)session.getAttribute("aux");
				String air = (String)session.getAttribute("air");
			%>
			<div id="formBox">
			<form action="/saveChanges" method="post" >
				<input type="hidden" name="schedID" value="${fn:escapeXml(schedID)}"/>
				<h2>Edit Your Custom Schedule</h2>
				<p>Feel free to edit any part of your schedule.</p>					
				<div class="row">
					<label class="description" for="scheduleName">Name </label>
					<input id="scheduleName" name="scheduleName" class="element text medium" type="text" maxlength="255" value="${fn:escapeXml(schedName)}" autofocus/> 
				</div> 
							
					<div id="section_break">
						<h1>Lights</h1>
						<p></p>
					</div>
					<div class="row">
						<label class="description" for="lightsOn">Turn Lights on at: </label>
							<select class="element select small" id="lightsOn" name="lightsOn"> 
								<%if(lightOn.equals("0")){
									%><option value="0" selected="selected">12am</option><%
								}else{
									%><option value="0" >12am</option><%
								}
								if(lightOn.equals("100")){
									%><option value="100" selected="selected">1am</option><%
								}else{
									%><option value="100" >1am</option><%
								}
								if(lightOn.equals("200")){
									%><option value="200" selected="selected">2am</option><%
								}else{
									%><option value="200" >2am</option><%
								}
								if(lightOn.equals("300")){
									%><option value="300" selected="selected">3am</option><%
								}else{
									%><option value="300" >3am</option><%
								}
								if(lightOn.equals("400")){
									%><option value="400" selected="selected">4am</option><%
								}else{
									%><option value="400" >4am</option><%
								}
								if(lightOn.equals("500")){
									%><option value="500" selected="selected">5am</option><%
								}else{
									%><option value="500" >5am</option><%
								}
								if(lightOn.equals("600")){
									%><option value="600" selected="selected">6am</option><%
								}else{
									%><option value="600" >6am</option><%
								}
								if(lightOn.equals("700")){
									%><option value="700" selected="selected">7am</option><%
								}else{
									%><option value="700" >7am</option><%
								}
								if(lightOn.equals("800")){
									%><option value="800" selected="selected">8am</option><%
								}else{
									%><option value="800" >8am</option><%
								}
								if(lightOn.equals("900")){
									%><option value="900" selected="selected">9am</option><%
								}else{
									%><option value="900" >9am</option><%
								}
								if(lightOn.equals("1000")){
									%><option value="1000" selected="selected">10am</option><%
								}else{
									%><option value="1000" >10am</option><%
								}
								if(lightOn.equals("1100")){
									%><option value="1100" selected="selected">11am</option><%
								}else{
									%><option value="1100" >11am</option><%
								}
								if(lightOn.equals("1200")){
									%><option value="1200" selected="selected">12pm</option><%
								}else{
									%><option value="1200" >12pm</option><%
								}
								if(lightOn.equals("1300")){
									%><option value="1300" selected="selected">1pm</option><%
								}else{
									%><option value="1300" >1pm</option><%
								}
								if(lightOn.equals("1400")){
									%><option value="1400" selected="selected">2pm</option><%
								}else{
									%><option value="1400" >2pm</option><%
								}
								if(lightOn.equals("1500")){
									%><option value="1500" selected="selected">3pm</option><%
								}else{
									%><option value="1500" >3pm</option><%
								}
								if(lightOn.equals("1600")){
									%><option value="1600" selected="selected">4pm</option><%
								}else{
									%><option value="1600" >4pm</option><%
								}
								if(lightOn.equals("1700")){
									%><option value="1700" selected="selected">5pm</option><%
								}else{
									%><option value="1700" >5pm</option><%
								}
								if(lightOn.equals("1800")){
									%><option value="1800" selected="selected">6pm</option><%
								}else{
									%><option value="1800" >6pm</option><%
								}
								if(lightOn.equals("1900")){
									%><option value="1900" selected="selected">7pm</option><%
								}else{
									%><option value="1900" >7pm</option><%
								}
								if(lightOn.equals("2000")){
									%><option value="1200" selected="selected">8pm</option><%
								}else{
									%><option value="2000" >8pm</option><%
								}
								if(lightOn.equals("2100")){
									%><option value="2100" selected="selected">9pm</option><%
								}else{
									%><option value="2100" >9pm</option><%
								}
								if(lightOn.equals("2200")){
									%><option value="2200" selected="selected">10pm</option><%
								}else{
									%><option value="2200" >10pm</option><%
								}
								if(lightOn.equals("2300")){
									%><option value="2300" selected="selected">11pm</option><%
								}else{
									%><option value="2300" >11pm</option><%
								}%>
							</select>
						</div> 
					<div class="row">
						<label class="description" for="lightsOff">Turn Lights off at: </label>
							<select class="element select small" id="lightsOff" name="lightsOff"> 
								<%if(lightOff.equals("0")){
									%><option value="0" selected="selected">12am</option><%
								}else{
									%><option value="0" >12am</option><%
								}
								if(lightOff.equals("100")){
									%><option value="100" selected="selected">1am</option><%
								}else{
									%><option value="100" >1am</option><%
								}
								if(lightOff.equals("200")){
									%><option value="200" selected="selected">2am</option><%
								}else{
									%><option value="200" >2am</option><%
								}
								if(lightOff.equals("300")){
									%><option value="300" selected="selected">3am</option><%
								}else{
									%><option value="300" >3am</option><%
								}
								if(lightOff.equals("400")){
									%><option value="400" selected="selected">4am</option><%
								}else{
									%><option value="400" >4am</option><%
								}
								if(lightOff.equals("500")){
									%><option value="500" selected="selected">5am</option><%
								}else{
									%><option value="500" >5am</option><%
								}
								if(lightOff.equals("600")){
									%><option value="600" selected="selected">6am</option><%
								}else{
									%><option value="600" >6am</option><%
								}
								if(lightOff.equals("700")){
									%><option value="700" selected="selected">7am</option><%
								}else{
									%><option value="700" >7am</option><%
								}
								if(lightOff.equals("800")){
									%><option value="800" selected="selected">8am</option><%
								}else{
									%><option value="800" >8am</option><%
								}
								if(lightOff.equals("900")){
									%><option value="900" selected="selected">9am</option><%
								}else{
									%><option value="900" >9am</option><%
								}
								if(lightOff.equals("1000")){
									%><option value="1000" selected="selected">10am</option><%
								}else{
									%><option value="1000" >10am</option><%
								}
								if(lightOff.equals("1100")){
									%><option value="1100" selected="selected">11am</option><%
								}else{
									%><option value="1100" >11am</option><%
								}
								if(lightOff.equals("1200")){
									%><option value="1200" selected="selected">12pm</option><%
								}else{
									%><option value="1200" >12pm</option><%
								}
								if(lightOff.equals("1300")){
									%><option value="1300" selected="selected">1pm</option><%
								}else{
									%><option value="1300" >1pm</option><%
								}
								if(lightOff.equals("1400")){
									%><option value="1400" selected="selected">2pm</option><%
								}else{
									%><option value="1400" >2pm</option><%
								}
								if(lightOff.equals("1500")){
									%><option value="1500" selected="selected">3pm</option><%
								}else{
									%><option value="1500" >3pm</option><%
								}
								if(lightOff.equals("1600")){
									%><option value="1600" selected="selected">4pm</option><%
								}else{
									%><option value="1600" >4pm</option><%
								}
								if(lightOff.equals("1700")){
									%><option value="1700" selected="selected">5pm</option><%
								}else{
									%><option value="1700" >5pm</option><%
								}
								if(lightOff.equals("1800")){
									%><option value="1800" selected="selected">6pm</option><%
								}else{
									%><option value="1800" >6pm</option><%
								}
								if(lightOff.equals("1900")){
									%><option value="1900" selected="selected">7pm</option><%
								}else{
									%><option value="1900" >7pm</option><%
								}
								if(lightOff.equals("2000")){
									%><option value="1200" selected="selected">8pm</option><%
								}else{
									%><option value="2000" >8pm</option><%
								}
								if(lightOff.equals("2100")){
									%><option value="2100" selected="selected">9pm</option><%
								}else{
									%><option value="2100" >9pm</option><%
								}
								if(lightOff.equals("2200")){
									%><option value="2200" selected="selected">10pm</option><%
								}else{
									%><option value="2200" >10pm</option><%
								}
								if(lightOff.equals("2300")){
									%><option value="2300" selected="selected">11pm</option><%
								}else{
									%><option value="2300" >11pm</option><%
								}%>

							</select>
						</div>
					<div class="row">
						<Legend>Power Light Set(s):</Legend><br>
								<%if(pins.equals("0")){%>
									<label for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox"><br><br>
 									<label for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"> <br><br>

								<%}else if(pins.equals("1")){%>
									<label for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox"><br><br>
 									<label for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"checked> <br><br>

								<%}else if(pins.equals("2")){%>
									<label for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox" checked><br><br>
	 									<label for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"> <br><br>

								<%}else{%>
									<label for="set1">Vegitative Lights</label><input value="true" id="set1" name="set1" type="checkbox" checked><br><br>
		 									<label for="set2">Flowering Lights</label><input value="true" id="set2" name="set2" type="checkbox"checked> <br><br>

								<%}%>
					</div>
					<div id="section_break">
						<h1>Water</h1>
						<p></p>
					</div>
					<div class="row">
					<label class="description" for="waterLength">Turn Water on for: </label>
							<select class="element select small" id="waterLength" name="waterLength"> 
								<%if(waterDur.equals("15")){
									%><option value="15" selected="selected">15 minutes</option><%
								}else{
									%><option value="15" >15 minutes</option><%
								}
								if(waterDur.equals("30")){
									%><option value="30" selected="selected">30 minutes</option><%
								}else{
									%><option value="30" >30 minutes</option><%
								}
								if(waterDur.equals("60")){
									%><option value="60" selected="selected">60 minutes</option><%
								}else{
									%><option value="60" >60 minutes</option><%
								}%>

							</select>
						</div>
					<div class="row">
						<label class="description" for="waterPeriod">Every: </label>
							<select class="element select small" id="waterPeriod" name="waterPeriod"> 
								<%if(waterPer.equals("1")){
									%><option value="1" selected="selected">1 hour</option><%
								}else{
									%><option value="1" >1 hour</option><%
								}
								if(waterPer.equals("2")){
									%><option value="2" selected="selected">2 hours</option><%
								}else{
									%><option value="2" >2 hours</option><%
								}
								if(waterPer.equals("3")){
									%><option value="3" selected="selected">3 hours</option><%
								}else{
									%><option value="3" >3 hours</option><%
								}
								if(waterPer.equals("4")){
									%><option value="4" selected="selected">4 hours</option><%
								}else{
									%><option value="4" >4 hours</option><%
								}
								if(waterPer.equals("5")){
									%><option value="5" selected="selected">5 hours</option><%
								}else{
									%><option value="5" >5 hours</option><%
								}
								if(waterPer.equals("6")){
									%><option value="6" selected="selected">6 hours</option><%
								}else{
									%><option value="6" >6 hours</option><%
								}
								if(waterPer.equals("7")){
									%><option value="7" selected="selected">7 hours</option><%
								}else{
									%><option value="7" >7 hours</option><%
								}
								if(waterPer.equals("8")){
									%><option value="8" selected="selected">8 hours</option><%
								}else{
									%><option value="8" >8 hours</option><%
								}
								if(waterPer.equals("9")){
									%><option value="9" selected="selected">9 hours</option><%
								}else{
									%><option value="9" >9 hours</option><%
								}%>
							</select>
						</div> 
					<div id="section_break">
						<h1>Fixed Ports</h1>
						<p></p>
					</div>
					<div class="row">
						<%if(aux.equals("true")){%>
							<legend>Auxiliary Port</legend><br>
									<label for="AuxOn">On</label><input value="On" id="AuxOn" name="Aux" type="radio" checked="checked">
 									<label for="AuxOff">Off</label><input value="Off" id="AuxOff" name="Aux" type="radio">
 						<%}else{%>
							<legend>Auxiliary Port</legend><br>
									<label for="AuxOn">On</label><input value="On" id="AuxOn" name="Aux" type="radio">
 									<label for="AuxOff">Off</label><input value="Off" id="AuxOff" name="Aux" type="radio" checked="checked">
 								<%}%>
					</div>
					<br>
					<div id="section_break">
					</div>
					<div class="row">
						<%if(air.equals("true")){%>
							<legend>Air Pump</legend><br>
									<label for="AirOn">On</label><input value="On" id="AirOn" name="Air" type="radio" checked="checked">
 									<label for="AirOff">Off</label><input value="Off" id="AirOff" name="Air" type="radio">
 						<%}else{%>
							<legend>Air Pump</legend><br>
									<label for="AirOn">On</label><input value="On" id="AirOn" name="Air" type="radio">
 									<label for="AirOff">Off</label><input value="Off" id="AirOff" name="Air" type="radio" checked="checked">
 								<%}%>
					</div>
					<br>
					<br>
					<br>
					<center>
						<input class="btn" type="submit" name="submit" value="Save Changes" />
					</center>
			</form>
			<br>
			<br>
			<%if(size.equals("1")){%>

				<p>You cannot delete your last schedule. You must have at least one schedule for the GroBot to run.</p>

			<% }else{ %>	
				<form action="/deleteSchedule" method="post" >
					<input type="hidden" name="schedID" value="${fn:escapeXml(schedID)}"/>
						<center>
								<input class="btn" type="submit" name="submit" value="Delete Schedule" />
						</center>
				</form>
			<%}%>
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