package model.types;

import java.io.Serializable;

import api.types.BasicUserInfo;
import model.AbstractPrototype;

/**
 * This class represents a basic user info model
 * @author Elia
 *
 */
public class UserInfo extends AbstractPrototype implements BasicUserInfo,Serializable {
	/**
	 * a serial key
	 */
	private static final long serialVersionUID = 6254148345588974566L;
	private String role;
	private String name;
	private String email;
	private int id;

	/**
	 * Create a new user info object
	 * @param role the user role
	 * @param name the user name
	 * @param email the user email
	 * @param id the user ID
	 */
	public UserInfo(String role, String name, String email, int id) {
		this.role = role;
		this.name = name;
		this.email = email;
		this.id = id;
	}

	/**
	 * Default constructor
	 */
    public UserInfo() {
    }


    /**
     * Get the user role
     */
    @Override
	public String getRole() {
		return role;
	}

    /**
     * Get the user email 
     */
	@Override
	public String getEmail() {
		return email;
	}

	
	/**
	 * Get the user name
	 */
	@Override
	public String getUsername() {
		return name;
	}

	/**
	 * Get the user ID
	 */
	@Override
	public int getID() {
		return id;
	}

}
