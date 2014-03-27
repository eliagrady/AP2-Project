/**
 * This class encapsulates SQL queries into methods relevant to this specific project
 * These methods returns the Query string needed to manipulate the SQL requests
 */
package model.db;

import java.sql.Date;

public class MySqlQuery {


	/**
	 * Generates a query that insert a new user into the database
	 *
	 * @param userName the user's name
	 * @param userEmail the user's email address
	 * @param userPassword the user's password
	 * @param userRole the user's role
	 * @return a string representation of the query needed to insert a new user
	 */
	public static String insertUser(String userName, String userEmail, String userPassword, String userRole) {
		return "INSERT INTO " + MySqlSettings.TABLE_USERS +
				"(" + MySqlSettings.USER_NAME +
				"," + MySqlSettings.USER_EMAIL +
				"," + MySqlSettings.USER_PASSWORD +
				"," + MySqlSettings.USER_ROLE + ") " +
				"VALUES('" + userName +
				"','" + userEmail +
				"','" + userPassword +
				"','" + userRole + "')";
	}


	/**
	 * Generates a query that insert a new client's billing information into the database
	 *
	 * @param clientEmail the client's email address
	 * @param clientBillingAddress the client's billing address
	 * @param clientCreditCard the credit card of the client
	 * @return a string representation of the query needed to insert a new client
	 */
	public static String insertClientBillingInformation(String clientEmail,String clientBillingAddress, String clientCreditCard) {
		return "INSERT INTO " + MySqlSettings.TABLE_CLIENTS_BILLING_INFO +
				"(" + MySqlSettings.USER_EMAIL +
				"," + MySqlSettings.CLIENT_BILLING_ADDRESS +
				"," + MySqlSettings.CLIENT_CREDIT_CARD +") " +
				"VALUES('" + clientEmail +
				"','" + clientBillingAddress +
				"','" + clientCreditCard + "')";
	}

	/**
	 * Generates a query that insert a new apartment into the database
	 *
	 * @param productName the product's name
	 * @param productPrice the product's price
	 * @return a string representation of the query needed to insert a new product
	 */
	public static String insertProduct(String productName, double productPrice) {
		return "INSERT INTO " + MySqlSettings.TABLE_PRODUCTS +
				"(" + MySqlSettings.PRODUCT_NAME +
				"," + MySqlSettings.PRODUCT_PRICE + ") " +
				"VALUES('" + productName +
				"','" + productPrice + "')";
	}

	/**
	 * Generates a query that insert a new order into the database 
	 * 
	 * @param userID the client's ID
	 * @param productID the product's ID
	 * @param orderDate the order's date of purchase
	 * @param orderSum the order's sum
	 * @return a string representation of the query needed to insert a new order into the database
	 */
	public static String insertOrder(int userID ,int productID , Date orderDate, double orderSum) {
		return "INSERT INTO " + MySqlSettings.TABLE_ORDERS + "" +
				"(" + MySqlSettings.USER_ID +
				"," + MySqlSettings.PRODUCT_ID +
				"," + MySqlSettings.ORDER_DATE +
				"," + MySqlSettings.ORDER_SUM + ") " +
				"VALUES('" + userID +                
				"','" + productID +              
				"','" + orderDate +
				"','" + orderSum + "')";
	}




	/**
	 * Generates a query that clears a specific table (TRUNCATE, not DROP!)
	 *
	 * @param tableName the name of the table to be cleared
	 * @return a string representation of the query needed to activate this request
	 */
	public static String clearTable(String tableName) {
		return "TRUNCATE TABLE " + tableName;
	}

	/**
	 * Generates a query that REMOVES a specific table (DROP, not TRUNCATE!)
	 *
	 * @param tableName the name of the table to be removed
	 * @return a string representation of the query needed to  to activate this request
	 */
	public static String deleteTable(String tableName) {
		return "DROP TABLE " + tableName;
	}

	/**
	 * Generates a query that REMOVES a specific database (DROP, not TRUNCATE!)
	 *
	 * @param databaseName the name of the database to be removed
	 * @return a string representation of the query needed to  to activate this request
	 */
	public static String deleteDatabase(String databaseName) {
		return "DROP DATABASE " + databaseName;
	}

