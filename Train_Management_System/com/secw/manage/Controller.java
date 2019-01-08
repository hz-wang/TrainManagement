package com.secw.manage;

import java.util.ArrayList;

import com.secw.entity.Driver;
import com.secw.entity.Driver.DriverStatus;
import com.secw.entity.Journey;
import com.secw.entity.Route;
import com.secw.entity.Station;
import com.secw.entity.Train;
import com.secw.entity.Train.TrainStatus;

/**
 * Title		: Controller.java
 * Description	: This class is to manage all managers
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Controller {
	private static TrainManager trainManager;
	private static DriverManager driverManager;
	private static StationManager stationManager;
	private static RouteManager routeManager;
	private static JourneyManager journeyManager;
	/**
	 * The constructor of driverManager class
	 * It creates all managers when invoked.
	 */
	public Controller(){
		trainManager = TrainManager.getManager();
		driverManager = DriverManager.getManager();
		stationManager = StationManager.getManager();
		routeManager = RouteManager.getManager();
		journeyManager = JourneyManager.getManager();
	}
	
	/**
	 * The getTrainData method of controller class
	 * This method is used to get all train list.
	 * @return allTrainList
	 */
	public static ArrayList<Train> getTrainData(){
		return trainManager.getAllTrainList();
	}
	
	/**
	 * The getAvailableTrainData method of controller class
	 * This method is used to get available train list.
	 * @return availableTrainList
	 */
	public static ArrayList<Train> getAvailableTrainData(){
		return trainManager.getAvailableTrainList();
	}
	
	/**
	 * The getDriverData method of controller class
	 * This method is used to get all driver list.
	 * @return allDriverList
	 */
	public static ArrayList<Driver> getDriverData(){
		return driverManager.getAllDriverList();
	}
	
	/**
	 * The getAvailableDriverData method of controller class
	 * This method is used to get available driver list.
	 * @return availableDriverList
	 */
	public static ArrayList<Driver> getAvailableDriverData(){
		return driverManager.getAvailableDriverList();
	}
	
	/**
	 * The getStationData method of controller class
	 * This method is used to get all station list.
	 * @return allStaionList
	 */
	public static ArrayList<Station> getStationData(){
		return stationManager.getAllStationList();
	}
	
	/**
	 * The getRouteData method of controller class
	 * This method is used to get all route list.
	 * @return allRouteList
	 */
	public static ArrayList<Route> getRouteData(){
		return routeManager.getAllRouteList();
	}
	
	/**
	 * The getJourneyData method of controller class
	 * This method is used to get all journey list.
	 * @return allJourneyList
	 */
	public static ArrayList<Journey> getJourneyData(){
		return JourneyManager.getAllJourneyList();
	}
	
	/**
	 * The removeAvailableTrain method of controller class
	 * This method is used to remove a train from available list.
	 * @param index
	 * @return true
	 * 	If success
	 * @return false
	 * 	If failed
	 */
	public static boolean removeAvailableTrain(int index){
		trainManager.getAvailableTrainList().remove(trainManager.getAllTrainList().get(index));
		return true; 
	}
	
	/**
	 * The removeAvailableDriver method of controller class
	 * This method is used to remove a driver from available list.
	 * @param index
	 * @return true
	 * 	If success
	 * @return false
	 * 	If failed
	 */
	public static boolean removeAvailableDriver(int index){
		driverManager.getAvailableDriverList().remove(driverManager.getAllDriverList().get(index));
		return true; 
	}
	
	/**
	 * The egodic method of controller class
	 * This method is used to find a corresponding object
	 * if given an id and a type.
	 * @param id
	 * @param str
	 * @return obeject
	 */
	public static Object egodic(int id, String str){
		ArrayList<?> a = null;
		Object o = null;
		Manager m = null;
		switch (str) {
		case "Driver":
			a = getAvailableDriverData();
			m = driverManager;
			break;
			
		case "Train":
			a = getAvailableTrainData();
			m = trainManager;
			break;
			
		case "AllTrain":
			a = getTrainData();
			m = trainManager;
			break;
			
		case "Route":
			a = getRouteData();
			m = routeManager;
			break;
			
		case "Journey":
			a = getJourneyData();
			m = journeyManager;
			break;

		default:
			break;
		}
		
		for (int i = 0; i < a.size(); i++) {
			if(str.equals("AllTrain")){
				if (m.getId(i, str) == id) {
					o = a.get(i);
					break;
				}
			}
			else{
				if (m.getId(i) == id) {
					o = a.get(i);
					break;
				}
			}
		}
		
		return o;
	}
	
	/**
	 * The changeStatus method of controller class
	 * This method is used to change statuses of driver and train.
	 * @param trainId
	 * @param driverId
	 */
	public static void changeStatus(int trainId, int driverId){
		trainManager.update((Train)egodic(trainId, "Train"), TrainStatus.stopped);
		trainManager.removeAvailable((Train)egodic(trainId, "Train"));
		driverManager.update((Driver)egodic(driverId, "Driver"), DriverStatus.unavailable);
		driverManager.removeAvailable((Driver)egodic(driverId, "Driver"));
	}
	
	/**
	 * The getSpanTime method of controller class
	 * This method is used to get the whole time span of a route.
	 * @param routeId
	 * @return result
	 */
	public static int getSpanTime(int routeId){
		int result = 0;
		for (int i = 0; i < routeManager.getTimeSpanList(routeId).size(); i++) {
			result +=  routeManager.getTimeSpanList(routeId).get(i);
		}
		
		return result;
	}
}
