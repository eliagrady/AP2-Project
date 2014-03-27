package api.actions;
import java.util.Collection;
import java.util.Date;

import api.types.Order;


/**
 * This class represents actions the manager can do
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public interface ManagerActions extends InformationActions {
	
	/**
	 * Gets a collection of employee interfaces
	 * @return a collection of employee interfaces
	 */
    public Collection<EmployeeActions> getEmployees();
    
	/**
	 * Gets a collection of client interfaces
	 * @return a collection of client interfaces
	 */
    public Collection<ClientActions> getClients();
    
    /**
     * Gets a collection of client interfaces that has ordered above a given price
     * @param price the minimum price to determine if the client should be returned
     * @return a collection of client interfaces that ordered above a given price
     */
    public Collection<ClientActions> getClientsOrderedAbovePrice(double price);
    
    /**
     * Get a report on orders by given date range
     * @param startDate the start date of the report
     * @param endDate the end date of the report
     * @return the collection of orders, as a report
     */
    public Collection<Order> getReportByDate(Date startDate, Date endDate);

    /**
     * Gets a collection of client interfaces that has ordered a specific product
     * @param productName the product name to search
     * @return a collection of client interfaces that ordered a given product name
     */
	public Collection<ClientActions> getClientsOrderedProduct(String productName);
}
