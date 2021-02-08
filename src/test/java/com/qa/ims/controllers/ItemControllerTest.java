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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO itemDAO;

	@InjectMocks
	private ItemController itemController;

	@Test
	public void testCreate() {
		final String item_name = "Orange Juice";
		final Double price = 2.42D;
		final Item created = new Item(item_name,price);
		Mockito.when(utils.getString()).thenReturn(item_name);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(itemDAO.create(created)).thenReturn(created);

		assertEquals(created, this.itemController.create());

		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDAO, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Apple Juice", 2.28D));
		Mockito.when(itemDAO.readAll()).thenReturn(items);

		assertEquals(items, this.itemController.readAll());

		Mockito.verify(this.itemDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item old = new Item(1L, "Apple juice", 2.28D);
		Item updated = new Item(1L, "Orange Juice", 2.42D);
		Long id = 1L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(itemDAO.read(id)).thenReturn(old);
		Mockito.when(utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(utils.getDouble()).thenReturn(updated.getPrice());
		Mockito.when(itemDAO.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.itemController.update());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDAO, Mockito.times(1)).read(id);
		Mockito.verify(this.itemDAO, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testUpdateNotFound() {
		Long id = 2L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(itemDAO.read(id)).thenReturn(null);
		Mockito.when(utils.getString()).thenReturn("Y", "N");
		
		assertEquals(null, this.itemController.update());
		
		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.itemDAO, Mockito.times(2)).read(id);
	}
	

	@Test
	public void testDelete() {
		final long id = 1L;
		Item old = new Item(1L, "Apple juice", 2.28D);
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(itemDAO.read(id)).thenReturn(old);
		Mockito.when(itemDAO.delete(id)).thenReturn(1);

		assertEquals(1L, this.itemController.delete());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.itemDAO, Mockito.times(1)).read(id);
		Mockito.verify(this.itemDAO, Mockito.times(1)).delete(id);
	}
	
	@Test
	public void testDeleteNotFound() {
		final long id = 2L;
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(itemDAO.read(id)).thenReturn(null);
		Mockito.when(utils.getString()).thenReturn("Y","N");
		
		assertEquals(0, this.itemController.delete());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.itemDAO, Mockito.times(2)).read(id);
	}
}
