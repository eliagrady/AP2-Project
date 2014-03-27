package api.actions;

import java.util.Collection;

import api.types.Product;

/**
 * This interface represents basic actions that everyone can do
 * @author Elia
 *
 */
public interface InformationActions {
	/**
	 * Get all products in the database
	 * @return a collection of products
	 */
    public Collection<Product> getAllProducts();
    /**
     * Get a product by it's name
     * @param productName the name of the product
     * @return the product if found, or null otherwise
     */
    public Product getProductByName(String productName);
}
