<%@ page import="controller.*" %>
<%@ page import="model.users.*" %>
<%@ page import="api.actions.*" language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>user navigation</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	response.sendRedirect(Controller.getInstance().getMenu(user));		
	%>
</body>
</html>