package model.users;

import model.db.errors.ObjectCreationException;
import controller.Controller;
import api.actions.SysAdminActions;
import api.types.BasicUserInfo;

/**
 * Represents a system admin.
 * A system admin is also a user, and can do SysAdminActions.
 * @author Elia
 *
 */
public class SysAdmin extends User implements SysAdminActions {
	
	public SysAdmin(BasicUserInfo info) {
		super(info);
	}

	/**
	 * Adds a new manager
	 * @param contactInfo the basic info
	 * @param password the password of the manager
	 * @return the userID of the manager created, or -1 if there was an error
	 */
	@Override
	public int addManager(BasicUserInfo contactInfo, String password) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getUserDatabase().addManager(contactInfo, password);
		} catch (ObjectCreationException e) {
			
			e.printStackTrace();
		}
		return -1;
	}


	/**
	 * Adds a new employee
	 * @param contactInfo the basic info
	 * @param password the password of the employee
	 * @return the userID of the employee created, or -1 if there was an error
	 */
	@Override
	public int addEmployee(BasicUserInfo contactInfo, String password) {
		Controller controller = Controller.getInstance();
		try {
			return controller.getUserDatabase().addEmployee(contactInfo, password);
		} catch (ObjectCreationException e) {
			
			e.printStackTrace();
		}
		return -1;
	}

}
