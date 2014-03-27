package model.db;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Collection;

import model.AbstractPrototype;
import model.users.Client;
import api.actions.InformationActions;
import api.types.Product;

/**
 * Represents a database for product management
 * @author Elia
 *
 */
public class ProductsDatabase extends AbstractPrototype implements InformationActions,Serializable {
	/**
	 * a serial key
	 */
	private static final long serialVersionUID = 1330412392027871244L;
	private DatabaseConnection database;
	public ProductsDatabase() {
		database = new DatabaseConnection();
	}
	
	/**
	 * Adds a new product
	 * @param product the product to add
	 * @return the product ID added, or -1 if there was an error adding it
	 */
	public int addProduct(Product product) {
		try {
			database.executeUpdate(MySqlQuery.insertProduct(product.getProductName(), product.getPrice()));
			ResultSet result = database.executeQuery(MySqlQuery.getProductByName(product.getProductName()));
			if(!result.next()) throw new SQLException();
			return result.getInt(MySqlSettings.PRODUCT_ID);
		}
		catch (SQLException e) {
			return -1;
		}
	}
	
	/**
	 * Order a given product
	 * @param product the product to order
	 * @param buyer the client that want to purchase the product
	 * @return true if the purchase was successfully processed
	 */
	public boolean orderProduct(Product product, Client buyer) {
		boolean purchaseSuccess = false;
		try {
			if(database.executeUpdate(MySqlQuery.insertOrder(
					buyer.getBasicUserInfo().getID(), 
					product.getProductID(),
					new Date(new java.util.Date().getTime()),
					product.getPrice())) > 0) {
				purchaseSuccess = true;
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return purchaseSuccess;
	}

	/**
	 * Get all products
	 */
	@Override
	public Collection<Product> getAllProducts() {
		ArrayDeque<Product> products = new ArrayDeque<>();
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getAllProducts());
			while(result.next()) {
				try {
					Product product = new model.types.Product(result.getInt(MySqlSettings.PRODUCT_ID),
							result.getString(MySqlSettings.PRODUCT_NAME), result.getDouble(MySqlSettings.PRODUCT_PRICE));
					products.add(product);					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * Get products by name
	 * @param productName the product name to get
	 */
	@Override
	public Product getProductByName(String productName) {
		Product product = null;
		try {
			ResultSet result = database.executeQuery(MySqlQuery.getProductByName(productName));
			while(result.next()) {
				try {
					product = new model.types.Product(result.getInt(MySqlSettings.PRODUCT_ID),
							result.getString(MySqlSettings.PRODUCT_NAME), result.getString(MySqlSettings.PRODUCT_PRICE));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	/**
	 * Update an existing product
	 * @param newProduct the new product to get info from
	 * @param oldProduct the old product to copy info to
	 * @return true if the update was successful
	 */
	public boolean updateExistingProduct(Product newProduct, Product oldProduct) {
		boolean updateSuccess = false;
		try {
			if(database.executeUpdate(MySqlQuery.updateExistingProduct(
					newProduct.getProductName(),
					newProduct.getPrice(),
					oldProduct.getProductID())) > 0) {
				updateSuccess = true; 
			}						
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return updateSuccess;
	}

}
