package com;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
@WebServlet("/GetReportForMonthServlet")
public class GetReportForMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReportForMonthServlet() {
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
			ManagerActions manager = (ManagerActions) user;
			
			Date startDate,endDate;
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("monthToSet"));			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			startDate = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();			
			Object orders = manager.getReportByDate(startDate,endDate);
			request.getSession().setAttribute("lastAction", orders);			
		}
		catch (ClassCastException e) {
			request.getSession().setAttribute("errorMessage", "Login Error: " + e.getMessage());
			response.sendRedirect("login.jsp");
		} 
		catch (ParseException e) {
			request.getSession().setAttribute("errorMessage", "Invalid date: " + e.getMessage());
		}
		response.sendRedirect(Controller.getInstance().getMenu(user));
	}

}
