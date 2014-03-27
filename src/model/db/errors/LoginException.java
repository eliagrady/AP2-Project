package model.db.errors;

/**
 * Represent an exception that occur when a login attempt fails
 * @author Elia
 *
 */
public class LoginException extends Exception {

	/**
	 * a serial key
	 */
	private static final long serialVersionUID = 1L;

	public LoginException(String message) {
		super(message);
	}

}
