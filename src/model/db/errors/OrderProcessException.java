package model.db.errors;

/**
 * Represent an order process exception 
 * @author Elia
 *
 */
public class OrderProcessException extends Exception {
	

	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -5295172879944639340L;

	public OrderProcessException(Exception cause) {
		super(cause);
	}

	public OrderProcessException(String message) {
		super(message);
	}


}
