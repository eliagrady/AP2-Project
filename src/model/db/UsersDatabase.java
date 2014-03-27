package model.db;



import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Collection;

import model.AbstractPrototype;
import model.db.errors.LoginException;
import model.types.UserInfo;
import model.users.Client;
import model.users.Employee;
import model.users.Manager;
import model.users.SysAdmin;
import model.users.User;
import api.actions.ClientActions;
import api.actions.EmployeeActions;
import api.types.BasicUserInfo;
import api.types.BillingUserInfo;

/**
 * Represents a database for user management
 * @author Elia
 *
 */
public class UsersDatabase extends AbstractPrototype implements Serializable {
	/**
	 * a serial key 
	 */
	private static final long serialVersionUID = 3831742223829012990L;
	private DatabaseConnection database = new DatabaseConnection();
	public UsersDatabase() {
	}
	
	
	/**
	 * returns an actions object for the given user data.
	 * @param email email to check
	 * @param password password to check
	 * @return an actions object for the given user data. if user credentials are wrong, 
	 * returns null.
	 * @throws LoginException if wasn't able to login
	 */
	public User authenticateByEmail(String email, String password) throws LoginException {
		ResultSet res = null;
		if(email==null || email.isEmpty() || password == null || password.isEmpty()) {
			throw new LoginException("Please fill the login fields");
		}
		try {
			String query = MySqlQuery.getUsersByEmailAndPassword(email, password);
			res = database.executeQuery(query);
			
			if(!res.next()) {
				throw new LoginException("login failed with email: " + email);
			}
			String userRole = res.getString(MySqlSettings.USER_ROLE);
			UserInfo info = new UserInfo(userRole,
					res.getString(MySqlSettings.USER_NAME), 
					res.getString(MySqlSettings.USER_EMAIL), 
					res.getInt(MySqlSettings.USER_ID));
			
			if(userRole.equals(MySqlSettings.ROLE_SYSADMIN)) {
				return new SysAdmin(info);
			}
			else if(userRole.equals(MySqlSettings.ROLE_MANAGER)) {
				return new Manager(info);
			}
			else if(userRole.equals(MySqlSettings.ROLE_EMPLOYEE)) {
				return new Employee(info);
			}
			else if(userRole.equals(MySqlSettings.ROLE_CLIENT)) {
				return new Client(info);
			}
			else {
				throw new LoginException("no such user role: " + info.getRole());
			}
		}
		catch (SQLException e) {
			throw new LoginException("problem with the database, login failed");
		}
	}
	
	/**
	 * adds the user to the database.
	 * @param info user info, not regarding the id
	 * @return the new id for the user that was created, or -1 if there was an error
	 */
	public int addClient(BasicUserInfo info,String password,BillingUserInfo billingInfo) {
		try {
			database.executeUpdate(MySqlQuery.insertUser(info.getUsername(), info.getEmail(), password , MySqlSettings.ROLE_CLIENT));
			database.executeUpdate(
					MySqlQuery.insertClientBillingInformation(	info.getEmail(), 
																billingInfo.getBillingAddress(),
																String.valueOf(billingInfo.getCreditCard().getCreditCardNumber())));
			ResultSet result = database.executeQuery(MySqlQuery.getUsersByEmailAndPassword(info.getEmail(), password));
			if(!result.next()) throw new SQLException();
			return result.getInt(MySqlSettings.USER_ID);
		}
		catch (SQLException e) {
			return -1;
		}
	}
	
	/**
	 * adds the user to the database.
	 * @param info user info, not regarding the id
	 * @return the new id for the user that was created, or -1 if there was an error
	 */
	private int addUser(BasicUserInfo info, String password,String role) {
		try {
			database.executeUpdate(MySqlQuery.insertUser(info.getUsername(), info.getEmail(), password ,role));
			ResultSet result = database.executeQuery(MySqlQuery.getUsersByEmailAndPassword(info.getEmail(), password));
			if(!result.next()) throw new SQLException();
			return result.getInt(MySqlSettings.USER_ID);
		}
		catch (SQLException e) {
			return -1;
		}
	}
	
