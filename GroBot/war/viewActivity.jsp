<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utBazaar.entity.Meeting" %>
<%@ page import="utBazaar.dao.UserDAO" %>
<%@ page import="utBazaar.entity.Posting" %>
<%@ page import="utBazaar.entity.User" %>
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
							<h1>Longhorn Bazaar</h1><br>
							<p>Helping Longhorns trade</p>
						</a>
					</div>
					<div id="menu">
						<ul>
							<li><a href="buy.jsp">Buy</a></li>
							<li><a href="sell.jsp">Sell</a></li>
							<li><a href="activities.jsp">Activities</a></li>
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
					<%
						ArrayList<String> listAttending = (ArrayList<String>)session.getAttribute("VIPList");
						Meeting meet = (Meeting)session.getAttribute("meeting");
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

							if(listAttending!=null){
							%>
							<div class="post">
					  <span class="welcometext">Hello, ${fn:escapeXml(name)}, here is your activity!</span>
						<br><br><br>
					</div>
									<br>
									<div style="height:150px">
										
										<p>
											<center>
											<img style="float: left; margin: 0px 15px 15px 0px;" src="<%= meet.getImageKey()%>"; width="150" />
											<img style="float: right; margin: 0px 15px 15px 0px;" src="<%= meet.getImageKey()%>"; width="150" />
												<h2>${fn:escapeXml(title)}</h2>
												<br>
												Time: ${fn:escapeXml(time)}
												${fn:escapeXml(":")}
												${fn:escapeXml(minutes)}
												${fn:escapeXml(amorpm)}
												<br>
												Date: ${fn:escapeXml(month)}
												${fn:escapeXml("/")}
												${fn:escapeXml(date)}
												${fn:escapeXml("/")}
												${fn:escapeXml(year)}
												<br>
												Location: ${fn:escapeXml(location)}
												<br>
												Description: ${fn:escapeXml(desc)}
												<br>
												
											<br>
											<h3>Here's whos's attending:</h3>
											<%for(String user: listAttending){
												pageContext.setAttribute("user", user.substring(0, user.length()-11));
											%>
											${fn:escapeXml(user)}
											<br>
											<%}%></center>
										</p>
										<br>
									</div>	
									<br>
					<%	
							}
					%> 
					</div>
				</div>
			</div>

			<div id="footer-content" valign="bottom">
				<div id="footer">
					<p><a href="activities.jsp">Return To Meetings</a></p>
				</div>
			</div>

		</div>
		<!-- end #footer -->
	</body>
</html>
