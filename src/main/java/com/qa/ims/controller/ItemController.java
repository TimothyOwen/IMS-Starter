package com.qa.ims.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.PrintUtils;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		LOGGER.info("Items: ");
		PrintUtils.printDottedLine();
		for (Item item : items) {
			LOGGER.info(item);
		}
		PrintUtils.printLine();
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		DecimalFormat df = new DecimalFormat("#.##");
		LOGGER.info("Please enter an item name");
		String item_name = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		price = Double.valueOf(df.format(price));
		Item item = itemDAO.create(new Item(item_name, price));
		LOGGER.info("Item Created");
		PrintUtils.printDottedLine();
		LOGGER.info(item);
		PrintUtils.printLine();
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		DecimalFormat df = new DecimalFormat("#.##");
		Item itemFound = getItem();
		Long item_id = itemFound.getItemId();
		LOGGER.info("Item Found:  {}", itemFound);
		LOGGER.info("Please enter an updated item name");
		String item_name = utils.getString();
		LOGGER.info("Please enter an update price");
		Double price = utils.getDouble();
		price = Double.valueOf(df.format(price));
		Item itemUpdated = itemDAO.update(new Item(item_id, item_name, price));
		LOGGER.info("Item Updated");
		PrintUtils.printDottedLine();
		LOGGER.info(itemUpdated);
		PrintUtils.printLine();
		return itemUpdated;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		Item itemFound = getItem();
		Long item_id = itemFound.getItemId();
		LOGGER.info("Item Deleted");
		PrintUtils.printDottedLine();
		PrintUtils.printLine();
		return itemDAO.delete(item_id);
	}
	/**
	 * Retrieves a customer using user input
	 */
	public Item getItem() {
		LOGGER.info("Please enter an item_id");
		Long item_id = utils.getLong();
		while(item_id > itemDAO.readLatest().getItemId() || item_id<1) {
			LOGGER.info("This is not a valid item_id. Please try again");
			item_id = utils.getLong();
		}
		return itemDAO.read(item_id);
	}
	

}
