package model.db;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Represents a test script for testing purposes
 * @author Elia
 *
 */
public class MySqlDatabaseTestScript extends DatabaseConnection {


	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -3450972713674494935L;

	public MySqlDatabaseTestScript() {
		
	}

	/**
	 * Builds up the database structure
	 */
	public void buildDatabaseStructure() {
		silentExecutorDatabaseContext(MySqlQuery.deleteDatabase(MySqlSettings.DATABASE_NAME));
		silentExecutorDatabaseContext(MySqlQuery.createDatabaseQuery());
		silentExecutor(MySqlQuery.createUsersTableQuery());
		silentExecutor(MySqlQuery.createClientInformationTableQuery());
		silentExecutor(MySqlQuery.createProductTableQuery());
		silentExecutor(MySqlQuery.createOrdersTableQuery());
	}

	/**
	 * Execute the queries and write if the execution result was completed in the console
	 * @param sqlQuery the query to execute
	 */
	private void silentExecutor(String sqlQuery) {
		int result = -1;
		try {
			result = super.executeUpdate(sqlQuery);
		}
		catch(SQLException e) {
			System.out.println("execution failed: "+sqlQuery);
			System.out.println("failure reason: "+e.getMessage());
		}
		if(result > -1) {
			System.out.println("execution success: "+sqlQuery);
		}
	}
	
	/**
	 * Execute the query in the database context to create the database itself
	 * @param databaseContextSqlQuery the query to execute
	 */
	private void silentExecutorDatabaseContext(String databaseContextSqlQuery) {
		int result = -1;
		try {
			result = super.executeUpdateDB(databaseContextSqlQuery);
		}
		catch(SQLException e) {
			System.out.println("execution failed: "+databaseContextSqlQuery);
			System.out.println("failure reason: "+e.getMessage());
		}
		if(result > -1) {
			System.out.println("execution success: "+databaseContextSqlQuery);
		}
	}

	/**
	 * Fills orders table
	 */
	private void fillOrders() {
		silentExecutor(MySqlQuery.insertOrder(1,3, new Date(new java.util.Date().getTime()), 5));
		silentExecutor(MySqlQuery.insertOrder(1,4, new Date(new java.util.Date().getTime()), 8));
		silentExecutor(MySqlQuery.insertOrder(1,4, new Date(new java.util.Date().getTime()), 8));
		
		//Next line fail:: cannot make orders if user does not exist
		silentExecutor(MySqlQuery.insertOrder(4,6, new Date(new java.util.Date().getTime()), 99999999));
	}

	/**
	 * Fill products table
	 */
	private void fillProducts() {
		silentExecutor(MySqlQuery.insertProduct("sword", 105.75));
		silentExecutor(MySqlQuery.insertProduct("Brain", 100000.75));
		silentExecutor(MySqlQuery.insertProduct("Dexter Figurine", 5));
		silentExecutor(MySqlQuery.insertProduct("Cheese", 8));
		silentExecutor(MySqlQuery.insertProduct("Blue Cheese", 8.99));
		silentExecutor(MySqlQuery.insertProduct("Nuclear Bomb", 99999999));
	}

	/**
	 * Fill users table and user billing info table
	 */
	private void fillUsers() {
		silentExecutor(MySqlQuery.insertUser("Amjad", "amjad.alian@gmail.com", "mabruk", MySqlSettings.ROLE_CLIENT));
		//Next line fail: bushra client not exist
		//silentExecutor(MySqlQuery.insertClientBillingInformation("bushra.alian@gmail.com", "Rachavia Hanarkis 48", "88856215"));
		silentExecutor(MySqlQuery.insertUser("oops", "oops@gmail.com", "oops", MySqlSettings.ROLE_CLIENT));
		silentExecutor(MySqlQuery.insertUser("Bushra", "bushra.alian@gmail.com", "ahlen", MySqlSettings.ROLE_CLIENT));
		silentExecutor(MySqlQuery.insertClientBillingInformation("bushra.alian@gmail.com","Rachavia Hanarkis 48", "6556215"));
		//Next line fail:: duplicate email entry isn't allowed
		//silentExecutor(MySqlQuery.insertClientBillingInformation("bushra.alian@gmail.com", "Rachavia Hanarkis 48", "88856215"));
		
		//Next line fail:: duplicate email entry isn't allowed (user email exist)
		//silentExecutor(MySqlQuery.insertUser("oops", "oops@gmail.com", "oops", MySqlSettings.ROLE_CLIENT));
		//Next line fail:: duplicate email entry isn't allowed (user email exist)
		//silentExecutor(MySqlQuery.insertUser("Bushra", "bushra.alian@gmail.com", "ahlen", MySqlSettings.ROLE_CLIENT));
		
		silentExecutor(MySqlQuery.insertClientBillingInformation("amjad.alian@gmail.com","Rachavia Hanarkis 48", "6556215"));		
		
		silentExecutor(MySqlQuery.insertUser("Noga", "noga.last@gmail.com", "password1", MySqlSettings.ROLE_EMPLOYEE));
		silentExecutor(MySqlQuery.insertUser("Natan", "natan.last@gmail.com", "password2", MySqlSettings.ROLE_EMPLOYEE));
		silentExecutor(MySqlQuery.insertUser("Naama", "naama.last@gmail.com", "password", MySqlSettings.ROLE_MANAGER));
		silentExecutor(MySqlQuery.insertUser("Elia", "elia.grady@gmail.com", "password", MySqlSettings.ROLE_SYSADMIN));		
	}
	
	/**
	 * Create an example database
	 */
	public void createExampleDatabase() {
		buildDatabaseStructure();
		fillUsers();
		//fillClients();
		//fillEmployees();
		//fill store data
		fillProducts();
		fillOrders();	
	}
}
