package api.types;

import java.util.Date;
/**
 * This interface represents an order by a user
 * @author Elia
 *
 */
public interface Order {
	/**
	 * Get the userID that has ordered this product
	 * @return the userID for the buyer
	 */
	public int getClientID();
	
	/**
	 * Get the product ordered
	 * @return the product ordered
	 */
	public Product getProduct();
	
	/**
	 * Get the date this purchase was performed
	 * @return the date for this purchase
	 */
	public Date getOrderDate();
	
	/**
	 * Get the amount this order has cost
	 * @return the price for the order
	 */
	public double getOrderSum();
}
