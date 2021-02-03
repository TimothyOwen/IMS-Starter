package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

public class OrderItemSubController {
	private OrderItemDAO orderitemDAO;
	private Utils utils;
	public OrderItemSubController(OrderItemDAO orderitemDAO, Utils utils) {
		super();
		this.orderitemDAO = orderitemDAO;
		this.utils = utils;
	}
	public void createOrderItems(List<OrderItem> orderitems) {
		for (OrderItem orderitem : orderitems) {
			orderitemDAO.create(orderitem);
		}
	}
	public void deleteOrderItems(Long order_id) {
		orderitemDAO.deleteByOrder(order_id);
	}
}
