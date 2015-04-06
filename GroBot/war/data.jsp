<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String lightOn = (String)session.getAttribute("lightOn");
		String lightOff = (String)session.getAttribute("lightOff");
		String lightPins = (String)session.getAttribute("lightPins");
		String waterDur = (String)session.getAttribute("waterDur");
		String waterPer = (String)session.getAttribute("waterPer");
		String aux = (String)session.getAttribute("aux");
		String air = (String)session.getAttribute("air");
	%>
	<body>
		${fn:escapeXml(lightOn)}
		${fn:escapeXml(lightOff)}
		${fn:escapeXml(lightPins)}
		${fn:escapeXml(waterDur)}
		${fn:escapeXml(waterPer)}
		${fn:escapeXml(aux)}
		${fn:escapeXml(air)}
	</body>
</html>