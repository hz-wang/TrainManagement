package com.secw.manage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.secw.entity.Route;
import com.secw.entity.Station;
import com.secw.entity.Timetable;
import com.secw.tool.IO;

/**
 * Title		: RouteManager.java
 * Description	: This class is to manage all routes
 * @author Zhang Yufeng, Wang Haozhe
 */
public class RouteManager extends Manager{
	private static HashMap<Route, Timetable> hashMap = new HashMap<Route, Timetable>();
	
	private static ArrayList<Route> allRouteList = new ArrayList<Route>();

	private static RouteManager routeManager = null;
	
	/**
	 * The constructor of routeManager class
	 * It can only be created privately.
	 * @exception IOException
	 */
	private RouteManager() {
		try {
			
			ArrayList<String> result = IO.readData("data/Route.txt");
			for(int i = 0; i < result.size(); i++){
				String temp[] = result.get(i).split("#");
				ArrayList<Station> stationList = new ArrayList<Station>();
				ArrayList<Integer> timeSpanList = new ArrayList<Integer>();
				String sep[] = temp[1].split(",");
				for(int j = 0; j < sep.length; j++){
					if(j % 2 == 1){
						stationList.add(StationManager.getManager().egodic(sep[j]));
					}
					else {
						timeSpanList.add(Integer.parseInt(sep[j]));
					}
				}
				add(Integer.parseInt(temp[0]), stationList, timeSpanList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The getManager method of routeManager class
	 * The instance of the class can only be created once
	 * @return routeManager
	 */
	public static RouteManager getManager(){
		if(routeManager == null){
			routeManager = new RouteManager();
		}
		
		return routeManager;
	}
	
	/**
	 * The add method of routeManager class
	 * This method is called when new route is created.
	 * @param stationList
	 * @param timeSpanList
	 */
	public void add(ArrayList<Station> stationList, ArrayList<Integer> timeSpanList){
		Route route = new Route(stationList, timeSpanList);
		hashMap.put(route, new Timetable());
		allRouteList.add(route);
	}
	
	/**
	 * The add method of routeManager class
	 * This method is called when new route is created.
	 * Mainly used to read from files.
	 * @param id
	 * @param stationList
	 * @param timeSpanList
	 */
	public void add(int id, ArrayList<Station> stationList, ArrayList<Integer> timeSpanList){
		Route route = new Route(id, stationList, timeSpanList);
		hashMap.put(route, new Timetable());
		allRouteList.add(route);
	}
	
	/**
	 * The remove method of routeManager class
	 * This method is called when route need to be deleted.
	 * @param route
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(Route route){
		allRouteList.remove(route); //TODO release journey
		return true;
	}
	
	/**
	 * The remove method of routeManager class
	 * This method is called when route need to be deleted.
	 * @param index
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(int index){
		allRouteList.remove(index); //TODO release journey
		return true;
	}
	
	//TODO unknown parameter
	public void update(){
		
	}
	
	/**
	 * The getAllRouteList method of routeManager class
	 * This method is called when all routes are needed.
	 * @return allRouteList
	 */
	public ArrayList<Route> getAllRouteList() {
		return allRouteList;
	}

	/**
	 * The getHashMap method of routeManager class
	 * This method is called when timetable of a route is needed.
	 * @return hashMap
	 */
	public HashMap<Route, Timetable> getHashMap() {
		return hashMap;
	}
	
	/**
	 * The getStationList method of routeManager class
	 * This method is called when all stations on a route are needed.
	 * @return allStationList
	 */
	public ArrayList<Station> getStationList(int index){
		Route route = (Route)Controller.egodic(index, "Route");
		return route.getStationList();
	}
	
	/**
	 * The getTimeSpanList method of routeManager class
	 * This method is called when all time spans on a route are needed.
	 * @return allTimeSpanList
	 */
	public ArrayList<Integer> getTimeSpanList(int index){
		Route route = (Route)Controller.egodic(index, "Route");
		return route.getTimeSpanList();
	}

	/**
	 * The getId method of routeManager class
	 * This method is called when a route's id is needed.
	 * @param index
	 * @return id
	 */
	@Override
	public int getId(int index) {
		return allRouteList.get(index).getId();
	}
	
}
