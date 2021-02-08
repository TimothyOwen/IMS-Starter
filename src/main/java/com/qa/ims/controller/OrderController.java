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
import com.qa.ims.utils.PrintUtils;
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
	private OrderItemSubController orderitemsubcontroller;
	private Utils utils;
	private PrintUtils printutils;

	public OrderController(CustomerDAO customerDAO, OrderDAO orderDAO, ItemDAO itemDAO, OrderItemDAO orderitemDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.orderDAO = orderDAO;
		this.itemDAO = itemDAO;
		this.orderitemDAO = orderitemDAO;
		this.orderitemsubcontroller = new OrderItemSubController(itemDAO, orderitemDAO);
		this.utils = utils;
		this.printutils = new PrintUtils();
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		LOGGER.info("Orders: ");
		PrintUtils.printDottedLine();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		PrintUtils.printLine();
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
		Order order = createOrderItems(next_order_id);
		LOGGER.info("Order Created:");
		PrintUtils.printDottedLine();
		LOGGER.info(order);
		PrintUtils.printLine();
		return order;
	}
	//Overload used for create and update methods
	public Order createOrderItems(Long order_id) {
		DecimalFormat df = new DecimalFormat("#.##");
		Customer customer = getCustomerByCustomerId();
		Long customer_id = customer.getCustomerId();
		String shipment_date = getShipmentDate();
		List<OrderItem> orderitems = new ArrayList<OrderItem>();
		List<Item> items = new ArrayList<Item>();
		String user_finished = "N";
		Item item;
		Double cost = (double) 0;
		do {
			Long item_id = getItemId();
			item = itemDAO.read(item_id);
			int item_quantity = Math.toIntExact(getItemQuantity());
			cost += item.getPrice() * item_quantity;
			cost = Double.valueOf(df.format(cost));
			orderitems.add(new OrderItem(order_id, item_id, item_quantity));
			items.add(item);
			printutils.printTicket(order_id, customer, items, orderitems, cost, shipment_date);
			LOGGER.info("Do you want to submit your order? (Y/N)");
			user_finished = utils.getString();
		} while (!user_finished.equals("Y"));
		Order order;
		if(orderDAO.read(order_id) == null) {
			order = new Order(order_id, customer_id, cost, shipment_date);
			order = orderDAO.create(order);
		}else {
			order = new Order(order_id, customer_id, cost, shipment_date);
		}
		orderitemsubcontroller.createOrderItems(orderitems);
		return order;
	}
	/**
	 * Reads an existing order by taking in customer_id
	 */
	public List<Order> read(Long customer_id) {
		List<Order> orders = orderDAO.readCustomers(customer_id);
		LOGGER.info("Orders: ");
		PrintUtils.printDottedLine();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		PrintUtils.printLine();
		return orders;
	}
	
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		Order orderFound = null;
		String tryAgain = "N";
		do {
			orderFound = getOrderByOrderId();
			while(orderFound == null) {
				LOGGER.info("No order was found to update. Try Again (Y/N)?");
				tryAgain = utils.getString();
				if(tryAgain.equalsIgnoreCase("N")){
					return null;
				}
				orderFound = getOrderByOrderId();		
			}
		} while(!tryAgain.equalsIgnoreCase("N"));
		Long order_id = orderFound.getOrderId();	
		Long customer_idFound = orderFound.getCustomerId();
		Customer customer = customerDAO.read(customer_idFound);
		List<OrderItem> orderitems = orderitemDAO.readOrderItems(order_id);
		List<Item> items = orderitemsubcontroller.trackAndDeleteOrderItems(orderitems, order_id);
		Double costFound = orderFound.getCost();
		String shipment_dateFound = orderFound.getShipmentDate();
		printutils.printTicket(order_id, customer, items, orderitems, costFound, shipment_dateFound);
		Order order = orderDAO.update(createOrderItems(order_id));
		printutils.printUpdate(order);
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		Order orderFound = getOrderByOrderId();
		if(orderFound==null) {
			LOGGER.info("No order was found to delete. Try Again (Y/N)");
			if(utils.getString().equals("Y")) {
				delete();
			}
			return 0;
		}
		Long order_id = orderFound.getOrderId();
		orderitemsubcontroller.deleteOrderItems(order_id);
		LOGGER.info("Order Deleted:");
		PrintUtils.printDottedLine();
		PrintUtils.printLine();
		return orderDAO.delete(order_id);
	}
	/**
	 * Finds customer from user input
	 */
	public Customer getCustomerByCustomerId() {
		LOGGER.info("Please enter a customer ID");
		Long customer_id = utils.getLong();
		return customerDAO.read(customer_id);
	}
	/**
	 * Finds order from user input
	 */
	public Order getOrderByOrderId() {
		LOGGER.info("Please enter an order_id:");
		Long order_id = utils.getLong();
		return orderDAO.read(order_id);
	}
	/**
	 * Return variables from user input
	 */
	public String getShipmentDate() {
		LOGGER.info("Please enter a delivery date");
		return utils.getDate();
	}
	public Long getItemId() {
		LOGGER.info("Please enter an item ID");
		return utils.getLong();
	}
	public Long getItemQuantity() {
		LOGGER.info("Please enter an item quantity");
		return utils.getLong();
	}
}
