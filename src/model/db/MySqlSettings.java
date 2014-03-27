/**
 * This class represents a settings class, for constants used in the SQL representation.
 * If a column's name in the representation needs to change, it can be changed here alone.
 */
package model.db;



public class MySqlSettings {
    // Settings
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_HOST = "localhost";
    
    public static final String DATABASE_NAME = "ap2_project_db";  
    public static final String databaseFullURL = "jdbc:mysql://" + DATABASE_HOST + "/" + DATABASE_NAME;
    protected static final String adminUsername = "root";
    protected static final String adminPassword = "";

    // Data representation within the SQL model.
    
    //Roles
    

	public static final String ROLE_CLIENT = "Client";
	public static final String ROLE_EMPLOYEE = "Employee";
	public static final String ROLE_MANAGER = "Manager";
	public static final String ROLE_SYSADMIN = "Admin";
    
    //Table names and column names within each table 
    
    //USERS (clients+employees)
    public static final String TABLE_USERS = "users";  

    public static final String USER_ID = "userID";
    public static final String USER_NAME = "userName";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_ROLE = "userRole";
    
    //CLIENT CONTACT AND BILLING INFORMATION
    public static final String TABLE_CLIENTS_BILLING_INFO = "clientsInfo";  

    //public static final String USER_ID = "userID";  // A Client is a user
    public static final String CLIENT_BILLING_ADDRESS = "billingAddress";
    public static final String CLIENT_CREDIT_CARD = "creditCard";
    
    
    //PRODUCTS
    public static final String TABLE_PRODUCTS = "products";
    
    public static final String PRODUCT_ID = "productID";
    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_PRICE = "productPrice";
    //public static final String PRODUCT_QUANTITY = "productQuantity";
    
    
    //ORDERS
    public static final String TABLE_ORDERS = "orders";

    public static final String ORDER_ID = "orderID";
    //public static final String USER_ID = "userID";  //already defined
    public static final String ORDER_DATE = "orderDate";
    public static final String ORDER_SUM = "orderSum";
	//For special 'clients over sum' query
    public static final String ORDERS_SUM = "ordersSum";
}
