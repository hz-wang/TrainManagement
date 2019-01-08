package com.secw.JUnit;

import com.secw.manage.StationManager;

public class StationManagerTest extends junit.framework.TestCase{
	public void testStationManager(){
		StationManager.getManager().add(1, "A");
		assertEquals("A", StationManager.getManager().getStation(0));
		assertTrue(StationManager.getManager().remove(0));
	}
}
