<%@page import="model.db.errors.LoginException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="controller.Controller"%>
<%@page import="view.View"%>
<%@page import="model.users.User"%>
<%@page import="com.AddEmployeeServlet"%>
<%@page import="com.AddManagerServlet"%>
<%@ page import="api.actions.*" language="java"
	contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<%
	boolean isValid = false;
	try {
		SysAdminActions user = (SysAdminActions) session.getAttribute("user");
		if(user != null) {
			isValid = true;
		}
		else {
			response.sendRedirect("index.jsp");
		}
	} 
	catch (ClassCastException e) {
		response.sendRedirect("index.jsp");
	}
%>
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
<title>System Administrator Menu</title>
</head>
<body>
	<%
		if (isValid) {
			response.setContentType("text/html");
			PrintWriter printer = response.getWriter();
			User user = (User)session.getAttribute("user");
	        printer.write(Controller.getInstance().getView().welcomeMessage(user));

			printer.write("<form method=POST  ACTION=\"LogoutServlet\">");
			printer.write("<input type=\"submit\" value=\"Logout\">");
			printer.write("</form>");	
	        	        
			printer.write("<div class=\"wrapper\">");
			printer.write("<header>");
			printer.write("<h1 class=\"title\">System Administrator Menu</h1>");
			printer.write("</header>");
			
			printer.write("<h3>Add new Manager</h3>");

			printer.write("<form method=POST ACTION=\"AddManagerServlet\">");
			printer.write("User name: <input type=\"text\" name=\"usernameToSet\"> <br>");
			printer.write("User email: <input type=\"text\" name=\"emailToSet\"> <br>");
			printer.write("Password: <input type=\"password\" name=\"passwordToSet\"> <br> ");
			printer.write("<input type=\"submit\" value=\"addManager\">");
			printer.write("</form>");

			printer.write("<br/>");

			printer.write("<h3>Add new Employee</h3>");

			printer.write("<form method=POST ACTION=\"AddEmployeeServlet\">");
			printer.write("User name: <input type=\"text\" name=\"usernameToSet\"> <br>");
			printer.write("User email: <input type=\"text\" name=\"emailToSet\"> <br>");
			printer.write("Password: <input type=\"password\" name=\"passwordToSet\"> <br> ");
			printer.write("<input type=\"submit\" value=\"addEmployee\">");
			printer.write("</form>");

			printer.write("<br/>");

			Object lastAction = session.getAttribute("lastAction");
			printer.write(Controller.getInstance().getView().getOutputFromLastAction(lastAction));
			Object errorMessage = session.getAttribute("errorMessage");
			printer.write(Controller.getInstance().getView().getOutputFromLastAction(errorMessage));
			printer.write("</div>");
		}
	%>
</body>
</html>