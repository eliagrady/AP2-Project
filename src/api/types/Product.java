package api.types;
/**
 * This class represents an abstract product, with a name, a price and a unique product ID
 */
public interface Product {
	/**
	 * Get the price for this product 
	 * @return the price for this product
	 */
	public double getPrice();
	
	/**
	 * Get the product name
	 * @return the product name
	 */
	public String getProductName();
	
	/**
	 * Get the product unique ID 
	 * @return the product unique ID
	 */
	public int getProductID();

}
