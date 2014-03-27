package model.users;

import java.util.Collection;

import model.db.errors.ObjectCreationException;
import model.db.errors.OrderProcessException;
import model.users.errors.NotLoggedAsClientException;
import controller.Controller;

import api.actions.ClientActions;
import api.actions.EmployeeActions;
import api.types.BasicUserInfo;
import api.types.Order;
import api.types.Product;
import api.types.BillingUserInfo;

/**
 * This class represents an Employee that is also a client and can do both 
 * client actions and employee actions
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class Employee extends Client implements EmployeeActions,ClientActions {
	private ClientActions loggedAsClient;	

	public Employee(BasicUserInfo info) {
		super(info);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Get the controlled client
	 * @return the interface for the controlled clients
	 */
	@Override
	public ClientActions getControlledClient() {
		return this.loggedAsClient;
	}

	/**
	 * Set the controlled client given it's interface
	 * @param otherClient client to control
	 */
	@Override
	public void setControlledClient(ClientActions otherClient) {
		this.loggedAsClient = otherClient;
	}

	/**
	 * Adds a new product
	 * @param newProduct the product to add
	 * @return the product ID added, or -1 if there was an error adding it
	 */
	@Override
	public int addNewProduct(Product newProduct) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getProductDatabase().addProduct(newProduct);
		} catch (ObjectCreationException e) {

			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * Updates existing product
	 * @param newProduct the new product to get info from
	 * @param oldProduct the old product to copy info to
	 * @return true if the product was updated successfully
	 */
	@Override
	public boolean updateExistingProduct(Product newProduct, Product oldProduct) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getProductDatabase().updateExistingProduct(newProduct,oldProduct);
		} catch (ObjectCreationException e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * opens a new client account
	 * @param basicUserInfo the basic user info 
	 * @param billingInfo the billing info
	 * @param password the password
	 * @return the new client's ID or -1 if there was an error creating
	 */
	@Override
	public int openNewClientAccount(BasicUserInfo basicUserInfo, BillingUserInfo billingInfo, String password) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getUserDatabase().addClient(basicUserInfo, password, billingInfo);
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return -1;		
	}


	/**
	 * Gets all orders for given controlled client
	 * @return a collection of orders for the controlled client or null if not connected to a client
	 */
	@Override
	public Collection<Order> getOrders() {
		if(loggedAsClient == null) { 
			try {
				throw new NotLoggedAsClientException();
			} catch (NotLoggedAsClientException e) {
				e.printStackTrace();
				return null;
			}
		}
		return loggedAsClient.getOrders();
	}


	/**
	 * Updates client information
	 * @param basicUserInfo basic info
	 * @param billingInfo billing info
	 * @param password password
	 * @return true if updated successfully
	 */
	@Override
	public boolean updateContactInformation(BasicUserInfo basicUserInfo,
			BillingUserInfo billingInfo, String password) {
		if(loggedAsClient == null) { 
			try {
				throw new NotLoggedAsClientException();
			} catch (NotLoggedAsClientException e) {
				e.printStackTrace();
				return false;
			}
		}
		return loggedAsClient.updateContactInformation(basicUserInfo, billingInfo, password);
	}


	/**
	 * Buy a product for the client
	 * @param product product to buy
	 * @return true if purchased successfully
	 */
	@Override
	public boolean buyProduct(Product product) throws OrderProcessException {
		if(loggedAsClient == null) { 
			try {
				throw new NotLoggedAsClientException();
			} catch (NotLoggedAsClientException e) {
				e.printStackTrace();
				return false;
			}
		}
		return loggedAsClient.buyProduct(product);
	}
}