	/**
	 * adds the user to the database.
	 * @param info user info, not regarding the id
	 * @return the new id for the user that was created, or -1 if there was an error
	 */
	public int addManager(BasicUserInfo info, String password) {
		return addUser(info, password, MySqlSettings.ROLE_MANAGER);
	}
	
	/**
	 * adds the user to the database.
	 * @param info user info, not regarding the id
	 * @return the new id for the user that was created, or -1 if there was an error
	 */
	public int addEmployee(BasicUserInfo info, String password) {
		return addUser(info, password, MySqlSettings.ROLE_EMPLOYEE);
		
	}


	/**
	 * Get all employees
	 * @return a collection of the employees
	 */
	public Collection<EmployeeActions> getEmployees() {
		ArrayDeque<EmployeeActions> employees = new ArrayDeque<>();
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getEmployees());
			while(result.next()) {
				try {
					UserInfo basicEmployeeInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					EmployeeActions employee = new Employee(basicEmployeeInfo);
					employees.add(employee);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	/**
	 * Get all clients 
	 * @return a collection of all clients
	 */
	public Collection<ClientActions> getClients() {
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getClients());
			while(result.next()) {
				try {
					UserInfo basicClientInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					ClientActions client = new Client(basicClientInfo);
					clients.add(client);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
	
	/**
	 * Get clients that purchased over a given amount
	 * @param purchaseSum the amount to check
	 * @return a collection of clients purchased over the amount given
	 */
	public Collection<ClientActions> getClientsPurchasedOverSum(double purchaseSum) {
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			String query = MySqlQuery.getClientsPurchasedOverSum(purchaseSum);
			ResultSet result = database.executeQuery(query);
			while(result.next()) {
				try {
					UserInfo basicClientInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					ClientActions client = new Client(basicClientInfo);
					clients.add(client);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}


	/**
	 * Updates a contact info
	 * @param basicUserInfo the basic contact info
	 * @param billingInfo the billing contact info
	 * @param password the password to set
	 * @return true if successfully changed
	 */
	public boolean updateContactInfo(BasicUserInfo basicUserInfo, BillingUserInfo billingInfo, String password) {
		int rowsChanged = 0;
		try {
			String query = MySqlQuery.updateBasicUserInformation(
					basicUserInfo.getID(),
					basicUserInfo.getUsername(),
					basicUserInfo.getEmail(),
					password ,
					MySqlSettings.ROLE_CLIENT);
			rowsChanged += database.executeUpdate(query);
			query = MySqlQuery.updateBillingUserInformation(
					billingInfo.getBillingAddress(),
					billingInfo.getCreditCard().getCreditCardNumber(),
					basicUserInfo.getEmail());
			rowsChanged += database.executeUpdate(query);
			return rowsChanged>0?true:false;
		}
		catch (SQLException e) {
			return false;
		}
	}


	/**
	 * Get client by email address
	 * @param email the client's email address
	 * @return the client if found, or null if not found
	 */
	public ClientActions getClientByEmail(String email) {
		ClientActions client = null;
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getClientByEmail(email));
			while(result.next()) {
				try {
					UserInfo basicClientInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					client = new Client(basicClientInfo);				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	/**
	 * Get client by id
	 * @param email the client's id
	 * @return the client if found, or null if not found
	 */
	public ClientActions getClientByID(int userID) {
		ClientActions client = null;
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getClientByID(userID));
			while(result.next()) {
				try {
					UserInfo basicClientInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					client = new Client(basicClientInfo);				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	/**
	 * Get clients that purchased over a given amount
	 * @param purchaseSum the amount to check
	 * @return a collection of clients purchased over the amount given
	 */
	public Collection<ClientActions> getClientsPurchasedProduct(String productName) {
		Collection<ClientActions> clients = new ArrayDeque<>();
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getClientsPurchasedProduct(productName));
			while(result.next()) {
				try {
					UserInfo basicClientInfo = new UserInfo(
							result.getString(MySqlSettings.USER_ROLE),
							result.getString(MySqlSettings.USER_NAME),
							result.getString(MySqlSettings.USER_EMAIL),
							result.getInt(MySqlSettings.USER_ID));
					ClientActions client = new Client(basicClientInfo);
					clients.add(client);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}
}
