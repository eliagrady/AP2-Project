package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

import api.actions.ClientActions;
import api.actions.EmployeeActions;
import api.types.Product;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductServlet() {
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

			//Controller controller = Controller.getInstance();
			//controller.getDatabase();
			ClientActions employee = (ClientActions) user;
			Product oldProduct = employee.getProductByName(request.getParameter("productNameToSearch"));
			Product newProduct = new model.types.Product(
					oldProduct.getProductID(),oldProduct.getProductName(),request.getParameter("productPriceToSet"));
			boolean result = ((EmployeeActions) employee).updateExistingProduct(newProduct, oldProduct);
			request.getSession().setAttribute("lastAction", result);

		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("login.jsp");
		}
		catch (NullPointerException e) {
			request.getSession().setAttribute("errorMessage", e.getMessage());
		}
		response.sendRedirect(Controller.getInstance().getMenu(user));
	}

}
