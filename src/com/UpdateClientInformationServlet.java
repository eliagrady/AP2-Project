package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.ClientActions;
import model.db.MySqlSettings;
import model.types.BillingInfo;
import model.types.CreditCard;
import model.types.UserInfo;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/UpdateClientInformationServlet")
public class UpdateClientInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClientInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");			
			
			ClientActions client = (ClientActions) user;
			
			UserInfo contactInfo = new UserInfo(
					MySqlSettings.ROLE_CLIENT,
					request.getParameter("usernameToSet"),
					request.getParameter("emailToSet"),
					user.getInfo().getID());
			BillingInfo billingInfo = new BillingInfo(new CreditCard(
					request.getParameter("creditCardToSet")),
					request.getParameter("billingAddressToSet"));
			boolean result = client.updateContactInformation(
					contactInfo,
					billingInfo,
					request.getParameter("passwordToSet"));
			request.getSession().setAttribute("lastAction", result);
			response.sendRedirect(Controller.getInstance().getMenu(user));
			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}

}
