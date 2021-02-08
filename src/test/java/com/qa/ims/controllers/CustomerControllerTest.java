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

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO customerDAO;

	@InjectMocks
	private CustomerController customerController;

	@Test
	public void testCreate() {
		final String first_name = "barry", surname = "scott";
		final Customer created = new Customer(first_name, surname);
		Mockito.when(utils.getString()).thenReturn(first_name, surname);
		Mockito.when(customerDAO.create(created)).thenReturn(created);

		assertEquals(created, this.customerController.create());

		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.customerDAO, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "jordan", "harrison"));
		Mockito.when(customerDAO.readAll()).thenReturn(customers);

		assertEquals(customers, this.customerController.readAll());

		Mockito.verify(this.customerDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Customer old = new Customer(1L, "jordan", "harrison");
		Customer updated = new Customer(1L, "chris", "perrins");
		Long id = 1L;
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(customerDAO.read(id)).thenReturn(old);
		Mockito.when(utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		Mockito.when(customerDAO.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.customerController.update());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.customerDAO, Mockito.times(1)).read(id);
		Mockito.verify(this.customerDAO, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testUpdateNotFound() {
		Long id = 2L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(customerDAO.read(id)).thenReturn(null);
		Mockito.when(utils.getString()).thenReturn("Y", "N");
		
		assertEquals(null, this.customerController.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.customerDAO, Mockito.times(2)).read(id);
	}
	

	@Test
	public void testDelete() {
		final long id = 1L;
		Customer old = new Customer(1L, "jordan", "harrison");
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(customerDAO.read(id)).thenReturn(old);
		Mockito.when(customerDAO.delete(id)).thenReturn(1);

		assertEquals(1L, this.customerController.delete());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.customerDAO, Mockito.times(1)).read(id);
		Mockito.verify(this.customerDAO, Mockito.times(1)).delete(id);
	}
	
	@Test
	public void testDeleteNotFound() {
		final long id = 2L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(customerDAO.read(id)).thenReturn(null);
		Mockito.when(utils.getString()).thenReturn("Y","N");
		
		assertEquals(0, this.customerController.delete());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.customerDAO, Mockito.times(2)).read(id);
	}
}
