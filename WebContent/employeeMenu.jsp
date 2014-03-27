<%@page import="java.io.PrintWriter"%>
<%@page import="controller.Controller"%>
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
		EmployeeActions user = (EmployeeActions) session.getAttribute("user");
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
<title>Employee Menu</title>
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
			printer.write("<h1 class=\"title\">Employee Menu</h1>");
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
			

			printer.write("<br/>");

			printer.write("<h2>Employee Action for Client</h2>");

			printer.write("<br/>");
			printer.write("<form method=POST ACTION=\"SetCurrentUserServlet\">");
			printer.write("User email: <input type=\"text\" name=\"clientEmailToLoginAs\">");
			printer.write("<input type=\"submit\" value=\"Login As User\">");
			printer.write("</form>");
			printer.write("<div class=\"loggedAs\">");
			if(session.getAttribute("loggedAs")!= null) {
				User loggedAs = (User) session.getAttribute("loggedAs");
				int userID = loggedAs.getInfo().getID();
				String name = ((User) Controller.getInstance().getUserDatabase().getClientByID(userID)).getInfo().getUsername();
				if(!name.isEmpty()) {
					printer.write("logged as: "+ name);
				}				
			}
			printer.write("</div>");
			printer.write("<br/>");
			
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

			printer.write("<h2>Employee Actions</h2>");

			printer.write("<br/>");
			
			printer.write("<form method=POST ACTION=\"AddClientServlet\">");
			printer.write("User Name: <input type=\"text\" name=\"usernameToSet\"> <br>");
			printer.write("User email: <input type=\"text\" name=\"emailToSet\"> <br>");
			printer.write("User password: <input type=\"password\" name=\"passwordToSet\"> <br>");
			printer.write("User billing address: <input type=\"text\" name=\"billingAddressToSet\"> <br>");
			printer.write("User credit card: <input type=\"text\" name=\"creditCardToSet\"> <br>");
			printer.write("<input type=\"submit\" value=\"Add New Client\">");
			printer.write("</form>");

			printer.write("<br/>");
			
			printer.write("<form method=POST ACTION=\"AddProductServlet\">");
			printer.write("Product Name: <input type=\"text\" name=\"productNameToSet\"> <br>");
			printer.write("Product Price: <input type=\"text\" name=\"productPriceToSet\"> <br>");
			printer.write("<input type=\"submit\" value=\"Add New Product\">");
			printer.write("</form>");

			printer.write("<br/>");
			
			
			printer.write("<form method=POST ACTION=\"UpdateProductServlet\">");
			printer.write("Product Name: <input type=\"text\" name=\"productNameToSearch\"> <br>");
			printer.write("Product Price: <input type=\"text\" name=\"productPriceToSet\"> <br>");
			printer.write("<input type=\"submit\" value=\"Update Product Price\">");
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