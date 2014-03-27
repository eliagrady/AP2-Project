package api.actions;

import api.types.BasicUserInfo;


/**
 * This interface represents actions for this system administrator
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public interface SysAdminActions {
	
	/**
	 * Adds a new Manager
	 * @param contactInfo the contact info for the new Manager
	 * @param password the password of the new Manager
	 * @return the userID of the new Manager
	 */
    public int addManager(BasicUserInfo contactInfo, String password);
    

	/**
	 * Adds a new Employee
	 * @param contactInfo the contact info for the new Employee
	 * @param password the password of the new Employee
	 * @return the userID of the new Employee
	 */
    public int addEmployee(BasicUserInfo contactInfo, String password);
}
