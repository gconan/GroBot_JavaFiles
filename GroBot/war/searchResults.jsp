<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="utBazaar.dao.UserDAO" %>
<%@ page import="utBazaar.entity.Item" %>
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
				ArrayList<Item> searchResults = (ArrayList<Item>)session.getAttribute("searchResults");
				if(searchResults.size()>=1){
					for(Item item: searchResults){
						pageContext.setAttribute("title", item.getName());
						pageContext.setAttribute("desc", item.getDescription());
						pageContext.setAttribute("price", item.getPrice());
						pageContext.setAttribute("id", item.getId());
			%>
			<div style="height:200px; overflow: hidden">
				<img style="float: left; margin: 0px 15px 15px 0px;" src="<%= item.getImageKey()%>"; width="150" />
				
					<br><h2>${fn:escapeXml(title)}</h2>
					<p>
					Asking Price: $${fn:escapeXml(price)}
					<br>${fn:escapeXml(desc)}
					<div style="float:right">
						<form action="/inquire" method="post">
							<input type="hidden" name="itemID" value="${fn:escapeXml(id)}"/>
							<input class="lhbButton" type="submit" name="button" align="right" value="Inquire Now!" />
						</form>
					</div>
				</p>
			</div><br>	
						
			<%
						}
				}else{
					%> <p>No Items Matched Your Search, Consider Broadening Your Search.<p> <%
				}
			%>
			</div>
			</div>
			<div id="footer-content" valign="bottom">
					<div id="footer">
						<p><a href="buy.jsp">Return To The Buy Page</a></p>
					</div>
				</div>
				
			
			</div>
			</div>
	</body>
</html>