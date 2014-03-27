package model.types;

import api.types.BillingUserInfo;
import api.types.CreditCard;
import model.AbstractPrototype;
/**
 * Represents billing information such as billing address and payment method
 * @author Elia
 *
 */
public class BillingInfo extends AbstractPrototype implements BillingUserInfo{
	private CreditCard creditCard;
	private String billingAddress;


	/**
	 * Construct a new billing info object
	 * @param creditCard the credit card to set
	 * @param billingAddress the billing address to set
	 */
	public BillingInfo(CreditCard creditCard, String billingAddress) {
		this.creditCard = creditCard;
		this.billingAddress = billingAddress;
	}
	
	/**
	 * Construct a new billing info object
	 * @param creditCardNumber the credit card number to set
	 * @param billingAddress the billing address to set
	 */
	public BillingInfo(int creditCardNumber, String billingAddress) {
		this.creditCard = new model.types.CreditCard(creditCardNumber);
		this.billingAddress = billingAddress;		
	}

	/**
	 * Default constructor
	 */
    public BillingInfo() {
    }

    /**
     * Get the billing address
     */
	@Override
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Get the credit card
	 */
	@Override
	public CreditCard getCreditCard() {
		return creditCard;
	}

}
