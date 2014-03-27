package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.ClientActions;
import api.types.Product;
import model.db.errors.OrderProcessException;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/BuyProductServlet")
public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductServlet() {
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
			
			boolean orderSuccess = false;
			try {
				Product product = client.getProductByName(request.getParameter("productToBuy"));
				if(product == null) {
					throw new OrderProcessException("product not found");
				}
				orderSuccess = client.buyProduct(product);
			} 
			catch (OrderProcessException e) {
				request.getSession().setAttribute("errorMessage", "Purhcase Error: " + e.getMessage());
			}
			
			request.getSession().setAttribute("lastAction", orderSuccess);
			response.sendRedirect(Controller.getInstance().getMenu(user));
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}

}
