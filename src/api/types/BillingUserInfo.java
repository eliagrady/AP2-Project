package api.types;


/**
 * This interface represents Billing information, such as address and payment methods
 * ID : 300907060
 * Username:  gradyel
 */
public interface BillingUserInfo {
	
	/**
	 * Get the billing address for the client
	 * @return the billing address for the client
	 */
    public String getBillingAddress();
    
    /**
     * Get the credit card 
     * @return the credit card
     */
    public CreditCard getCreditCard();
}
