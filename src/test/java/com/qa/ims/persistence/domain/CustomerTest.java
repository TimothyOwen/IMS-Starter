package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	@Test
	public void testToString() {
		String expected = "Customer ID: 1  First Name: Jeff  Surname: Bezos";
		Customer customer = new Customer(1L,"Jeff","Bezos");
		assertTrue(customer.toString().contains(expected));
	}
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	@Test
	public void testGetId() {
		Long id = 1L;
		Customer customer = new Customer(id,"Jeff","Bezos");
		assertEquals(id, customer.getCustomerId());
	}
	@Test
	public void testGetNames() {
		String[] expected = {"Jeff","Bezos"};
		String[] actual = new String[2];
		Customer customer = new Customer("Jeff","Bezos");
		actual[0] = customer.getFirstName();
		actual[1] = customer.getSurname();
		assertArrayEquals(expected, actual);
	}
	@Test
	public void testSet() {
		Long id = 2L;
		Customer customer = new Customer("Jeff","Bezos");
		Customer expected = new Customer(2L,"Jeff","Bezos");
		customer.setCustomerId(id);
		assertEquals(expected, customer);
	}
}
