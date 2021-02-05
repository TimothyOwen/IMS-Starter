package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Item; 
import com.qa.ims.utils.DBUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemDAOTest {

	private final ItemDAO itemDAO = new ItemDAO();

	@BeforeClass
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql");
		DBUtils.getInstance().init("src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Orange Juice", 2.03);
		assertEquals(created, itemDAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Apple Juice", 2.28));
		assertEquals(expected, itemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals((new Item(1L, "Apple Juice", 2.28)), itemDAO.readLatest());
	}
 
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals((new Item(ID, "Apple Juice", 2.28)), itemDAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(2L, "Cranberry Juice", 2.53);
		assertEquals(updated, itemDAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
	}
	@Test
	public void testDeleteInvalid() {
		assertEquals(0, itemDAO.delete(2000));
	}
	@AfterClass
	public static void deleteAll() {
		DBUtils.getInstance().init("src/test/resources/sql-dropall.sql");
	}
}