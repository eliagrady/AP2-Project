package controller;

import java.io.Serializable;

import api.actions.ClientActions;
import api.actions.EmployeeActions;
import api.actions.ManagerActions;
import api.actions.SysAdminActions;
import model.MyPrototypeFactory;
import model.PrototypeFactory;
import model.db.DatabaseConnection;
import model.db.MySqlDatabaseTestScript;
import model.db.OrdersDatabase;
import model.db.ProductsDatabase;
import model.db.UsersDatabase;
import model.db.errors.ObjectCreationException;
import model.users.User;
import view.View;

/**
 * This controller provides the database connections and tests
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class Controller implements Serializable {

	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -7241683304465975536L;
	private MyPrototypeFactory objectsFactory = new MyPrototypeFactory();
	private static Controller instance = new Controller();


	/**
	 * Constructor
	 */
	public Controller() {
		initFactory();
	}



	/**
	 * Get a new instance of this Controller
	 *
	 * @return the instance of the controller
	 */
	public static Controller getInstance() {
		return instance ;
	}



	/**
	 * Run tests on the database
	 */
	public void testSystem() {
		//init system (DB, Scripts etc.)
		MySqlDatabaseTestScript script = new MySqlDatabaseTestScript();
		script.createExampleDatabase();
	}


	/**
	 * Get the view object
	 * @return the view object
	 * @throws ObjectCreationException if a view object was unable to be created
	 */
	public View getView() throws ObjectCreationException {
		return (View) objectsFactory.createObject("View");		
	}

	/**
	 * Creates a new prototype factory statically, with lazy instantiation
	 *
	 * @return the generated prototype factory
	 */
	private void initFactory() {
		objectsFactory.addPrototypeObject("DatabaseConnection", new DatabaseConnection());
		objectsFactory.addPrototypeObject("UserDatabase", new UsersDatabase());
		objectsFactory.addPrototypeObject("ProductsDatabase", new ProductsDatabase());
		objectsFactory.addPrototypeObject("OrdersDatabase", new OrdersDatabase());
		objectsFactory.addPrototypeObject("View", new View());
	}



	/**
	 * Get the database connection
	 * @return a database connection object
	 * @throws ObjectCreationException if the database connection object couldn't be created
	 */
	public DatabaseConnection getDatabase() throws ObjectCreationException {
		return (DatabaseConnection) objectsFactory.createObject("DatabaseConnection");
	}



	/**
	 * Get a user's database
	 * @return the user's database 
	 * @throws ObjectCreationException if the user's database cannot be created
	 */
	public UsersDatabase getUserDatabase() throws ObjectCreationException {
		return (UsersDatabase) objectsFactory.createObject("UserDatabase");		
	}


	/**
	 * Get a product database
	 * @return the product database 
	 * @throws ObjectCreationException if the database cannot be created
	 */
	public ProductsDatabase getProductDatabase() throws ObjectCreationException {
		return (ProductsDatabase) objectsFactory.createObject("ProductsDatabase");		
	}


	/**
	 * Get the factory for databases
	 * @return the prototype factory
	 */
	public PrototypeFactory getFactory() {
		return objectsFactory;		
	}


	/**
	 * Get an orders database
	 * @return an orders database
	 * @throws ObjectCreationException if the database cannot be created
	 */
	public OrdersDatabase getOrdersDatabase() throws ObjectCreationException {
		return (OrdersDatabase) objectsFactory.createObject("OrdersDatabase");	
	}


	/**
	 * Get the proper JSP menu to show the client based on the user's permissions
	 * @param user the user to check permissions for
	 * @return the name of the menu to show
	 */
	public String getMenu(User user) {
		if(user instanceof SysAdminActions){
			return "sysAdminMenu.jsp";
		}
		if(user instanceof ManagerActions){
			return "managerMenu.jsp";
		}
		else if (user instanceof EmployeeActions) {
			return "employeeMenu.jsp";
		}
		else if (user instanceof ClientActions) {
			return "clientMenu.jsp";
		}
		else {
			return "login.jsp";
		}
	
	}

}
