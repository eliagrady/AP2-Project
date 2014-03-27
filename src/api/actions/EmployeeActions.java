package api.actions;


import api.types.BasicUserInfo;
import api.types.BillingUserInfo;
import api.types.Product;

/**
 * This interface represents actions that an employee can do
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public interface EmployeeActions extends InformationActions {
	
	/**
	 * Opens a new client's account
	 * @param basicUserInfo the basic info for the new client's account
	 * @param billingInfo the billing info for the new client's account
	 * @param password the password for the client
	 * @return the user ID for the client, or -1 if there was an error
	 */
    public int openNewClientAccount(BasicUserInfo basicUserInfo, BillingUserInfo billingInfo,String password);
    
    /**
     * Add a new product to the database
     * @param newProduct the new product to add
     * @return the product ID that was added, or -1 if there was an error
     */
    public int addNewProduct(Product newProduct);
    
    /**
     * Updates an existing product
     * @param newProduct the new product to copy data from
     * @param oldProduct the old product to edit it's entries
     * @return true if the product was updated successfully
     */
    public boolean updateExistingProduct(Product newProduct, Product oldProduct);
    
    /**
     * Get the currently controlled client
     * @return an interface for the client's actions
     */
    public ClientActions getControlledClient();
    
    /**
     * Set the currently controlled client
     * @param clientInterface the client interface to set
     */
    public void setControlledClient(ClientActions clientInterface);
}
