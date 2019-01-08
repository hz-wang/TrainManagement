package com.secw.manage;

import java.io.IOException;
import java.util.ArrayList;

import com.secw.entity.Driver;
import com.secw.entity.Driver.DriverStatus;
import com.secw.tool.IO;

/**
 * Title		: DriverManager.java
 * Description	: This class is to manage all drivers
 * @author Zhang Yufeng, Wang Haozhe
 */
public class DriverManager extends Manager{
	private static ArrayList<Driver> allDriverList = new ArrayList<Driver>();
	private static ArrayList<Driver> availableDriverList = new ArrayList<Driver>();
	
	private static DriverManager driverManager = null;

	/**
	 * The constructor of driverManager class
	 * It can only be created privately.
	 * @exception IOException
	 */
	private DriverManager() {
		try {
			ArrayList<String> result = IO.readData("data/Driver.txt");
			for(int i = 0; i < result.size(); i++){
				String temp[] = result.get(i).split("#");
				add(Integer.parseInt(temp[0]), DriverStatus.valueOf(temp[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The getManager method of driverManager class
	 * The instance of the class can only be created once
	 * @return driverManager
	 */
	public static DriverManager getManager(){
		if(driverManager == null){
			driverManager = new DriverManager();
		}
		
		return driverManager;
	}
	
	/**
	 * The add method of driverManager class
	 * This method is called when new driver is created.
	 */
	public void add(){
		Driver driver = new Driver();
		allDriverList.add(driver);
		availableDriverList.add(driver);
	}
	
	/**
	 * The add method of driverManager class
	 * This method is called when new driver is created.
	 * Mainly used to read from files.
	 * @param id
	 * @param status
	 */
	public void add(int id, DriverStatus status){
		Driver driver = new Driver(id, status);
		allDriverList.add(driver);
		availableDriverList.add(driver);
	}
	
	/**
	 * The remove method of driverManager class
	 * This method is called when driver need to be deleted.
	 * @param driver
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(Driver driver){
		if (driver.getStatus() == DriverStatus.available){
			allDriverList.remove(driver);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * The remove method of driverManager class
	 * This method is called when driver need to be deleted.
	 * @param index
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(int index){
		if(allDriverList.get(index).getStatus() == DriverStatus.available){
			allDriverList.remove(index);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * The removeAvailable method of driverManager class
	 * The method is called when a driver becomes unavailable.
	 * @param driver
	 */
	public void removeAvailable(Driver driver){
		availableDriverList.remove(driver);
	}
	
	/**
	 * The getDriverStatus method of driverManager class
	 * The method is called to get a specific driver's status.
	 * @param index
	 * @return status
	 */
	public DriverStatus getDriverStatus(int index){
		return allDriverList.get(index).getStatus();
	}
	
	/**
	 * The update method of driverManager class
	 * This method is called when a driver's status need to change.
	 * @param index
	 * @param driverStatus
	 */
	public void update(int index, DriverStatus driverStatus){
		allDriverList.get(index).setStatus(driverStatus);
	}
	
	/**
	 * The update method of driverManager class
	 * This method is called when a driver's status need to change.
	 * @param driver
	 * @param driverStatus
	 */
	public void update(Driver driver, DriverStatus driverStatus){
		driver.setStatus(driverStatus);
	}

	/**
	 * The getAllDriverList method of driverManager class
	 * This method is called when all drivers are needed.
	 * @return allDriverList
	 */
	public ArrayList<Driver> getAllDriverList() {
		return allDriverList;
	}

	/**
	 * The getAvailableDriverList method of driverManager class
	 * This method is called when only drivers that are available are needed.
	 * @return availableDriverList
	 */
	public ArrayList<Driver> getAvailableDriverList() {
		return availableDriverList;
	}
	
	/**
	 * The getId method of driverManager class
	 * This method is called when a driver's id is needed.
	 * @param index
	 * @return id
	 */
	@Override
	public int getId(int index) {

		return availableDriverList.get(index).getId();
	}
	
}
