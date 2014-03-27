package model.types;

import java.io.Serializable;

import model.AbstractPrototype;

/**
 * This class represents a product
 * @author Elia
 *
 */
public class Product extends AbstractPrototype implements api.types.Product,Serializable {
	
	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -6979787081671231765L;
	private int productID;
	private String name;
	private double price;


    public Product() {

    }

    /**
     * Create a new product
     * @param productID the product's ID
     * @param name the product name
     * @param price the product price
     */
	public Product(int productID, String name, double price) {
		this.productID = productID;
		this.name = name;
		this.price = price;
	}

    /**
     * Create a new product
     * @param productID the product's ID
     * @param name the product name
     * @param priceAsString the product price as a string
     */
	public Product(int productID, String name, String priceAsString) {
		this.productID = productID;
		this.name = name;
		this.price = Double.parseDouble(priceAsString);
	}

	/**
	 * Get the product price
	 */
	@Override
	public double getPrice() {
		return price;
	}

	/**
	 * Get the product name
	 */
	@Override
	public String getProductName() {
		return name;
	}
	
	/**
	 * Get the product ID
	 */
	@Override
	public int getProductID() {
		return productID;
	}

}
