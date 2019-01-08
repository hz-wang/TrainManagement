package com.secw.JUnit;
import com.secw.entity.Driver;
import com.secw.entity.Driver.DriverStatus;

public class DriverTest extends junit.framework.TestCase{
	
	public void testDriver() {
		Driver driver1 = new Driver(1, DriverStatus.available);
		assertEquals(1, driver1.getId());
		assertEquals(DriverStatus.available, driver1.getStatus());
		
		Driver driver2 = new Driver(2, DriverStatus.unavailable);
		assertEquals(2, driver2.getId());
		assertEquals(DriverStatus.unavailable, driver2.getStatus());
	}
}
