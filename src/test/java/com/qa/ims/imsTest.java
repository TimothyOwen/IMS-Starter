package com.qa.ims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CustomerAction;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.domain.Access;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class imsTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private CustomerController customers;
	
	@Mock
	private ItemController items;
	
	@Mock
	private OrderController orders;
	
	@InjectMocks
	private IMS ims = new IMS();
	
	@Test
	public void testSystemDeveloper() {
		Access[] accesss = new Access[] {Access.CUSTOMER, Access.DEVELOPER, Access.EXIT};
		Domain[] domains = new Domain[] {Domain.CUSTOMER, Domain.ITEM, Domain.ORDER, Domain.RETURN};
		Action[] actions = new Action[] {Action.CREATE, Action.DELETE, Action.READ, Action.UPDATE, Action.RETURN};
		Mockito.mockStatic(Access.class);
		Mockito.mockStatic(Domain.class);
		Mockito.mockStatic(Action.class);
		this.utils = ims.getUtils();
		Mockito.when(Access.getAccess(utils)).thenReturn(Access.DEVELOPER, Access.EXIT);
		Mockito.when(Access.values()).thenReturn(accesss);
		Mockito.when(Domain.getDomain(utils)).thenReturn(Domain.ORDER, Domain.RETURN);
		Mockito.when(Domain.values()).thenReturn(domains);
		Mockito.when(Action.getAction(utils)).thenReturn(Action.RETURN);
		Mockito.when(Action.values()).thenReturn(actions);
		ims.imsDeveloper();
	}
	
	@Test
	public void testSystemCustomer() {
		Access[] accesss = new Access[] {Access.CUSTOMER, Access.DEVELOPER, Access.EXIT};
		CustomerAction[] actions = new CustomerAction[] {CustomerAction.A, CustomerAction.B, CustomerAction.C, CustomerAction.D, CustomerAction.E, CustomerAction.F, CustomerAction.RETURN};
		Mockito.mockStatic(CustomerAction.class);
		this.utils = ims.getUtils();
		Mockito.when(Access.getAccess(utils)).thenReturn(Access.CUSTOMER, Access.EXIT);
		Mockito.when(Access.values()).thenReturn(accesss);
		Mockito.when(CustomerAction.getAction(utils)).thenReturn(CustomerAction.RETURN);
		Mockito.when(CustomerAction.values()).thenReturn(actions);
		ims.imsSystem();
	}
}