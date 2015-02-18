<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Change Password</title>
  </head>
  <body>
    <%
      String email = null;
      if(session.getAttribute("email") == null){
          response.sendRedirect("/index.html");
      }
      else {
      	email = (String) session.getAttribute("email");
      }

      String sessionID = null;
      Cookie[] cookies = request.getCookies();
      if(cookies != null){
      	for(Cookie cookie : cookies){
          	if(cookie.getName().equals("email")) email = cookie.getValue();
          	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
      	}
      }
      else {
      	sessionID = session.getId();
      }
    %>
    <br>
    <form action="/changep" method="post">
       <input type="text" name="currPassw" id="currPassw" /><br>
       <input type="text" name="newPassw" id="newPassw" /><br>
       <input type="text" name="verifNewPassw" id="currPassw" /><br>
  	   <input type="submit" name="submit" id="submit" value="Change" />
    </form>

    <form action="/logout" method="post">
      <input type="submit" value="Logout" />
    </form>
  </body>
</html>
