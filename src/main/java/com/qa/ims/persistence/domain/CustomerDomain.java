package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum CustomerDomain {

	CUSTOMER("Change customer information"), ITEM("View available items"), 
	ORDER("Create or update orders"), RETURN("To close current menu");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private CustomerDomain(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.name()+" : "+this.description;
	}

	public static void printDomains() {
		for (CustomerDomain domain : CustomerDomain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}

	public static CustomerDomain getCustomerDomain(Utils utils) {
		CustomerDomain domain;
		while (true) {
			try {
				domain = CustomerDomain.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}

}
