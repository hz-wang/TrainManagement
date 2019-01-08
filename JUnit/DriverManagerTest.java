package com.secw.JUnit;

import com.secw.entity.Driver.DriverStatus;
import com.secw.manage.DriverManager;

public class DriverManagerTest extends junit.framework.TestCase{
	
	public void testDriverManager(){
		DriverManager.getManager().add(1, DriverStatus.available);
		assertEquals(DriverStatus.available, DriverManager.getManager().getDriverStatus(0));
		assertTrue(DriverManager.getManager().remove(0));
	}
}