	/**
	 * Generates a query to get all data from a specific table
	 *
	 * @return a string representation of the query needed to get all the data from a specific table
	 */
	public static String requestTable(String tableName) {
		return "SELECT * from " + tableName;
	}

	/**
	 * Generates a query to get get the client given it's ID
	 *
	 * @param userEmail the client's email to retrieve
	 * @return a string representation of the query needed to get the client by it's ID
	 */
	public static String requestClientContactInformationByID(String userID) {
		return "SELECT * from " + MySqlSettings.TABLE_CLIENTS_BILLING_INFO + " WHERE " + MySqlSettings.USER_ID + "=" + userID;
	}

	/**
	 * Generates a query to get get the client given it's name
	 *
	 * @param userName the client's name to retrieve
	 * @return a string representation of the query needed to get the client by it's name
	 */
	public static String requestClientByName(String userName) {
		return "SELECT * from " + MySqlSettings.TABLE_CLIENTS_BILLING_INFO + " WHERE " + MySqlSettings.USER_NAME + "=" + userName;
	}

	/**
	 * Generates a query to get get the client given it's email
	 *
	 * @param userEmail the client's email address to retrieve
	 * @return a string representation of the query needed to get the client by it's email
	 */
	public static String requestClientByEmail(String userEmail) {
		return "SELECT * from " + MySqlSettings.TABLE_CLIENTS_BILLING_INFO + " WHERE " + MySqlSettings.USER_EMAIL + "=" + userEmail;
	}

	/**
	 * Returns a query to create a database
	 *
	 * @return a query to create a database
	 */
	public static String createDatabaseQuery() {
		return "CREATE DATABASE IF NOT EXISTS " + MySqlSettings.DATABASE_NAME + " DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
	}


	/**
	 * Returns a query to create a new orders table
	 *
	 * @return a query to create a new orders table
	 */
	public static String createUsersTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_USERS + "` (" +
				"  `" + MySqlSettings.USER_ID + "` int(11) NOT NULL AUTO_INCREMENT," +
				"  `" + MySqlSettings.USER_NAME + "` text CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.USER_EMAIL + "` VARCHAR(255) CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.USER_PASSWORD + "` text CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.USER_ROLE + "` text CHARACTER SET latin1 NOT NULL," +
				"  PRIMARY KEY (`" + MySqlSettings.USER_ID + "`)" +
				","+"  UNIQUE (`" + MySqlSettings.USER_EMAIL + "`) "+
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new orders table
	 *
	 * @return a query to create a new orders table
	 */
	public static String createClientInformationTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_CLIENTS_BILLING_INFO + "` (" +
				"  `" + MySqlSettings.USER_EMAIL + "` VARCHAR(255) CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.CLIENT_BILLING_ADDRESS + "` text CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.CLIENT_CREDIT_CARD + "` text CHARACTER SET latin1 NOT NULL," +
				"  PRIMARY KEY (`" + MySqlSettings.USER_EMAIL + "`)" +
				","+"  FOREIGN KEY (`" + MySqlSettings.USER_EMAIL + "`) " +
				"REFERENCES "+MySqlSettings.TABLE_USERS+"("+MySqlSettings.USER_EMAIL+")"+
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new clients table
	 *
	 * @return a query to create a new clients table
	 */
	public static String createProductTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_PRODUCTS + "` (" +
				"  `" + MySqlSettings.PRODUCT_ID + "` int(11) NOT NULL AUTO_INCREMENT," +
				"  `" + MySqlSettings.PRODUCT_NAME + "` text CHARACTER SET latin1 NOT NULL," +
				"  `" + MySqlSettings.PRODUCT_PRICE + "` double NOT NULL," +
				"  PRIMARY KEY (`" + MySqlSettings.PRODUCT_ID + "`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Returns a query to create a new orders table
	 *
	 * @return a query to create a new orders table
	 */
	public static String createOrdersTableQuery() {
		return "CREATE TABLE IF NOT EXISTS `" + MySqlSettings.TABLE_ORDERS + "` (" +
				"  `" + MySqlSettings.ORDER_ID + "` int(11) NOT NULL AUTO_INCREMENT," +
				"  `" + MySqlSettings.USER_ID + "` int(11) NOT NULL," +
				"  `" + MySqlSettings.PRODUCT_ID + "` int(11) NOT NULL," +
				"  `" + MySqlSettings.ORDER_DATE + "` date NOT NULL," +
				"  `" + MySqlSettings.ORDER_SUM + "` double NOT NULL," +
				"  PRIMARY KEY (`" + MySqlSettings.ORDER_ID + "`)" +
				","+"  FOREIGN KEY (`" + MySqlSettings.USER_ID + "`) " +
				"REFERENCES "+MySqlSettings.TABLE_USERS+"("+MySqlSettings.USER_ID+")"+
				","+"  FOREIGN KEY (`" + MySqlSettings.PRODUCT_ID + "`) " +
				"REFERENCES "+MySqlSettings.TABLE_PRODUCTS+"("+MySqlSettings.PRODUCT_ID+")"+
				") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci";
	}

