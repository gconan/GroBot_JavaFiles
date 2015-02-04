<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utBazaar.dao.UserDAO" %>
<%@ page import="utBazaar.entity.Meeting" %>
<%@ page import="utBazaar.entity.Posting" %>
<%@ page import="utBazaar.entity.User" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@ page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>Longhorn Bazaar</title>
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
			    var email=getCookie("email");
			    if (email=="") {
			        window.location.assign("/index.html")
			    }else{
			        
			    }
			}

			function attend(){
				alert("You are now going to the meeting");
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
						<a href="/index.html">
							<h1>Longhorn Bazaar</h1><br />
							<p>Helping Longhorns trade</p>
						</a>
					</div>
					<div id="menu">
						<ul>
							<li><a href="buy.jsp">Buy</a></li>
							<li><a href="sell.jsp">Sell</a></li>
							<li class="current_page_item"><a href="#">Activities</a></li>
							<li><a href="profile.jsp">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
							
						</ul>
					</div>
				</div>
				<%
				String em = (String) session.getAttribute("email");
		User us = UserDAO.INSTANCE.getUserByEmail(em);
		pageContext.setAttribute("name", us.getEmail().substring(0, em.length()-11));
	%>
				<div id="page">
				  <div id="content">
					<div class="post">
					  <span class="welcometext">Hello, ${fn:escapeXml(name)}, welcome to the Longhorn Bazaar Activity Page!</span>
					  <form action="/meetingSearch" method="post">
					    <input type="text" name="search" id="search-text" value="" spellcheck="true" />
					    <input type="submit" title="Search" value="Search" />
					  </form>
						<br><a href="newMeeting.jsp">Create A New Meeting</a><br><br>
					</div>
						
						
					
					<%
						if(session.getAttribute("email") == null){
							    response.sendRedirect("/index.html");
						}
						
						ArrayList<Meeting> searchResults = (ArrayList<Meeting>)UserDAO.INSTANCE.getAllMeetings();
						String email2 = (String) session.getAttribute("email");
						pageContext.setAttribute("email2",email2);
						pageContext.setAttribute("size",searchResults.size());
						if(searchResults!=null){
							for(Meeting meet: searchResults){
					
								pageContext.setAttribute("title", meet.getName());
								pageContext.setAttribute("desc", meet.getDescription());
								pageContext.setAttribute("location", meet.getLocation());
								pageContext.setAttribute("date", meet.getDate());
								pageContext.setAttribute("time", meet.getTime());
								pageContext.setAttribute("minutes", meet.getMinutes());
								pageContext.setAttribute("amorpm", meet.getAmOrPm());
								pageContext.setAttribute("month", meet.getMonth());
								pageContext.setAttribute("year", meet.getYear());
								pageContext.setAttribute("duration", meet.getDuration());
								pageContext.setAttribute("id", meet.getIdString());
								pageContext.setAttribute("numPpl", meet.getSize());
								pageContext.setAttribute("timeend", meet.getTimeEnd());
								pageContext.setAttribute("minutesend", meet.getMinutesEnd());
								pageContext.setAttribute("amorpmend", meet.getAmOrPmEnd());
								

					%>
					<div style="height:200px; overflow: hidden">
							<img style="float: left; margin: 0px 15px 15px 0px;" src="<%= meet.getImageKey()%>"; width="150" />
							<p>
								<h2>${fn:escapeXml(title)}</h2>
								<br>
								Date: ${fn:escapeXml(month)}
								${fn:escapeXml("/")}
								${fn:escapeXml(date)}
								${fn:escapeXml("/")}
								${fn:escapeXml(year)}
								 &nbsp;     
								Time: ${fn:escapeXml(time)}
								${fn:escapeXml(":")}
								${fn:escapeXml(minutes)}
								${fn:escapeXml(amorpm)}
								 &nbsp;      
								Ends: ${fn:escapeXml(timeend)}
								${fn:escapeXml(":")}
								${fn:escapeXml(minutesend)}
								${fn:escapeXml(amorpmend)}
								&nbsp;
								Number of People Attending: 
										<div style="width: 60%; margin-left: 165px">
											${fn:escapeXml(desc)}
										</div>
								<br>
								Location: ${fn:escapeXml(location)}
								<br>
								Description:
								<br>
								<div style="width: 60%; margin-left: 165px">
											${fn:escapeXml(desc)}
								</div>
								<br>
								
							</p>
							
						</div>
							<% if (meet.getOrganizer().equals((String) session.getAttribute("email"))){ %>
								<div style="float:right">
									<form action="/seeAttending" method="post">
										<input type="hidden" name="seeWho" value="${fn:escapeXml(id)}"/>
										<input class="lhbButton" type="submit" name="button" style="float: right;" value="Who's Going?" />
									</form>
									<br><br>
								</div>
							<% }else{ %>
								<div style="float:right">
									<form action="/goToAMeeting" method="post">
										<input type="hidden" name="attend" value="${fn:escapeXml(id)}"/>
										<input class="lhbButton" type="submit" name="button" valign="center" value="Attend!" onClick="attend()"/>
									</form>
									<br><br>
								</div>
								<br>
							
						<%
								}
								%><br><%

							}
						}else{
						
						}
					%> 
					<br>
					
				 </div>
				</div>
			</div>
			<div id="footer-content">
				<div id="footer">
					<p>still not sure what we are putting here </p>
				</div>
			</div>
		</div>
	</body>
</html>

