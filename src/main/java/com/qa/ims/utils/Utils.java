package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Utils {

	private static final Logger LOGGER = LogManager.getLogger();

	private final Scanner scanner;

	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Utils() {
		scanner = new Scanner(System.in);
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		String stringInput = scanner.nextLine();
		PrintUtils.printLine();
		return stringInput;
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}
	
	public String getDate() {
		Long Day = (long) 0;
		Long Month = (long) 0;
		Long Year = (long) 0;
		do {
			try {
				LOGGER.info("Please enter the day (dd)");
				Day = getLong();
				LOGGER.info("Please enter the month (mm)");
				Month = getLong();
				LOGGER.info("Please enter the year (yy)");
				Year = getLong();
			} catch (NumberFormatException nfe) {
				LOGGER.info("Invalid input - Please enter again");
			}
		} while (Day == 0 || Month == 0 || Year == 0 || (Day>31 || Day<1) || (Month>12 || Month<1) || (Year<21));
		return String.valueOf(Day)+"/"+String.valueOf(Month)+"/"+String.valueOf(Year);
	}
}
