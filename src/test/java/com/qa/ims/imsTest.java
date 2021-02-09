package com.qa.ims;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.controller.OrderItemSubController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Access;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerDomain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class imsTest {
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private OrderItemDAO orderitemDAO;
	
	@Mock
	private CustomerController customerController;
	
	@Mock
	private ItemController itemController;
	
	@Mock
	private OrderController orderController;
	
	@Mock
	private OrderItemSubController orderitemsubcontroller;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private IMS ims;
	
	@Test
	public void testSystem() {
		ims = new IMS();
		utils = ims.getUtils();
		ims.imsCustomer();
		Mockito.when(CustomerDomain.getCustomerDomain(utils)).thenReturn(CustomerDomain.valueOf("CUSTOMER"));
	}
	
	
}