package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderItemTest {
	@Test
	public void testToString() {
		String expected = "Order Item ID: 1  Order ID: 1  Item ID: 1  Item Quantity: 2";
		OrderItem orderitem = new OrderItem(2L,2L,3);
		orderitem.setOrderItemId(1L);
		orderitem.setOrderId(1L);
		orderitem.setItemId(1L);
		orderitem.setItemQuantity(2);
		assertTrue(orderitem.toString().contains(expected));
	}  
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderItem.class).verify();
	}
	@Test
	public void testGetId() {
		Long id = 1L;
		OrderItem orderitem = new OrderItem(id,1L,1L,2);
		assertEquals(id, orderitem.getOrderItemId());
	}
	@Test
	public void testGetOrderItemName() {
		Object[] expected = {1L,1L,1L,2};
		Object[] actual = new Object[4];
		OrderItem orderitem = new OrderItem(1L,1L,1L,2);
		actual[0] = orderitem.getOrderItemId();
		actual[1] = orderitem.getOrderId();
		actual[2] = orderitem.getItemId();
		actual[3] = orderitem.getItemQuantity();
		assertEquals(expected, actual);
	}
	@Test
	public void testSetId() {
		Long id = 2L;
		OrderItem orderitem = new OrderItem(1L,1L,1L,2);
		OrderItem expected = new OrderItem(2L,1L,1L,2);
		orderitem.setOrderItemId(id);
		assertEquals(expected, orderitem);
	}
}
