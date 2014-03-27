package model.db;


import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Collection;

import model.AbstractPrototype;
import model.db.errors.OrderProcessException;


import api.types.Order;
import api.types.Product;

/**
 * This class represent a database management for orders
 * @author Elia
 *
 */
public class OrdersDatabase extends AbstractPrototype implements Serializable {
	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -6894993723960591507L;
	private DatabaseConnection database;
	public OrdersDatabase() {
		database = new DatabaseConnection();
	}
	
	/**
	 * Order a given product 
	 * @param product the product to order
	 * @param clientID the user that orders the product
	 * @return true if the order was processed successfully
	 * @throws OrderProcessException if the order was unable to process
	 */
	public boolean orderProduct(Product product, int clientID) throws OrderProcessException {		
		try {
			//The query will return the number of rows affected by the query.
			//If it's more than 0, then the order was processed.
			if(database.executeUpdate(MySqlQuery.insertOrder(clientID,  product.getProductID(), 
					new Date(new java.util.Date().getTime()), product.getPrice()))>0) {
				return true;
			}
		}
		catch(SQLException e) {
			throw new OrderProcessException(e);
		}
		return false;
	}
	
	/**
	 * Get orders by given date
	 * @param start the start date
	 * @param end the end date
	 * @return a collection of orders from a given date to a given date
	 */
	public Collection<Order> getOrdersByDate(java.util.Date start, java.util.Date end) {
		ArrayDeque<Order> orders = new ArrayDeque<>();
		try {
			String query = MySqlQuery.getOrdersByDate(new Date(start.getTime()), new Date(end.getTime()));
			ResultSet result = database.executeQuery(query);
			while(result.next()) {
				try {
					Product orderedProduct = new model.types.Product(
							result.getInt(MySqlSettings.PRODUCT_ID),
							result.getString(MySqlSettings.PRODUCT_NAME),
							result.getDouble(MySqlSettings.PRODUCT_PRICE));
					Order order = new model.types.Order(
							result.getInt(MySqlSettings.USER_ID),
							orderedProduct,
							result.getDate(MySqlSettings.ORDER_DATE),
							result.getDouble(MySqlSettings.ORDER_SUM));
					orders.add(order);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	/**
	 * Get orders by a given userID
	 * @param userID the clientID
	 * @return
	 */
	public Collection<Order> getOrdersByClient(int userID) {
		ArrayDeque<Order> orders = new ArrayDeque<>();
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getOrdersByClient(userID));
			while(result.next()) {
				try {
					Product orderedProduct = new model.types.Product(
							result.getInt(MySqlSettings.PRODUCT_ID),
							result.getString(MySqlSettings.PRODUCT_NAME),
							result.getDouble(MySqlSettings.PRODUCT_PRICE));
					Order order = new model.types.Order(
							result.getInt(MySqlSettings.USER_ID),
							orderedProduct,
							result.getDate(MySqlSettings.ORDER_DATE),
							result.getDouble(MySqlSettings.ORDER_SUM));
					orders.add(order);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

}
