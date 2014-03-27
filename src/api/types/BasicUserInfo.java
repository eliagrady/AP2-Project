package api.types;

/**
 * This interface represent basic user information:
 * Role, email , the user's name and the userID
 * @author Elia
 *
 */
public interface BasicUserInfo {
	
	/**
	 * Get the role of the user
	 * @return the role of the user
	 */
    public String getRole();
    
    /**
     * Get the email of the user
     * @return the email of the user
     */
    public String getEmail();
    
    /**
     * Get the user's name
     * @return the user's name
     */
    public String getUsername();
    
    /**
     * The userID of this user
     * @return the userID of the user
     */
    public int getID();
}
