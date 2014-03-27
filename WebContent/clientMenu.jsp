<%@page import="controller.Controller"%>
<%@page import="model.users.User"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.AddEmployeeServlet"%>
<%@page import="com.AddManagerServlet"%>
<%@ page import="api.actions.*" language="java"
	contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<%
	boolean isValid = false;
	try {
		ClientActions user = (ClientActions) session.getAttribute("user");
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
<title>Client Menu</title>
</head>
<body>
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
			printer.write("<h1 class=\"title\">Client Menu</h1>");
			printer.write("</header");

			printer.write("<form method=POST ACTION=\"GetProductsServlet\">");
			//printer.write("<input type=\"submit\" value=\"Get Products\">");
			printer.write("</form>");	
			
			printer.write("<form method=POST ACTION=\"GetProductsServlet\">");
			printer.write("<input type=\"submit\" value=\"Get Products\">");
			printer.write("</form>");	

			printer.write("<form method=POST ACTION=\"GetProductByNameServlet\">");
			printer.write("Product Name: <input type=\"text\" name=\"productToSearch\">");
			printer.write("<input type=\"submit\" value=\"Search\">");
			printer.write("</form>");
			
			printer.write("<form method=POST ACTION=\"GetClientOrdersServlet\">");
			printer.write("<input type=\"submit\" value=\"Get Orders\">");
			printer.write("</form>");
			
			printer.write("<form method=POST ACTION=\"BuyProductServlet\">");
			printer.write("Product Name: <input type=\"text\" name=\"productToBuy\">");			
			printer.write("<input type=\"submit\" value=\"Buy Product\">");
			printer.write("</form>");
			
			
			printer.write("<form method=POST ACTION=\"UpdateClientInformationServlet\">");
			printer.write("User Name: <input type=\"text\" name=\"usernameToSet\"> <br>");
			printer.write("User email: <input type=\"text\" name=\"emailToSet\"> <br>");
			printer.write("User password: <input type=\"password\" name=\"passwordToSet\"> <br>");
			printer.write("User billing address: <input type=\"text\" name=\"billingAddressToSet\"> <br>");
			printer.write("User credit card: <input type=\"text\" name=\"creditCardToSet\"> <br>");
			printer.write("<input type=\"submit\" value=\"Update User Information\">");
			printer.write("</form>");
			
			printer.write("<br/>");

			Object lastAction = session.getAttribute("lastAction");
			printer.write(Controller.getInstance().getView().getOutputFromLastAction(lastAction));
			Object errorMessage = session.getAttribute("errorMessage");
			printer.write(Controller.getInstance().getView().getOutputFromLastAction(errorMessage));
			printer.write("</div");
		}
	%>
</body>
</html>