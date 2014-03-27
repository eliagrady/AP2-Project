package model.users;

import api.actions.ClientActions;
import api.types.BasicUserInfo;
import api.types.BillingUserInfo;
import api.types.Order;
import api.types.Product;

import java.util.ArrayDeque;
import java.util.Collection;

import controller.Controller;

import model.db.errors.ObjectCreationException;
import model.db.errors.OrderProcessException;

/**
 * model.userManagement
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */

/**
 * This class represent a concrete client
 * A client is also a user and can do ClientActions
 * @author Elia
 *
 */
public class Client extends User implements ClientActions {
	private BillingUserInfo billingUserInfo;

	/**
	 * Create a new client
	 * @param userInfo the client user info
	 * @param billingInfo the client billing info
	 */
	public Client(BasicUserInfo userInfo,BillingUserInfo billingInfo) {
		super(userInfo);
		this.billingUserInfo = billingInfo;		
	}
	
	/**
	 * Create a new client
	 * @param userInfo the client user info
	 */
	public Client(BasicUserInfo userInfo) {
		super(userInfo);
	}

	/**
	 * Get basic user info
	 * @return the user info
	 */
	public BasicUserInfo getBasicUserInfo() {
		return super.getInfo();
	}

	/**
	 * Set basic user info
	 * @param basicUserInfo info to set
	 */
	public void setBasicUserInfo(BasicUserInfo basicUserInfo) {
		super.info = basicUserInfo;
	}


	/**
	 * Get billing user info
	 * @return billing user info
	 */
	public BillingUserInfo getBillingUserInfo() {
		return billingUserInfo;
	}


	/**
	 * Set billing info
	 * @param billingUserInfo info to set
	 */
	public void setBillingUserInfo(BillingUserInfo billingUserInfo) {
		this.billingUserInfo = billingUserInfo;
	}


	/**
	 * Get the collection of orders this client made
	 * @return a collection of orders this client made
	 */
	@Override
	public Collection<Order> getOrders() {
		Controller controller = Controller.getInstance();
		Collection<Order> orders = new ArrayDeque<>();
		try {
			orders = controller.getOrdersDatabase().getOrdersByClient(getInfo().getID());
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return orders;
	}


	/**
	 * Updates the contact information
	 * @return true if the information was updated successfully
	 */
	@Override
	public boolean updateContactInformation(BasicUserInfo basicUserInfo,
			BillingUserInfo billingInfo, String password) {
		Controller controller = Controller.getInstance();
		try {
			//The query will return the number of rows affected by the query.
			//If it's more than 0, then the order was processed.
			if(controller.getUserDatabase().updateContactInfo(basicUserInfo, billingInfo, password)) {
				return true;
			}
		}
		catch(ObjectCreationException e) {
			e.printStackTrace();
		}
		return false;	
	}



	/**
	 * purchase a product
	 * @return true if the order successfully processed
	 */
	@Override
	public boolean buyProduct(Product product) throws OrderProcessException {
		Controller controller = Controller.getInstance();
		try {
			//The query will return the number of rows affected by the query.
			//If it's more than 0, then the order was processed.
			if(controller.getOrdersDatabase().orderProduct(product, getInfo().getID())) {
				return true;
			}
		}
		catch(OrderProcessException | ObjectCreationException e) {
			throw new OrderProcessException(e);
		}
		return false;		
	}


	/**
	 * Get all products
	 * @return a collection of all products
	 */
	@Override
	public Collection<Product> getAllProducts() {
		Controller controller = Controller.getInstance();
		try {
			return controller.getProductDatabase().getAllProducts();
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * Get product with a given name
	 * @return a product if found, or null otherwise
	 */
	@Override
	public Product getProductByName(String productName) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getProductDatabase().getProductByName(productName);
		} catch (ObjectCreationException e) {			
			e.printStackTrace();
		}
		return null;
	}
}
