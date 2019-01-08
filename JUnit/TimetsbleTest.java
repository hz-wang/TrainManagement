package com.secw.JUnit;

import java.util.ArrayList;

import com.secw.entity.Driver;
import com.secw.entity.Journey;
import com.secw.entity.Route;
import com.secw.entity.Station;
import com.secw.entity.Timetable;
import com.secw.entity.Train;

public class TimetsbleTest extends junit.framework.TestCase{
	public void testTimetable() {
		ArrayList<Journey> journeyList = new ArrayList<Journey>();
		Timetable timeTable = new Timetable();
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
		Train train = new Train();
		Driver driver = new Driver();
		Route route = new Route(1, stationList, timeSpanList);
		int startTime = 600;
		Journey journey = new Journey(train, driver, route, startTime);
		journeyList.add(journey);
		assertEquals(journeyList, timeTable.addJourney(journey));
	}
}
