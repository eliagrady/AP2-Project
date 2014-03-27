package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.ManagerActions;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/GetClientsPurchasedOverPriceServlet")
public class GetClientsPurchasedOverPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClientsPurchasedOverPriceServlet() {
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
			ManagerActions manager = (ManagerActions) user;
			Object orders = null;
			try{
				orders = manager.getClientsOrderedAbovePrice((Double.parseDouble(request.getParameter("productPriceToSearch"))));
			}
			catch(NumberFormatException e) {
				request.getSession().setAttribute("errorMessage", "Please enter price as a number ");				
			}
			request.getSession().setAttribute("lastAction", orders);
			response.sendRedirect(Controller.getInstance().getMenu(user));
			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}

}
