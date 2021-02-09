package com.qa.ims.utils;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {
	
	@Mock
	private Scanner scanner;
	
	@InjectMocks
	private Utils utils;
	
	@Test
	public void testGetString() {
		Mockito.when(scanner.nextLine()).thenReturn("Test");
		assertEquals("Test", utils.getString());
	}
	
	@Test
	public void testGetLong() {
		Mockito.when(scanner.nextLine()).thenReturn("Test","5");
		assertEquals("5", String.valueOf(utils.getLong()));
	}
	
	@Test
	public void testGetDouble() {
		Mockito.when(scanner.nextLine()).thenReturn("Test","5.23");
		assertEquals("5.23", String.valueOf(utils.getDouble()));
	}
	
	@Test
	public void testGetDate() {
		Mockito.when(scanner.nextLine()).thenReturn("1","41","21","1","11","11","40","21","21","02","02","21");
		assertEquals("2/2/21", String.valueOf(utils.getDate()));
	}
}
