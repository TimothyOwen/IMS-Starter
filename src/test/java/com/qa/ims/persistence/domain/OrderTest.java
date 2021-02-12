package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
	@Test
	public void testToString() {
		String expected = "Order ID: 1  Customer ID: 1  Cost: 2.0  Shipment Date: 21/02/21";
		Order order = new Order(2L,3D,"21/02/22");
		order.setOrderId(1L);
		order.setCustomerId(1L);
		order.setCost(2D);
		order.setShipmentDate("21/02/21");
		assertTrue(order.toString().contains(expected));
	}  
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	@Test
	public void testGetId() {
		Long id = 1L;
		Order order = new Order(id,1L,3D,"21/02/21");
		assertEquals(id, order.getOrderId());
	}
	@Test
	public void testGetOrderName() {
		Object[] expected = {1L,3D,"21/02/21"};
		Object[] actual = new Object[3];
		Order order = new Order(1L,3D,"21/02/21");
		actual[0] = order.getCustomerId();
		actual[1] = order.getCost();
		actual[2] = order.getShipmentDate();
		assertArrayEquals(expected, actual);
	}
	@Test
	public void testSetId() {
		Long id = 2L;
		Order order = new Order(1L,3D,"21/02/21");
		Order expected = new Order(2L,1L,3D,"21/02/21");
		order.setOrderId(id);
		assertEquals(expected, order);
	}
}
