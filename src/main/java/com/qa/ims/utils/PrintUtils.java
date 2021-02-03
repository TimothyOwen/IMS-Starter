package com.qa.ims.utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;

public class PrintUtils {
	public static final Logger LOGGER = LogManager.getLogger();

	public static void printLine() {
		LOGGER.info("______________________________");
		LOGGER.info("");
	}
	public static void printDottedLine() {
		LOGGER.info("--------------");
		LOGGER.info("");
	}

	public void printUpdate(Order order) {
		LOGGER.info("Order Updated: ");
		printDottedLine();
		LOGGER.info(order);
		printLine();
	}
	public static void printDelete() {
		LOGGER.info("Order Deleted: ");
		printDottedLine();
		printLine();
	}
	public void printTicket(Long order_id, Customer customer, List<Item> items, List<OrderItem> orderitems, Double cost, String shipment_date) {
		printDottedLine();
		LOGGER.info("Order ID: "+order_id);
		printDottedLine();
		LOGGER.info("Customer Name: "+customer.getFirstName()+" "+customer.getSurname());
		printDottedLine();
		for (int i = 0; i < items.size(); i++) {
			LOGGER.info(items.get(i).getItemName() + " x" + orderitems.get(i).getItemQuantity());
		}
		LOGGER.info("Total: £ " + cost);
		printDottedLine();
		LOGGER.info("Delivery Date: "+shipment_date);
		printDottedLine();
	}
}
