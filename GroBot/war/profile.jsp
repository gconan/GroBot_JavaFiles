<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="groBot.entity.User" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
																					
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<title>Longhorn Bazaar</title>
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
						<a href="index.html">
							<h1>Longhorn Bazaar</h1><br/>
							<p>Helping Longhorns trade</p>
						</a>
					</div>
					<div id="menu">
						<ul>
							<li><a href="buy.jsp">Buy</a></li>
							<li><a href="sell.jsp">Sell</a></li>
							<li><a href="activities.jsp">Activities</a></li>
							<li class="current_page_item"><a href="#">Profile</a></li>
							<li><a href="/logout">Logout</a></li>
							
						</ul>
					</div>
				</div>
				<div id="page">
					<div id="content">
						<br>
						<div style="height:650px; overflow: hidden">
							<div>
								<p>
									<div>
									<img style="float: left; margin: 0px 15px 15px 0px; height: 150px; overflow: hidden;" width="150" />
									<img style="float: right; margin: 0px 15px 15px 0px; height: 150px; overflow: hidden;" width="150" />
									</div>
									<br>
									<center>
										<h2>name</h2>
										<br>
									</center>
									<p>
										<div style="margin: 120px 15px 15px 0px;"><br>Want to change your password?
											<form action="/changep" method="post">
												Current Password: <input type="text" name="currPassw" id="currPassw" /><br>
												New Password: <input type="text" name="newPassw" id="newPassw" /><br>
												Verify New Password: <input type="text" name="verifNewPassw" id="currPassw" /><br>
												<input type="submit" name="submit" id="submit" value="Change" />
											</form>
											<br>
											<form action="/deleteAccount" method="post">
												<input type="submit" name="delete" id="delete" value="Delete Account" />
											</form>
											<br>
										</div>
									</p>
								
								</p>
								<br>
							</div>	
							<br>
						</div>
					</div>
				</div> 
			</div>
			<div id="footer-content" valign="bottom">
				<div id="footer">
					<p>still not sure what we are putting here </p> <!-- end #footer -->
				</div>
			</div>
		</div>
	</body>
</html>

