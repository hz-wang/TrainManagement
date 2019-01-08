package com.secw.manage;

import java.io.IOException;
import java.util.ArrayList;

import com.secw.entity.Station;
import com.secw.tool.IO;

/**
 * Title		: StationManager.java
 * Description	: This class is to manage all stations
 * @author Zhang Yufeng, Wang Haozhe
 */
public class StationManager extends Manager{
	private static ArrayList<Station> allStationList = new ArrayList<Station>();
	
	private static StationManager stationManager = null;
	
	/**
	 * The constructor of stationManager class
	 * It can only be created privately.
	 * @exception IOException
	 */
	private StationManager() {
		try {
			ArrayList<String> result = IO.readData("data/Station.txt");
			for(int i = 0; i < result.size(); i++){
				String temp[] = result.get(i).split("#");
				add(Integer.parseInt(temp[0]), temp[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The getManager method of stationManager class
	 * The instance of the class can only be created once
	 * @return stationManager
	 */
	public static StationManager getManager(){
		if(stationManager == null){
			stationManager = new StationManager();
		}
		return stationManager;
	}
	
	/**
	 * The add method of stationManager class
	 * This method is called when new station is created.
	 * @param name
	 */
	public void add(String name){
		allStationList.add(new Station(name));
	}
	
	/**
	 * The add method of stationManager class
	 * This method is called when new station is created.
	 * @param id
	 * @param name
	 */
	public void add(int id, String name){
		allStationList.add(new Station(id, name));
	}
	
	/**
	 * The remove method of stationManager class
	 * This method is called when station need to be deleted.
	 * @param station
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(Station station){
		if (station.getId() != 0){
			allStationList.remove(station);
			return true;
		}
		else{
			System.out.println("不能删除站点");
			return false;
		}
	}
	
	/**
	 * The remove method of stationManager class
	 * This method is called when station need to be deleted.
	 * @param index
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(int index){
		if (allStationList.get(index).getId() != 0){
			allStationList.remove(index);
			return true;
		}
		else{
			System.out.println("不能删除站点");
			return false;
		}
	}
	
	/**
	 * The getStation method of stationManager class
	 * The method is called to get a specific station's name.
	 * @param index
	 * @return name
	 */
	public String getStation(int index){
		return allStationList.get(index).getName();
	}
	
	/**
	 * The getAllStationList method of stationManager class
	 * This method is called when all stations are needed.
	 * @return allStationList
	 */
	public ArrayList<Station> getAllStationList() {
		return allStationList;
	}

	/**
	 * The egodic method of stationManager class
	 * This method is called when a station is needed according to a name.
	 * @param name
	 * @return station
	 */
	public Station egodic(String name){
		int i;
		for (i = 0; i < allStationList.size(); i++) {
			if (allStationList.get(i).getName().equals(name)) {
				break;
			}
		}
		return allStationList.get(i);
	}

	/**
	 * The getId method of stationManager class
	 * This method is called when a station's id is needed.
	 * @param index
	 * @return id
	 */
	@Override
	public int getId(int index) {
		return allStationList.get(index).getId();
	}
}
