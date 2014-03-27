/**
 * Represents an Exception that occurs when the creation of an object has failed
 */
package model.users.errors;

/**
 * Represent an error with the email syntax. 
 * Email should be: user@domain.suffix
 * @author Elia
 *
 */
@SuppressWarnings("serial")
public class InvalidEmailAddressException extends Exception {
    /**
     * Default constructor
     */
    public InvalidEmailAddressException() {

    }

    /**
     * A string representation of this exception
     *
     * @return the string representation of this exception
     */
    @Override
    public String getMessage() {
        return "Invalid Email address format";
    }
}
