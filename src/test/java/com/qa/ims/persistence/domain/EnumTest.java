package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CustomerAction;

public class EnumTest {
	@Test
	public void testAction() {
		Action.printActions(Domain.CUSTOMER);
		String expected = "CREATE: To save a new entity into the database";
		assertEquals(expected, Action.CREATE.getDescription());
	}
	@Test
	public void testCustomerAction() {
		CustomerAction.printActions();
		String expected = "A: Change your customer information";
		assertEquals(expected, CustomerAction.A.getDescription());
	}
	@Test
	public void testAccess() {
		Access.printAccess();
		String expected = "CUSTOMER";
		assertEquals(expected, Access.CUSTOMER.getName());
	}
	@Test
	public void testDomain() {
		Domain.printDomains();
		String expected = "CUSTOMER";
		assertEquals(expected, Domain.CUSTOMER.getDescription());
	}
}
