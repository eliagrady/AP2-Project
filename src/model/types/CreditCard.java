package model.types;

/**
 * This class represent a 'simple' credit card
 * @author Elia
 *
 */
public class CreditCard implements api.types.CreditCard {
	
	private int creditCardNumber;
	
	/**
	 * Create a new credit card
	 * @param creditCardNumber the new credit card number
	 */
	public CreditCard(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * Create a credit card based on a string
	 * @param creditCardNumberAsString
	 */
	public CreditCard(String creditCardNumberAsString) {
		try{
			this.creditCardNumber = Integer.parseInt(creditCardNumberAsString);	
		}
		catch(NumberFormatException e) {
			//unable to create credit card
		}
		
	}

	/**
	 * Gets the credit card number
	 */
	@Override
	public int getCreditCardNumber() {
		return creditCardNumber;
	}

}
