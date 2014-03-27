package api.actions;

import api.types.BasicUserInfo;
import api.types.BillingUserInfo;
import api.types.Order;
import api.types.Product;

import java.util.Collection;

import model.db.errors.OrderProcessException;

/**
 * This interface represents all the actions a client can do
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public interface ClientActions extends InformationActions {


    //Client specific
	/**
	 * Get the orders this client has made
	 * @return a collection of the orders this client performed
	 */
    public Collection<Order> getOrders();
    
    /**
     * Updates the client contact information
     * @param basicUserInfo the basic contact info for the client
     * @param billingInfo the billing info for the client
     * @param password the new password to set
     * @return true if the update was successfully performed
     */
    public boolean updateContactInformation(BasicUserInfo basicUserInfo,BillingUserInfo billingInfo,String password);
    
    /**
     * Purchase a product
     * @param product the product to purchase
     * @return true if the order was processed correctly
     * @throws OrderProcessException if there was a problem processing the order
     */
    public boolean buyProduct(Product product) throws OrderProcessException;
}
