<%@page import="controller.Controller"%>
<%@page import="java.util.ArrayList"%>
<%@page import="view.View"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="corehtml/assets/styles/style.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="corehtml/assets/styles/orbit-1.2.3.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="corehtml/assets/styles/jquery.fancybox-1.3.4.css" media="screen" />
<!--[if lt IE 9]><script type="text/javascript" src="corehtml/assets/scripts/modernizr.js"></script><![endif]-->
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Login page</title>
</head>
<body>
<body>
	<%
		session.removeAttribute("user");
	%>
	<div class="wrapper">
	<header>
	<h1 class="title">Login</h1>
	</header>
	<form method=POST ACTION="LoginServlet">
		<label class="element">Email:</label><input class="input" type="email" name="email"> 
		<label class="element">Password:</label><input class="input" type="password" name="password">
		<input class="input" type="submit" value="Login">
	</form>
	<br />
	<br />
	<div class="errorMessage element"><%=session.getAttribute("errorMessage")==null?"":session.getAttribute("errorMessage")%></div>	
	<br />
	<%
	session.removeAttribute("errorMessage");
	%>
	<br />
	</div>
</body>
</html>
