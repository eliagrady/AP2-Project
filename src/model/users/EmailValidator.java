package model.users;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class EmailValidator {
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	private static Matcher matcher;
 
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	/**
	 * Validate hex with regular expression
	 * 
	 * @param email
	 *            hex for validation
	 * @return true valid email, false invalid email
	 */
	public static boolean validate(final String email) { 
		matcher = pattern.matcher(email);
		return matcher.matches();
 
	}
}