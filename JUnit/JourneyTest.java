package com.secw.JUnit;

import java.util.ArrayList;

import com.secw.entity.Driver;
import com.secw.entity.Journey;
import com.secw.entity.Route;
import com.secw.entity.Station;
import com.secw.entity.Train;
import com.secw.manage.Controller;
import com.secw.manage.RouteManager;

public class JourneyTest extends junit.framework.TestCase{
	public void testJourney() {
		ArrayList<Station> stationList = new ArrayList<Station>();
		stationList.add(new Station("A"));
		stationList.add(new Station("A"));
		stationList.add(new Station("C"));
		stationList.add(new Station("D"));
		stationList.add(new Station("E"));
		ArrayList<Integer> timeSpanList = new ArrayList<Integer>();
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		timeSpanList.add(10);
		Train train = new Train();
		Driver driver = new Driver();
		Route route = new Route(1, stationList, timeSpanList);
		RouteManager.getManager().getAllRouteList().add(route);
		int startTime = 600;
		new Controller();
		Journey journey = new Journey(train, driver, route, startTime);
		assertEquals(train, journey.getTrain());
		assertEquals(driver, journey.getDriver());
		assertEquals(route, journey.getRoute());
		assertEquals(startTime, journey.getStartTime());
		assertEquals(650, journey.getReturnTime());
	}
}
