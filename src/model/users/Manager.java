package model.users;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Date;

import model.db.errors.ObjectCreationException;
import controller.Controller;

import api.actions.ClientActions;
import api.actions.EmployeeActions;
import api.actions.ManagerActions;
import api.types.BasicUserInfo;
import api.types.Order;

/**
 * This class represent a concrete Manager
 * A manger is also an employee. it can do manager,employee and client actions
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class Manager extends Employee implements ManagerActions,EmployeeActions,ClientActions {

	public Manager(BasicUserInfo info) {
		super(info);
	}

	/**
	 * Get all employees
	 * @return a collection of all employees
	 */
	@Override
	public Collection<EmployeeActions> getEmployees() {
		Controller controller = Controller.getInstance();
		Collection<EmployeeActions> employees = new ArrayDeque<>();
		try {
			employees = controller.getUserDatabase().getEmployees();
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return employees;
	}

	/**
	 * Gets all clients
	 * @return a collection of all clients
	 */
	@Override
	public Collection<ClientActions> getClients() {
		Controller controller = Controller.getInstance();
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			clients = controller.getUserDatabase().getClients();
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return clients;
	}

	/**
	 * Get clients that purchased over a given sum
	 * @param price the sum to search clients that ordered above it
	 * @return a collection of clients that ordered above this price
	 */
	@Override
	public Collection<ClientActions> getClientsOrderedAbovePrice(double price) {
		Controller controller = Controller.getInstance();
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			clients = controller.getUserDatabase().getClientsPurchasedOverSum(price);
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return clients;
	}

	/**
	 * Get a report by given date range
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return a collection of orders that was ordered in the time range
	 */
	@Override
	public Collection<Order> getReportByDate(Date startDate, Date endDate) {
		Controller controller = Controller.getInstance();
		Collection<Order> orders = new ArrayDeque<>();
		try {
			orders = controller.getOrdersDatabase().getOrdersByDate(startDate,endDate);
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * Get clients that purchased over a given sum
	 * @param price the sum to search clients that ordered above it
	 * @return a collection of clients that ordered above this price
	 */
	@Override
	public Collection<ClientActions> getClientsOrderedProduct(String productName) {
		Controller controller = Controller.getInstance();
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			clients = controller.getUserDatabase().getClientsPurchasedProduct(productName);
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return clients;
	}
}
