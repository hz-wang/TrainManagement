package com.secw.JUnit;

import java.util.ArrayList;

import com.secw.entity.Route;
import com.secw.entity.Station;

public class RouteTest extends junit.framework.TestCase{
	public void testRoute() {
		ArrayList<Station> stationList = new ArrayList<Station>();
		stationList.add(new Station("A"));
		stationList.add(new Station("B"));
		stationList.add(new Station("C"));
		stationList.add(new Station("D"));
		stationList.add(new Station("E"));
		ArrayList<Integer> timeSpanList = new ArrayList<Integer>();
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		Route route = new Route(1, stationList, timeSpanList);
		assertEquals(1, route.getId());
		assertEquals(stationList, route.getStationList());
		assertEquals(timeSpanList, route.getTimeSpanList());
	}
}
