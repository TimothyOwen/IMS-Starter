package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Access {

	CUSTOMER("Please enter your Customer ID"), DEVELOPER("Please enter password to gain developer access"), STOP("Exit");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	private Access(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name();
	}

	public static void printAccess() {
		for (Access access : Access.values()) {
			LOGGER.info(access.getName());
		}
	}

	public static Access getAccess(Utils utils) {
		Access access;
		while (true) {
			try {
				access = Access.valueOf(utils.getString().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return access;
	}

}
