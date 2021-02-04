package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderDAOTest {

	private final OrderDAO orderDAO = new OrderDAO();

	@BeforeClass
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql");
		DBUtils.getInstance().init("src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1L, 33.86, "23/11/21");
		assertEquals(created.hashCode(), orderDAO.create(created).hashCode());
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L , 25.73, "03/07/21"));
		assertEquals(expected.hashCode(), orderDAO.readAll().hashCode());
	}

	@Test
	public void testReadLatest() {
		assertEquals((new Order(1L, 1L, 25.73, "03/07/21")).hashCode(), orderDAO.readLatest().hashCode());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals((new Order(ID, 1L, 25.73, "03/07/21")).hashCode(), orderDAO.read(ID).hashCode());
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(2L, 1L, 400D, "26/12/21");
		assertEquals(updated.hashCode(), orderDAO.update(updated).hashCode());

	}

	@Test
	public void testDelete() {
		assertEquals(1, orderDAO.delete(1));
	}
	@Test
	public void testDeleteInvalid() {
		assertEquals(0, orderDAO.delete(2000));
	}
	@AfterClass
	public static void deleteAll() {
		DBUtils.getInstance().init("src/test/resources/sql-dropall.sql");
	}
}