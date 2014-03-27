/**
 * Represents an Exception that occurs when there is a permission issue with a given request
 */
package model.users.errors;

@SuppressWarnings("serial")
public class PremissionException extends Exception {
    /**
     * Default constructor
     */
    public PremissionException() {

    }

    /**
     * A string representation of this exception
     *
     * @return the string representation of this exception
     */
    @Override
    public String getMessage() {
        return "Insufficiate premissions. Access Denied";
    }
}
