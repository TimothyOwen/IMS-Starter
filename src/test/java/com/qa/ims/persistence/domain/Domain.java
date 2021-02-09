package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Domain {

	CUSTOMER, ITEM, ORDER, RETURN;

	public static final Logger LOGGER = LogManager.getLogger();

	public String getDescription() {
		return this.name();
	}

	public static void printDomains() {
		for (Domain domain : Domain.values()) {
			LOGGER.info(domain.getDescription());
		}
	}
	public static Domain getDomain(Utils utils) {
		Domain domain;
		while (true) {
			try {
				domain = Domain.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return domain;
	}
}