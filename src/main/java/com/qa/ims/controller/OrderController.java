package com.qa.ims.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
	private OrderItemDAO orderitemDAO;
	private Utils utils;

	public OrderController(CustomerDAO customerDAO, OrderDAO orderDAO, ItemDAO itemDAO, OrderItemDAO orderitemDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.orderitemDAO = orderitemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		LOGGER.info("Orders: ");
		Utils.printDottedLine();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		Utils.printLine();
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		Long next_order_id = (long) 1;
		Order latestOrder = orderDAO.readLatest();
		if (latestOrder != null) {
			next_order_id = latestOrder.getOrderId() + 1;
		}
		Order order = create(next_order_id);
		LOGGER.info("Order Created:");
		Utils.printDottedLine();
		LOGGER.info(order);
		Utils.printLine();
		return order;
	}
	//Overload
	public Order create(Long order_id) {
		DecimalFormat df = new DecimalFormat("#.##");
		LOGGER.info("Please enter a customer ID");
		Long customer_id = utils.getLong();
		Customer customer = customerDAO.read(customer_id);
		LOGGER.info("Please enter a delivery date");
		String shipment_date = utils.getDate();
		List<OrderItem> orderitems = new ArrayList<OrderItem>();
		List<Item> items = new ArrayList<Item>();
		String user_finished = "N";
		Item item;
		Double cost = (double) 0;
		do {
			LOGGER.info("Please enter an item ID");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter how many items of these you want");
			int item_quantity = Math.toIntExact(utils.getLong());
			item = itemDAO.read(item_id);
			cost += item.getPrice() * item_quantity;
			cost = Double.valueOf(df.format(cost));
			orderitems.add(new OrderItem(order_id, item_id, item_quantity));
			items.add(item);
			printTicket(order_id, customer, items, orderitems, cost, shipment_date);
			LOGGER.info("Do you want to submit your order? (Y/N)");
			user_finished = utils.getString();
		} while (user_finished.equals("N"));
		Order order;
		if(orderDAO.read(order_id) == null) {
			order = new Order(customer_id, cost, shipment_date);
			order = orderDAO.create(order);
		}else {
			order = new Order(order_id, customer_id, cost, shipment_date);
		}
		for (OrderItem orderitem : orderitems) {
			orderitemDAO.create(orderitem);
		}
		return order;
	}
	
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the order_id of the order you would like to update");
		Long order_id = utils.getLong();
		Order orderFound = orderDAO.read(order_id);
		if(orderFound == null) {
			LOGGER.info("No order was found to update. Try Again (Y/N)?");
			if(utils.getString().equals("Y")) {
				update();
			}
		}
		Long customer_idFound = orderFound.getCustomerId();
		Customer customer = customerDAO.read(customer_idFound);
		List<OrderItem> orderitems = orderitemDAO.readOrderItems(order_id);
		List<Item> items = new ArrayList<Item>();
		for(OrderItem orderitem: orderitems) {
			orderitemDAO.delete(orderitem.getOrderItemId());
			items.add(itemDAO.read(orderitem.getItemId()));
		};
		Double costFound = orderFound.getCost();
		String shipment_dateFound = orderFound.getShipmentDate();
		printTicket(order_id, customer, items, orderitems, costFound, shipment_dateFound);
		Order order = orderDAO.update(create(order_id));
		LOGGER.info("Order Updated: ");
		Utils.printDottedLine();
		LOGGER.info(order);
		Utils.printLine();
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the order_id of the order you would like to delete");
		Long order_id = utils.getLong();
		Order orderFound = orderDAO.read(order_id);
		if(orderFound==null) {
			LOGGER.info("No order was found to delete. Try Again (Y/N)");
			if(utils.getString().equals("Y")) {
				delete();
			}
			return 0;
		}
		LOGGER.info("Order Deleted");
		Utils.printDottedLine();
		Utils.printLine();
		return orderDAO.delete(order_id);
	}
	/**
	 * Prints the contents and information of an order
	 */
	public void printTicket(Long order_id, Customer customer, List<Item> items, List<OrderItem> orderitems, Double cost, String shipment_date) {
		Utils.printDottedLine();
		LOGGER.info("Order ID: "+order_id);
		Utils.printDottedLine();
		LOGGER.info("Customer Name: "+customer.getFirstName()+" "+customer.getSurname());
		Utils.printDottedLine();
		for (int i = 0; i < items.size(); i++) {
			LOGGER.info(items.get(i).getItemName() + " x" + orderitems.get(i).getItemQuantity());
		}
		LOGGER.info("Total: £ " + cost);
		Utils.printDottedLine();
		LOGGER.info("Delivery Date: "+shipment_date);
		Utils.printDottedLine();
	}

}
