package com.qa.ims;

import org.junit.Test;
import org.mockito.Mockito;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CustomerAction;
import com.qa.ims.persistence.domain.Access;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

public class IMSTest {
	private Utils utils;
	private IMS ims = new IMS();
	@Test
	public void testSystemAdministrator() {
		IMS ims = new IMS();
		this.utils = ims.getUtils();
		Access[] accesss = new Access[] {Access.CUSTOMER, Access.ADMINISTRATOR, Access.EXIT};
		Domain[] domains = new Domain[] {Domain.CUSTOMER, Domain.ITEM, Domain.ORDER, Domain.RETURN};
		Action[] actions = new Action[] {Action.CREATE, Action.DELETE, Action.READ, Action.UPDATE, Action.RETURN};
		Mockito.mockStatic(Access.class);
		Mockito.mockStatic(Domain.class);
		Mockito.mockStatic(Action.class);
		Mockito.when(Access.getAccess(utils)).thenReturn(Access.ADMINISTRATOR, Access.EXIT);
		Mockito.when(Access.values()).thenReturn(accesss);
		Mockito.when(Domain.getDomain(utils)).thenReturn(Domain.ORDER, Domain.RETURN);
		Mockito.when(Domain.values()).thenReturn(domains);
		Mockito.when(Action.getAction(utils)).thenReturn(Action.RETURN);
		Mockito.when(Action.values()).thenReturn(actions);
		ims.imsAdministrator();
	}
	
	@Test
	public void testSystemCustomer() {
		this.utils = ims.getUtils();
		Access[] accesss = new Access[] {Access.CUSTOMER, Access.ADMINISTRATOR, Access.EXIT};
		CustomerAction[] actions = new CustomerAction[] {CustomerAction.A, CustomerAction.B, CustomerAction.C, CustomerAction.D, CustomerAction.E, CustomerAction.F, CustomerAction.RETURN};
		Mockito.mockStatic(CustomerAction.class);
		Mockito.when(Access.getAccess(utils)).thenReturn(Access.CUSTOMER, Access.EXIT);
		Mockito.when(Access.values()).thenReturn(accesss);
		Mockito.when(CustomerAction.getAction(utils)).thenReturn(CustomerAction.RETURN);
		Mockito.when(CustomerAction.values()).thenReturn(actions);
		ims.imsSystem();
	}
}