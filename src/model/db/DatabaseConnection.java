/**
 * Represents a database objects, allowing to query, modify, save and restore all buildings and apartments inside.
 * Provides an API for actions this Database can perform.
 */
package model.db;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.Serializable;
import java.sql.*;

import model.AbstractPrototype;

public class DatabaseConnection extends AbstractPrototype implements Serializable {	
    /**
	 * a serial key
	 */
	private static final long serialVersionUID = 2636864783296199242L;
	private static MysqlDataSource ds = new MysqlConnectionPoolDataSource();

    /**
     * Constructor for the SqlDatabase
     */
	public DatabaseConnection() {
    	ds.setServerName(MySqlSettings.DATABASE_HOST);
        ds.setDatabaseName(MySqlSettings.DATABASE_NAME);
        ds.setUser(MySqlSettings.adminUsername);
        ds.setPassword(MySqlSettings.adminPassword);
    }
    
	/**
	 * Create a statement for sql queries
	 * @return a statement to make queries with
	 * @throws SQLException if the statement cannot be created
	 */
    private Statement createStatement() throws SQLException {
        return ds.getConnection().createStatement();
    }
    
    /**
     * Given a MySql query, it can execute it, and provide it's ResultSet
     * @param sqlQuery the query string i.e: CREATE TABLE name
     * @return the ResultSet that returned from the query
     * @throws SQLException if the query was unable to be executed
     */
    protected ResultSet executeQuery(String sqlQuery) throws SQLException {
		return createStatement().executeQuery(sqlQuery);    	
    }
    
    /**
     * Given a MySql query that updates the database (in a database context), it can execute it
     * @param sqlQuery the query string i.e: CREATE TABLE name
     * @return either the row count for SQL Data Manipulation Language (DML) statements 
     * 		   or 0 for SQL statements that return nothing 
     * @throws SQLException if the query was unable to be executed
     */
    protected int executeUpdate(String sqlQuery) throws SQLException {        
		return createStatement().executeUpdate(sqlQuery);
    }
    
    /**
     * Given a MySql query, it execute it with the host context (for database creation)
     * @param sqlQuery the query string i.e: CREATE DATABASE name
     * @return either the row count for SQL Data Manipulation Language (DML) statements 
     * 		   or 0 for SQL statements that return nothing 
     * @throws SQLException if the query was unable to be executed
     */
    protected int executeUpdateDB(String sqlQuery) throws SQLException {
        try {
			Class.forName(MySqlSettings.DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection connectionTest = DriverManager.getConnection
                ("jdbc:mysql://" + MySqlSettings.DATABASE_HOST + "/" +
                        "?user=" + MySqlSettings.adminUsername + "" +
                        "&password=" + MySqlSettings.adminPassword + "");
        Statement statementTest = connectionTest.createStatement();
		return statementTest.executeUpdate(sqlQuery);    	
    }
    
    /**
     * Given a MySql query, it can execute it, and provide it's ResultSet
     * @param sqlQuery the query string i.e: CREATE TABLE name
     * @return true if the first result is a ResultSet object;
     * 		   false if it is an update count or there are no results 
     * @throws SQLException if the query was unable to be executed
     */
    protected boolean execute(String sqlQuery) throws SQLException {
		return createStatement().execute(sqlQuery);    	
    }
    
}