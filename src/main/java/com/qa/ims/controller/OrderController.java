package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemDAO orderitemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderItemDAO orderitemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderitemDAO = orderitemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customer_id = utils.getLong();
		// Calculate cost by referencing item ID
		LOGGER.info("Please enter a cost of order");
		Double cost = utils.getDouble();
		LOGGER.info("Please enter a shipment date");
		String shipment_date = utils.getString();
		//
		///
		//
		//Add try block here, return to here if invalid input
		Order latestOrder = orderDAO.readLatest();
		Long next_order_id = (long) 1;
		if(latestOrder!=null) {
			next_order_id = latestOrder.getOrderId()+1;
		}
		List<OrderItem> orderitems = new ArrayList<OrderItem>();
		String user_finished = "N";
		do {
			LOGGER.info("Please enter an item ID");
			Long item_id = utils.getLong();
			LOGGER.info("Please enter how many items of these you want");
			int item_quantity = Math.toIntExact(utils.getLong());
			orderitems.add(new OrderItem(next_order_id, item_id, item_quantity));
			LOGGER.info("Have you finished ordering? (Y/N)");
			user_finished = utils.getString();
		} while (user_finished.equals("N"));
		Order order = orderDAO.create(new Order(customer_id, cost, shipment_date));
		//
		for(OrderItem orderitem: orderitems) {
			orderitemDAO.create(orderitem);
		}
		//
		///
		//
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the order_id of the order you would like to update");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter a customer ID");
		Long customer_id = utils.getLong();
		LOGGER.info("Please enter a cost of order");
		Double cost = utils.getDouble();
		LOGGER.info("Please enter a shipment date");
		String shipment_date = utils.getString();
		Order order = orderDAO.update(new Order(order_id, customer_id, cost, shipment_date));
		LOGGER.info("Order Updated");
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
		return orderDAO.delete(order_id);
	}

}
