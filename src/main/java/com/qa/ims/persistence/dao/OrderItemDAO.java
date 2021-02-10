package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAO implements Dao<OrderItem> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_item_id = resultSet.getLong("order_item_id");
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		int item_quantity = resultSet.getInt("item_quantity");
		return new OrderItem(order_item_id, order_id, item_id, item_quantity);
	}

	/**
	 * Reads all order items from the database
	 * 
	 * @return A list of order items
	 */
	@Override
	public List<OrderItem> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items");) {
			List<OrderItem> orderitems = new ArrayList<>();
			while (resultSet.next()) {
				orderitems.add(modelFromResultSet(resultSet));
			}
			return orderitems;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderItem readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items ORDER BY order_item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a order item in the database
	 * 
	 * @param orderitem - takes in a order item object. id will be ignored
	 */
	@Override
	public OrderItem create(OrderItem orderitem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders_items(order_id, item_id, item_quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderitem.getOrderId());
			statement.setLong(2, orderitem.getItemId());
			statement.setInt(3, orderitem.getItemQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	/**
	 * Reads a specific order_item 
	 * 
	 * @return
	 */
	@Override
	public OrderItem read(Long order_item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE order_item_id = ?");) {
			statement.setLong(1, order_item_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a order item in the database
	 * 
	 * @param orderitem - takes in a order item object, the id field will be used to
	 *                 update that order item in the database
	 * @return
	 */
	@Override
	public OrderItem update(OrderItem orderitem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders_items SET order_id = ?, item_id = ?, item_quantity = ? WHERE order_item_id = ?");) {
			statement.setLong(1, orderitem.getOrderId());
			statement.setLong(2, orderitem.getItemId());
			statement.setInt(3, orderitem.getItemQuantity()); 
			statement.setLong(4, orderitem.getOrderItemId());
			statement.executeUpdate();
			return read(orderitem.getOrderItemId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a order item in the database
	 * 
	 * @param orderitem_id - orderitem_id of the order item
	 */
	@Override
	public int delete(long order_item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE order_item_id = ?");) {
			statement.setLong(1, order_item_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	public int deleteByOrder(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders_items WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	/**
	 * Reads the order items from a specific order
	 * 
	 * @return
	 */
	public List<OrderItem> readOrderItems(Long order_id) {
		List<OrderItem> orderitems = new ArrayList<>();
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders_items WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				while(resultSet.next()) {
					orderitems.add(modelFromResultSet(resultSet));
					
				}
				return orderitems;
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return Collections.emptyList();
	}

}