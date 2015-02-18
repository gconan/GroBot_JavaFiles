<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="groBot.entity.User" %>
<%@ page import="groBot.dao.UserDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>User's Home Page</title>
	</head>
	<body>
		<%
			String userName = null;
			if(session.getAttribute("GroBotEmail") == null){
			    response.sendRedirect("/index.html");
			}
			else {
				userName = (String) session.getAttribute("GroBotEmail");
				User us = UserDAO.INSTANCE.getUserByEmail(userName);
				pageContext.setAttribute("name", us.getFirstName());
			}

			String sessionID = null;
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies){
			    	if(cookie.getName().equals("GroBotEmail")) userName = cookie.getValue();
			    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
				}
			}else {
				sessionID = session.getId();
			}
		%>
		<h3>Hi ${fn:escapeXml(name)}, Login successful.</h3>
		<br>
		<a href="/changePassword.jsp">Change Password</a>
		<form action="/logout" method="post">
			<input type="submit" value="Logout" />
		</form>
	</body>
</html>
