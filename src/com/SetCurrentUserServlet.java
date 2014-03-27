package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.EmployeeActions;
import model.db.errors.ObjectCreationException;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/SetCurrentUserServlet")
public class SetCurrentUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCurrentUserServlet() {
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
		User user = null;
		try {
			user = (User) request.getSession().getAttribute("user");			
			
			EmployeeActions employee = (EmployeeActions) user;
			String email = request.getParameter("clientEmailToLoginAs");
			if(email != null && !email.isEmpty()) {
				employee.setControlledClient(Controller.getInstance().getUserDatabase().getClientByEmail(email));				
			}			
			if(employee.getControlledClient()!=null) {
				request.getSession().setAttribute("loggedAs", employee.getControlledClient());
				request.getSession().setAttribute("lastAction", true);
			}
			else {
				request.getSession().setAttribute("lastAction", false);
			}
			response.sendRedirect(Controller.getInstance().getMenu(user));			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
		catch (ObjectCreationException e) {
			request.getSession().setAttribute("errorMessage",e.getMessage());
			response.sendRedirect(Controller.getInstance().getMenu(user));			
		}
	}

}
