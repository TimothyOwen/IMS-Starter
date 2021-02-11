package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	@Test
	public void testToString() {
		String expected = "Item ID: 1  Item Name: Shampoo  Price: 2.99";
		Item item = new Item(1L,"",0D);
		item.setItemName("Shampoo");
		item.setPrice(2.99D);
		assertTrue(item.toString().contains(expected));
	} 
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	@Test
	public void testGetId() {
		Long id = 1L;
		Item item = new Item(id,"Shampoo",2.99D);
		assertEquals(id, item.getItemId());
	}
	@Test
	public void testGetItemName() {
		Object[] expected = {"Shampoo",2.99};
		Object[] actual = new Object[2];
		Item item = new Item("Shampoo",2.99);
		actual[0] = item.getItemName();
		actual[1] = item.getPrice();
		assertArrayEquals(expected, actual);
	}
	@Test
	public void testSetId() {
		Long id = 2L;
		Item item = new Item("Shampoo",2.99);
		Item expected = new Item(2L,"Shampoo",2.99);
		item.setItemId(id);
		assertEquals(expected, item);
	}
}
