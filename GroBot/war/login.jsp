<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>User's Home Page</title>
	</head>
	<body>
		<%

		String userName = null;
		if(session.getAttribute("email") == null){
		    response.sendRedirect("/index.html");
		}
		else {
			userName = (String) session.getAttribute("email");
		}

		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies){
		    	if(cookie.getName().equals("email")) userName = cookie.getValue();
		    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
		else {
			sessionID = session.getId();
		}

		%>
	<h3>Hi <%=userName %>, Login successful.</h3>
	<br>
	<a href="<%=response.encodeURL("changePassword.jsp") %>">Change Password</a>
	<form action="<%=response.encodeURL("logout") %>" method="post">
		<input type="submit" value="Logout" />
	</form>
	</body>
</html>
