package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * Customer Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum CustomerAction {
	A(": Change your customer information"), B(": View all available items"), C(": View your orders"),
	D(": Create an order"), E(": Update one of your orders"), F(": Delete order"), RETURN("");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	CustomerAction(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getName() {
		return this.name();
	}
	public String getDescription() {
		
		return this.name() + this.description;
	}

	/**
	 * Prints out all possible actions
	 */
	public static void printActions() {
		for (CustomerAction customerAction : CustomerAction.values()) {
			String customerActionString = customerAction.getDescription();
			LOGGER.info(customerActionString);
			LOGGER.info("");
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static CustomerAction getAction(Utils utils) {
		CustomerAction action = null;
		do {
			try {
				action = CustomerAction.valueOf(utils.getString().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (action == null);
		return action;
	}
}