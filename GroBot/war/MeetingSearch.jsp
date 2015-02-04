<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utBazaar.entity.Meeting" %>
<%@ page import="utBazaar.entity.Posting" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
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
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>Item Search Results</title>
	</head>
	<body>
		<script>
			checkCookie();
		</script>
		<div id="wrapper">
			<div id="wrapper2">
				<div id="header" class="container">
					<div id="logo">
						<a href="index.html">
							<h1>Longhorn Bazaar</h1><br />
							<p>Helping Longhorns trade</p>
						</a>
					</div>
				</div>
				<div id="page">
				  <div id="content">
						<div class="post">
						  <span class="welcometext">Your Search Results!</span>
					
						</div>
					<%
						if(session.getAttribute("email") == null){
							    response.sendRedirect("/index.html");
						}
						ArrayList<Meeting> searchResults = (ArrayList<Meeting>)session.getAttribute("meetings");
						if(searchResults.size()>=1){
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
								<br>
								Time: ${fn:escapeXml(time)}
								${fn:escapeXml(":")}
								${fn:escapeXml(minutes)}
								${fn:escapeXml(amorpm)}
								<br>
								Number of People Attending: ${fn:escapeXml(numPpl)}
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
									<br>
								</div>
							<% }else{ %>
								<div style="float:right">
									<form action="/goToAMeeting" method="post">
										<input type="hidden" name="attend" value="${fn:escapeXml(id)}"/>
										<input class="lhbButton" type="submit" name="button" valign="center" value="Attend!" />
									</form>
									<br><br>
								</div>
								<br>
							
						<%
								}
								%><br><%

							}
						}else{
							%> <p>No Items Matched Your Search, Consider Broadening Your Search.<p> <%
						}
					%> 
					</div>
			</div>
			<div id="footer-content" valign="bottom">
					<div id="footer">
						<p><a href="activities.jsp">Return To The Activities Page</a></p>
					</div>
				</div>
				
			
		</div>
	</div>
	</body>
</html>