package com.secw.entity;

import java.util.ArrayList;

/**
 * Title		: Route.java
 * Description	: This class is to store route information
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Route {
	private int id; //The identification of route
	private static int count = 0; //Auto-increment number to set id
	
	private ArrayList<Station> stationList = new ArrayList<Station>();
	private ArrayList<Integer> timeSpanList = new ArrayList<Integer>();
	
	/**
	 * The constructor of route class
	 * It reads two parameters and uses these to initialize attributes.
	 * @param stationList
	 * @param timeSpanList
	 */
	public Route(ArrayList<Station> stationList, ArrayList<Integer> timeSpanList) {
		super();
		this.id = ++count;
		this.stationList = stationList;
		this.timeSpanList = timeSpanList;
	}
	
	/**
	 * The constructor of route class
	 * It reads three parameters and uses these to initialize attributes.
	 * Mainly used to read from files.
	 * @param id
	 * @param stationList
	 * @param timeSpanList
	 */
	public Route(int id, ArrayList<Station> stationList, ArrayList<Integer> timeSpanList) {
		super();
		count = count > id ? count : id;
		this.id = id;
		this.stationList = stationList;
		this.timeSpanList = timeSpanList;
	}
	
	/**
	 * The getId method of route class
	 * This method is called when the id of 
	 * route is needed.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getStationList method of route class
	 * This method is called when the list of
	 * stations is needed.
	 * @return stationList
	 */
	public ArrayList<Station> getStationList() {
		return stationList;
	}

	/**
	 * The getTimeSpanList method of route class
	 * This method is called when the list of
	 * time spans is needed.
	 * @return timeSpanList
	 */
	public ArrayList<Integer> getTimeSpanList() {
		return timeSpanList;
	}
	
	/**
	 * The setStationList of route class
	 * This method is called when the list of
	 * stations is going to change.
	 * @param stationList
	 */
	public void setStationList(ArrayList<Station> stationList) {
		this.stationList = stationList;
	}

	/**
	 * The setTimeSpanList of route class
	 * This method is called when the list of
	 * time spans is going to change.
	 * @param timeSpanList
	 */
	public void setTimeSpanList(ArrayList<Integer> timeSpanList) {
		this.timeSpanList = timeSpanList;
	}

	/**
	 * The toString method of route class
	 * This method is called when station list
	 * in string format is needed.
	 * @return result
	 */
	@Override
	public String toString(){
		String result = "central";
		for(Station s : stationList){
			result += "-->" + s.getName();
		}
		return result;
	}
}
