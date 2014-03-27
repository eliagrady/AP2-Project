package model.types;

import api.types.Product;
import model.AbstractPrototype;

import java.io.Serializable;
import java.util.Date;
/**
 * This class represents an order that a client made
 * @author Elia
 *
 */
public class Order extends AbstractPrototype implements api.types.Order,Serializable {
	
	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -8569181180282301822L;
	private int clientID;
	private Product product;
	private Date orderDate;
	private double sum;

    public Order() {

    }
    /**
     * Create a new order
     * @param clientID the userID of the client 
     * @param product the product that was ordered
     * @param orderDate the order date
     * @param sum the order price amount
     */
	public Order(int clientID, Product product, Date orderDate, double sum) {
		this.clientID = clientID;
		this.product = product;
		this.orderDate = orderDate;
		this.sum = sum;
	}

	/**
	 * Get the client ID
	 */
	@Override
	public int getClientID() {
		return clientID;
	}

	/**
	 * Get the product ordered
	 */
	@Override
	public Product getProduct() {
		return product;
	}

	/**
	 * Get the order date
	 */
	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Get the order sum
	 */
	@Override
	public double getOrderSum() {
		return sum;
	}

}
