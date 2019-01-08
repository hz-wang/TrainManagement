package com.secw.entity;

import java.util.ArrayList;
import java.util.LinkedList;

import com.secw.tool.Calculate;

/**
 * Title		: Timetable.java
 * Description	: This class is to work out timetable
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Timetable {
	public ArrayList<Journey> journeyList = new ArrayList<Journey>(); 

	/**
	 * The addJourney method of timetable class
	 * This method is called when new journey comes 
	 * and need to be stored.
	 * @return journeyList
	 */
	public ArrayList<Journey> addJourney(Journey journry){
		journeyList.add(journry);
		return journeyList;
	}
	
	/**
	 * The getOutWardTimetable method of timetable class
	 * This method is called when outward timetable is needed.
	 * @return currentTimeList
	 */
	public ArrayList<String> getOutWardTimetable(Journey journey){
		ArrayList<String> currentTimeList = new ArrayList<String>();
		
		int currentTime = journey.getStartTime();
		for (int i = 0; i < journey.getRoute().getTimeSpanList().size(); i++) {
			currentTime += journey.getRoute().getTimeSpanList().get(i);
			currentTimeList.add(Calculate.minToHour(currentTime));
		}
		return currentTimeList;
	}
	
	/**
	 * The getReturnTimetable method of timetable class
	 * This method is called when return timetable is needed.
	 * @return currentTimeList
	 */
	public LinkedList<String> getReturnTimetable(Journey journey){
		LinkedList<String> currentTimeList = new LinkedList<String>();
		
		int currentTime = journey.getReturnTime();
		for (int i = journey.getRoute().getTimeSpanList().size() - 1; i >= 0; i--) {
			currentTime += journey.getRoute().getTimeSpanList().get(i);
			currentTimeList.addFirst(Calculate.minToHour(currentTime));
		}
		return currentTimeList;
	}
}
