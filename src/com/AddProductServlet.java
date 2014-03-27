package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.EmployeeActions;
import model.types.Product;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
			Product product = new Product(-1,request.getParameter("productNameToSet"),request.getParameter("productPriceToSet"));
			int productID = employee.addNewProduct(product);
			request.getSession().setAttribute("lastAction", productID);			
		}
		catch (ClassCastException | NumberFormatException e) {
			request.getSession().setAttribute("errorMessage", e.getMessage());
		}

		response.sendRedirect(Controller.getInstance().getMenu(user));
	}

}