	/**
	 * Query to get orders by given date
	 * @param start the start date
	 * @param end the end date
	 * @return the query string to do this operation
	 */
	public static String getOrdersByDate(Date start, Date end) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_ORDERS);
		builder.append(" NATURAL JOIN ");
		builder.append(MySqlSettings.TABLE_PRODUCTS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.PRODUCT_ID);
		builder.append("=");
		builder.append(MySqlSettings.TABLE_PRODUCTS + "." + MySqlSettings.PRODUCT_ID);
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.ORDER_DATE);
		builder.append(" BETWEEN ");
		builder.append("\'" + start + "\'");
		builder.append(" AND ");
		builder.append("\'" + end + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get a user by email and password
	 * @param email the email to check
	 * @param password the password to check
	 * @return the query needed for the execution of this sql query
	 */
	public static String getUsersByEmailAndPassword(String email, String password) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.USER_EMAIL);
		builder.append("=");
		builder.append("\'" + email + "\'");
		builder.append(" AND ");
		builder.append(MySqlSettings.USER_PASSWORD);
		builder.append(" = ");
		builder.append("\'" + password + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get all products
	 * @return a query to get all products
	 */
	public static String getAllProducts() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_PRODUCTS);
		return builder.toString();
	}

	/**
	 * Generate a query to get a product by it's name
	 * @param productName the product name
	 * @return a query to search for the product
	 */
	public static String getProductByName(String productName) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_PRODUCTS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.PRODUCT_NAME);
		builder.append("=");
		builder.append("\'" + productName + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get orders by a specific client
	 * @param clientID the client ID to search
	 * @return the string representation of this query
	 */
	public static String getOrdersByClient(int clientID) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_ORDERS);
		builder.append(" NATURAL JOIN ");
		builder.append(MySqlSettings.TABLE_PRODUCTS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.PRODUCT_ID);
		builder.append("=");
		builder.append(MySqlSettings.TABLE_PRODUCTS + "." + MySqlSettings.PRODUCT_ID);
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.USER_ID);
		builder.append("=");
		builder.append("\'" + clientID + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get all employees
	 * @return a query to get all employees
	 */
	public static String getEmployees() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_EMPLOYEE + "\'");
		return builder.toString();
	}	
	
	/**
	 * Generate a query to get all clients
	 * @return a query to get all clients
	 */
	public static String getClients() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_CLIENT + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get clients that purchased over a given price
	 * @param purchaseSum the price to get clients that purchased above it
	 * @return the string representation for this query
	 */
	public static String getClientsPurchasedOverSum(double purchaseSum) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * .,");
		builder.append("SUM("+MySqlSettings.ORDER_SUM+")"+ " AS " + MySqlSettings.ORDERS_SUM);
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" NATURAL JOIN ");
		builder.append(MySqlSettings.TABLE_ORDERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_CLIENT + "\'");
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ID);
		builder.append("=");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.USER_ID);
		builder.append(" GROUP BY ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ID);
		builder.append(" HAVING ");
		builder.append("SUM("+MySqlSettings.ORDER_SUM+")"+ " > " + purchaseSum);
		return builder.toString();
	}


	/**
	 * Generate a query to update an existing product
	 * @param newProductName the new product name
	 * @param newProductPrice the new product price
	 * @param oldProductID the old product ID to edit
	 * @return a string representation for the query
	 */
	public static String updateExistingProduct(String newProductName, double newProductPrice, int oldProductID) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(MySqlSettings.TABLE_PRODUCTS);
		builder.append(" SET ");
		builder.append(MySqlSettings.PRODUCT_PRICE);
		builder.append("=");
		builder.append("\'" + newProductPrice + "\'");
		builder.append(" , ");
		builder.append(MySqlSettings.PRODUCT_NAME);
		builder.append("=");
		builder.append("\'" + newProductName + "\'");
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_PRODUCTS + "." + MySqlSettings.PRODUCT_ID);
		builder.append("=");
		builder.append("\'" + oldProductID + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to update basic user Information
	 * @param userID the user ID 
	 * @param username the username
	 * @param email the email
	 * @param password the password
	 * @param roleClient the role of the client (usually a client)
	 * @return a string representation for this query
	 */
	public static String updateBasicUserInformation(int userID,String username, String email,
			String password, String roleClient) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" SET ");
		builder.append(MySqlSettings.USER_NAME);
		builder.append("=");
		builder.append("\'" + username + "\'");
		builder.append(" , ");
		builder.append(MySqlSettings.USER_EMAIL);
		builder.append("=");
		builder.append("\'" + email + "\'");
		builder.append(" , ");
		builder.append(MySqlSettings.USER_PASSWORD);
		builder.append("=");
		builder.append("\'" + password + "\'");
		builder.append(" , ");
		builder.append(MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + roleClient + "\'");
		builder.append(" WHERE ");
		builder.append(MySqlSettings.USER_ID);
		builder.append("=");
		builder.append(userID);
		return builder.toString();
	}
	
	/**
	 * Generate a query to update a client's billing information
	 * @param billingAddress the new billing address
	 * @param creditCardNumber the new credit card number
	 * @param email the email for this client
	 * @return a query to update the client's billing info
	 */
	public static String updateBillingUserInformation(String billingAddress,int creditCardNumber, String email) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(MySqlSettings.TABLE_CLIENTS_BILLING_INFO);
		builder.append(" SET ");
		builder.append(MySqlSettings.CLIENT_BILLING_ADDRESS);
		builder.append("=");
		builder.append("\'" + billingAddress + "\'");
		builder.append(" , ");
		builder.append(MySqlSettings.CLIENT_CREDIT_CARD);
		builder.append("=");
		builder.append("\'" + creditCardNumber + "\'");
		builder.append(" WHERE ");
		builder.append(MySqlSettings.USER_EMAIL);
		builder.append("=");
		builder.append("\'" + email + "\'");
		return builder.toString();
	}

	/**
	 * Generate a query to get a client by a given email
	 * @param email the email to search by
	 * @return a query to search for the client
	 */
	public static String getClientByEmail(String email) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_CLIENT + "\'");
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_EMAIL);
		builder.append("=");
		builder.append("\'" + email + "\'");
		return builder.toString();
	}
	
	/**
	 * Generate a query to get a client by a given email
	 * @param userID the userID
	 * @return a query to search for the client
	 */
	public static String getClientByID(int userID) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_CLIENT + "\'");
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ID);
		builder.append("=");
		builder.append("\'" + userID + "\'");
		return builder.toString();
	}


	/**
	 * Generate a query to get clients that purchased a given product
	 * @param productName the product name to search
	 * @return the string representation for this query
	 */
	public static String getClientsPurchasedProduct(String productName) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * ");
		builder.append(" FROM ");
		builder.append(MySqlSettings.TABLE_USERS);
		builder.append(" NATURAL JOIN ");
		builder.append("("+MySqlSettings.TABLE_PRODUCTS);
		builder.append(" NATURAL JOIN ");
		builder.append(MySqlSettings.TABLE_ORDERS+")");
		builder.append(" WHERE ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ROLE);
		builder.append("=");
		builder.append("\'" + MySqlSettings.ROLE_CLIENT + "\'");
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_USERS + "." + MySqlSettings.USER_ID);
		builder.append("=");
		builder.append(MySqlSettings.TABLE_ORDERS + "." + MySqlSettings.USER_ID);
		builder.append(" AND ");
		builder.append(MySqlSettings.TABLE_PRODUCTS + "." + MySqlSettings.PRODUCT_NAME);
		builder.append("=");
		builder.append("\'" + productName + "\'");
		return builder.toString();
	}
}