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
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/GetProductByNameServlet")
public class GetProductByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProductByNameServlet() {
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
		//String email, password;
		//email = 
		//password = request.getParameter("password");
		
		try {
			User user = (User) request.getSession().getAttribute("user");			
			ClientActions client = (ClientActions) user;
			//Controller controller = Controller.getInstance();
			//controller.getDatabase();
			
			Product product = client.getProductByName(request.getParameter("productToSearch"));
			//sysAdmin.addEmployee(new model.types.UserInfo(role, name, email, id))
			request.getSession().setAttribute("lastAction", product);
			response.sendRedirect(Controller.getInstance().getMenu(user));			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("loginErrorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}

}
