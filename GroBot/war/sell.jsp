<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utBazaar.dao.UserDAO" %>
<%@ page import="utBazaar.entity.Item" %>
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
							<li class="current_page_item"><a href="#">Sell</a></li>
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
					<div class="post">
					  <span class="welcometext">Hello, ${fn:escapeXml(name)}, welcome to the Longhorn Bazaar Sell Page!</span>
					  <form action="/sellSearch" method="post">
					    <input type="text" name="search" id="search-text" value="" spellcheck="true" />
					    <input type="submit" title="Search" value="Search" />
					  </form>
						<br><a href="newItem.jsp">Sell A New Item!</a><br><br>
					</div>
						
						
					<div id="wrapper" >

						<%
					
							if(session.getAttribute("email") == null){
							    response.sendRedirect("/index.html");
							}
							String email = (String) session.getAttribute("email");
							ArrayList<Item> searchResults = (ArrayList<Item>)UserDAO.INSTANCE.getUserItems(email);
							if(searchResults.size()>=1){
								for(Item item: searchResults){
									pageContext.setAttribute("title", item.getName());
									pageContext.setAttribute("desc", item.getDescription());
									pageContext.setAttribute("price", item.getPrice());
									pageContext.setAttribute("id", item.getId());
						%>
							<div style="height:200px; overflow: hidden">
								<img style="float: left; margin: 0px 15px 15px 0px;" src="<%= item.getImageKey()%>"; width="150" />
								<p>
									<h2>${fn:escapeXml(title)}</h2>
									Asking Price: $${fn:escapeXml(price)}
									<br>Description: 
										<div style="width: 60%; margin-left: 165px">
											${fn:escapeXml(desc)}
										</div>
									<div style="float:right">
										<form action="/delete" method="post">
											<input type="hidden" name="itemID" value="${fn:escapeXml(id)}"/>
											<input class="lhbButton" type="submit" name="button" align="right" value="Delete Item" />
										</form>
									</div>
								</p>
							</div>
							<br>
						<%
								}
							}else{
							}
							
						%>
						</div>
					</div>
				</div>
			</div>
			<div id="footer-content">
				<div id="footer">
					<p>still not sure what we are putting here </p>
				</div>
			</div>
		</div>
				<!-- end #footer -->
	</body>
</html>
