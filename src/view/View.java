package view;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import model.AbstractPrototype;
import model.users.Client;
import model.users.Employee;
import model.users.User;

import api.types.BasicUserInfo;
import api.types.Product;
import api.types.Order;
/**
 * Represent a view object that can render requests to HTML format
 * @author Elia
 *
 */
public class View extends AbstractPrototype implements Serializable {

	/**
	 * a serial key
	 */
	private static final long serialVersionUID = -1444177118003308766L;
	/**
	 * 
	 * @param lastAction the last action object
	 * @return HTML code of last action
	 */
	public String getOutputFromLastAction(Object lastAction) {
		StringBuilder builder = new StringBuilder();
		if(lastAction == null) {
			builder.append("<div class=\"errorMessage\">");
			//builder.append("last Action object is null"); //FOR DEBUG
			builder.append("</div>");
			return builder.toString();
		}
		else {

			//Case last action is boolean
			try {
				if((boolean) lastAction) {
					builder.append("<div class=\"lastAction element\">");
					builder.append("Action completed successfully!");
					builder.append("</div>");
					return builder.toString();
				}
				else {
					builder.append("<div class=\"lastAction element\">");
					builder.append("Unable to complete action");
					builder.append("</div>");
					return builder.toString();
				}
			}
			catch(ClassCastException e) {
				//not a boolean then...maybe int?
				//Case last action is int
				try {
					if(((Integer) lastAction)>-1) {
						builder.append("<div class=\"lastAction element\">");
						builder.append("Action completed successfully! user created with ID "+lastAction);
						builder.append("</div>");
						return builder.toString();
					} 
					else {
						builder.append("<div class=\"lastAction element\">");
						builder.append("Unable to complete action");
						builder.append("</div>");
						return builder.toString();
					}
				}
				catch (ClassCastException e2) {
					//not a boolean and not an integer...
				}
			}
			//Case last action is a single product
			if(lastAction instanceof Product) {
				Product product = (Product) lastAction;				
				builder.append("<div class=\"lastAction element\">");
				builder.append("<table>");
				builder.append("<tr>");
				builder.append("<td>"+product.getProductName() +"</td>");
				builder.append("<td>"+product.getPrice() +"</td>");		
				builder.append("</tr>");
				builder.append("</table>");
				builder.append("</div>");
				return builder.toString();
			}
			if(lastAction instanceof Collection<?>) {
				Collection<?> collection = (Collection<?>) lastAction;
				Iterator<?> collectionIterator = collection.iterator();

				builder.append("<table>");				
				int index = 1;
				while (collectionIterator.hasNext()) {
					Object object = (Object) collectionIterator.next();
					builder.append("<tr>");
					//Get orders (or: report)
					if(object instanceof Order) {
						Order order = (Order) object;
						builder.append("<td>"+ index +"</td>");
						builder.append("<td>"+order.getProduct().getProductName() +"</td>");
						builder.append("<td>"+order.getOrderSum() +"</td>");
						builder.append("<td>"+order.getOrderDate() +"</td>");
					}
					//Get products
					else if(object instanceof Product) {
						Product product = (Product) object;
						builder.append("<td>"+ index +"</td>");
						builder.append("<td>"+product.getProductName() +"</td>");
						builder.append("<td>"+product.getPrice() +"</td>");
					}
					//Get All employees
					else if(object instanceof Employee) {
						Employee employee = (Employee) object;
						BasicUserInfo employeeInfo = employee.getBasicUserInfo();
						builder.append("<td>"+ index +"</td>");
						builder.append("<td>"+ employeeInfo.getUsername() +"</td>");
					}
					//Get All clients
					else if(object instanceof Client) {
						Client client = (Client) object;
						BasicUserInfo clientInfo = client.getBasicUserInfo();
						builder.append("<td>"+ index +"</td>");
						builder.append("<td>"+ clientInfo.getUsername() +"</td>");
					}
					builder.append("</tr>");
					index++;

				}
				builder.append("</table>");
				builder.append("</div>");
				return builder.toString();
			}
			else {
				//builder.append("<p id=\"lastAction\"> No data to display </p>");
				return builder.toString();
			}
		}
	}
	
	/**
	 * Welcome message
	 * @param user
	 * @return
	 */
	public String welcomeMessage(User user) {
		StringBuilder builder = new StringBuilder();		
		builder.append("<div class=\"welcomeMessage\">");
		builder.append("Welcome ");
		builder.append(user.getInfo().getUsername());		
		builder.append("</div>");
		return builder.toString();
	}

	public String errorMessage(Object errorMessage) {
		if(errorMessage != null) {
			StringBuilder builder = new StringBuilder();		
			builder.append("<div class=\"errorMessage\">");
			builder.append((String)errorMessage);		
			builder.append("</div>");
			return builder.toString();			
		}
		return "";
	}
}
