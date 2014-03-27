package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.actions.SysAdminActions;

import model.db.MySqlSettings;
import model.types.UserInfo;
import model.users.User;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet("/AddManagerServlet")
public class AddManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddManagerServlet() {
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
			SysAdminActions sysAdmin = (SysAdminActions) user;
			//Controller controller = Controller.getInstance();
			//controller.getDatabase();
			
			UserInfo contactInfo = new UserInfo(MySqlSettings.ROLE_MANAGER, request.getParameter("usernameToSet"), request.getParameter("emailToSet"), -1);
			int userID = sysAdmin.addManager(contactInfo,request.getParameter("passwordToSet"));
			//sysAdmin.addEmployee(new model.types.UserInfo(role, name, email, id))
			request.getSession().setAttribute("lastAction", userID);
			response.sendRedirect("sysAdminMenu.jsp");
			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}

}
