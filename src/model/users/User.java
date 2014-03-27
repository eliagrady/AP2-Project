package model.users;


import api.types.BasicUserInfo;


/**
 * model.userManagement
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */

public abstract class User {

	protected BasicUserInfo info;
	
	//protected UsersDatabase usersDatabase;
	
	public User(BasicUserInfo info) {
		this.info = info;
		//usersDatabase = new UsersDatabase();
	}

	public BasicUserInfo getInfo() {
		return info;
	}

}
