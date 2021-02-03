package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.OrderItem;

public class OrderItemSubController {
	private OrderItemDAO orderitemDAO;
	private ItemDAO itemDAO;
	
	public OrderItemSubController(ItemDAO itemDAO, OrderItemDAO orderitemDAO) {
		super();
		this.itemDAO = itemDAO;
		this.orderitemDAO = orderitemDAO;
	}
	public void createOrderItems(List<OrderItem> orderitems) {
		for (OrderItem orderitem : orderitems) {
			orderitemDAO.create(orderitem);
		}
	}
	public List<OrderItem> readOrderItems(Long order_id) {
		List<OrderItem> orderitems = orderitemDAO.readOrderItems(order_id);
		return orderitems;
	}
	public void deleteOrderItems(Long order_id) {
		orderitemDAO.deleteByOrder(order_id);
	}
	//Deletes order items but saves them first
	public List<Item> trackAndDeleteOrderItems(List<OrderItem> orderitems, Long order_id){
		List<Item> items = new ArrayList<Item>();
		for(OrderItem orderitem: orderitems) {
			orderitemDAO.delete(orderitem.getOrderItemId());
			items.add(itemDAO.read(orderitem.getItemId()));
		};
		return items;
	}
}
