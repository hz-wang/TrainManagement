package com.secw.manage;

import java.util.ArrayList;

import com.secw.entity.Driver;
import com.secw.entity.Journey;
import com.secw.entity.Route;
import com.secw.entity.Train;

/**
 * Title		: JourneyManager.java
 * Description	: This class is to manage all journeys
 * @author Zhang Yufeng, Wang Haozhe
 */
public class JourneyManager extends Manager{
	private static ArrayList<Journey> allJourneyList = new ArrayList<Journey>();
	
	private static JourneyManager journeyManager = null;

	/**
	 * The constructor of journeyManager class
	 * It can only be created privately.
	 */
	private JourneyManager() {
		
	}
	
	/**
	 * The getManager method of journeyManager class
	 * The instance of the class can only be created once
	 * @return journeyManager
	 */
	public static JourneyManager getManager(){
		if(journeyManager == null){
			journeyManager = new JourneyManager();
		}
		
		return journeyManager;
	}
	
	/**
	 * The add method of journeyManager class
	 * This method is called when new journey is created.
	 * @param train
	 * @param driver
	 * @param route
	 * @param startTime
	 */
	public static void add(Object train, Object driver, Object route, int startTime){
		allJourneyList.add(new Journey((Train)train, (Driver)driver, (Route)route, startTime));
	}

	/**
	 * The remove method of journeyManager class
	 * This method is called when journey need to be deleted.
	 * @param journey
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(Journey journey){
		allJourneyList.remove(journey); //release journey
		return true;
	}
	
	/**
	 * The remove method of journeyManager class
	 * This method is called when journey need to be deleted.
	 * @param index
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(int index){
		allJourneyList.remove(index);
		return true;
	}
	
	/**
	 * The getAllJourneyList method of journeyManager class
	 * This method is called when all journeys are needed.
	 * @return allJourneyList
	 */
	public static ArrayList<Journey> getAllJourneyList() {
		return allJourneyList;
	}

	/**
	 * The getId method of journeyManager class
	 * This method is called when a journey's id is needed.
	 * @param index
	 * @return id
	 */
	@Override
	public int getId(int index) {

		return allJourneyList.get(index).getId();
	}
	
	
}
