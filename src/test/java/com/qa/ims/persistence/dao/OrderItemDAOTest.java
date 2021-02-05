package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.OrderItem; 
import com.qa.ims.utils.DBUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderItemDAOTest {

	private final OrderItemDAO orderitemDAO = new OrderItemDAO();

	@BeforeClass
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql");
		DBUtils.getInstance().init("src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final OrderItem created = new OrderItem(2L, 1L, 1L, 4);
		assertEquals(created, orderitemDAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<OrderItem> expected = new ArrayList<>();
		expected.add(new OrderItem(1L, 1L, 1L, 2));
		assertEquals(expected, orderitemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals((new OrderItem(1L, 1L, 1L, 2)), orderitemDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals((new OrderItem(1L, 1L, 1L, 2)), orderitemDAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final OrderItem updated = new OrderItem(2L, 1L, 2L, 4);
		assertEquals(updated, orderitemDAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, orderitemDAO.delete(1));
	}
	@Test
	public void testDeleteInvalid() {
		assertEquals(0, orderitemDAO.delete(2000));
	}
	@AfterClass
	public static void deleteAll() {
		DBUtils.getInstance().init("src/test/resources/sql-dropall.sql");
	}
}