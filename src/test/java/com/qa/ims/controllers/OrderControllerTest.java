package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.controller.OrderItemSubController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private OrderItemDAO orderitemDAO;
	
	@Mock
	private OrderItemSubController orderitemsubcontroller;

	@InjectMocks
	private OrderController orderController;

	@Test
	public void testCreate() {
		final Long order_id = 2L;
		final Long customer_id = 1L;
		final Double cost = 20.52;
		final String shipment_date = "11/09/21";
		final Long item_id = 1L;
		final Long item_quantity = 9L;
		Order old = new Order(1L, 1L, 25.73, "03/07/21");
		Order created = new Order(order_id, customer_id, cost, shipment_date);
		Customer customer = new Customer(1L, "jordan", "harrison");
		Item item = new Item(1L, "Apple Juice", 2.28D);

		Mockito.when(utils.getLong()).thenReturn(customer_id, item_id, item_quantity);
		Mockito.when(utils.getDate()).thenReturn(shipment_date);
		Mockito.when(utils.getString()).thenReturn("Y");
		Mockito.when(customerDAO.read(customer_id)).thenReturn(customer);
		Mockito.when(customerDAO.readLatest()).thenReturn(customer);
		Mockito.when(itemDAO.read(item_id)).thenReturn(item);
		Mockito.when(itemDAO.readLatest()).thenReturn(item);
		Mockito.when(orderDAO.readLatest()).thenReturn(old);
		Mockito.when(orderDAO.create(created)).thenReturn(created);

		assertEquals(created, this.orderController.create());
		
		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDate();
		Mockito.verify(this.customerDAO, Mockito.times(1)).read(customer_id);
		Mockito.verify(this.itemDAO, Mockito.times(1)).read(item_id);
		Mockito.verify(this.orderDAO, Mockito.times(1)).readLatest();
		Mockito.verify(this.orderDAO, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 25.73, "03/07/21"));
		Mockito.when(orderDAO.readAll()).thenReturn(orders);

		assertEquals(orders, this.orderController.readAll());

		Mockito.verify(this.orderDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Long order_id = 1L;
		Order old = new Order(1L, 1L, 25.73, "03/07/21");
		Order updated = new Order(1L, 1L, 20.52, "11/09/21");
		Long customer_id = 1L;
		Customer customer = new Customer(1L, "jordan", "harrison");
		Long item_id = 1L;
		Long item_quantity = 9L;
		Item item = new Item(1L, "Apple Juice", 2.28D);
		Long orderitem_id = 1L;
		OrderItem orderitem = new OrderItem(1L,1L,1L,2);
		List<Item> items = new ArrayList<Item>();
		List<OrderItem> orderitems = new ArrayList<>();
		items.add(item);
		orderitems.add(orderitem);
		
		Mockito.when(utils.getLong()).thenReturn(order_id, customer_id, item_id, item_quantity);
		Mockito.when(utils.getDate()).thenReturn("11/09/21");
		Mockito.when(utils.getString()).thenReturn("Y");
		Mockito.when(customerDAO.read(customer_id)).thenReturn(customer);
		Mockito.when(customerDAO.readLatest()).thenReturn(customer);
		Mockito.when(itemDAO.read(item_id)).thenReturn(item);
		Mockito.when(itemDAO.readLatest()).thenReturn(item);
		Mockito.when(orderDAO.read(order_id)).thenReturn(old);
		Mockito.when(orderDAO.readLatest()).thenReturn(old);
		Mockito.when(orderDAO.update(updated)).thenReturn(updated);
		Mockito.when(orderitemDAO.readOrderItems(order_id)).thenReturn(orderitems);
		Mockito.when(orderitemDAO.delete(1L)).thenReturn(0);
		
		
		assertEquals(updated, this.orderController.update());
		
		Mockito.verify(this.utils, Mockito.times(4)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.customerDAO, Mockito.times(2)).read(order_id);
		Mockito.verify(this.itemDAO, Mockito.times(2)).read(item_id);
		Mockito.verify(this.orderDAO, Mockito.times(2)).read(order_id);
		Mockito.verify(this.orderDAO, Mockito.times(1)).update(updated);
		Mockito.verify(this.orderitemDAO, Mockito.times(1)).readOrderItems(order_id);
		Mockito.verify(this.orderitemDAO, Mockito.times(1)).delete(orderitem_id);
		
	}

	@Test
	public void testUpdateNotFound() {
		Long order_id = 2L;
		Mockito.when(this.utils.getLong()).thenReturn(order_id);
		Mockito.when(this.utils.getString()).thenReturn("Y", "N");
		
		assertEquals(null, this.orderController.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.orderDAO, Mockito.times(2)).readLatest();
	}
	
	@Test
	public void testDelete() {
		final long order_id = 1L;
		Order old = new Order(1L, 1L, 25.73, "03/07/21");
		Mockito.when(utils.getLong()).thenReturn(order_id);
		Mockito.when(orderDAO.read(order_id)).thenReturn(old);
		Mockito.when(orderDAO.delete(order_id)).thenReturn(1);

		assertEquals(1L, this.orderController.delete());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.orderDAO, Mockito.times(1)).read(order_id);
		Mockito.verify(this.orderDAO, Mockito.times(1)).delete(order_id);
	}
	
	@Test
	public void testDeleteNotFound() {
		final long order_id = 2L;
		Mockito.when(utils.getLong()).thenReturn(order_id);
		Mockito.when(utils.getString()).thenReturn("Y","N");
		
		assertEquals(0, this.orderController.delete());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.orderDAO, Mockito.times(2)).readLatest();
	}
	
	@Test
	public void testReadOrder() {
		final long customer_id = 1L;
		Order order1 = new Order(1L, 1L, 25.73, "03/07/21");
		Order order2 = new Order(2L, 1L, 20.52, "11/09/21");
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		Mockito.when(orderDAO.readCustomers(customer_id)).thenReturn(orders);
		
		assertEquals(orders, this.orderController.read(customer_id));

		Mockito.verify(this.orderDAO, Mockito.times(1)).readCustomers(customer_id);
	}
	
	
}
