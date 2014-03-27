package api.types;

/**
 * An interface for a credit card
 * ID : 300907060
 * Username:  gradyel
 */
public interface CreditCard {
	/**
	 * Get the credit card number
	 * @return the number of the credit card
	 */
    public int getCreditCardNumber();

    //Can be used, however in this project it is not required
//    public void setCreditCardExpirationDate(int month,int year);
//    public void setCreditCardSecurityPin(int creditCardSecurityPin);
//    public void setCreditCardHolderName(String creditCardHolderName);
//    public void setCreditCardHolderBillingAddress(String creditCardHolderBillingAddress);

}
