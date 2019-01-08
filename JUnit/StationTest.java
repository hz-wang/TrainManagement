package com.secw.JUnit;

import com.secw.entity.Station;

public class StationTest extends junit.framework.TestCase{
	public void testStation() {
		Station station1 = new Station(1, "G");
		assertEquals(1, station1.getId());
		assertEquals("G", station1.getName());
	}
}
