package com.secw.entity;

import com.secw.manage.Controller;

/**
 * Title		: Journey.java
 * Description	: This class is to store journey information
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Journey {
	private int id; //The identification of journey
	private Train train; //The train on the journey
	private Driver driver; //The driver on the journey
	private Route route; //The route on the journey
	private int startTime; //The start time of the journey
	private int returnTime; //The return time of the journey
	private static int count = 0; //Auto-increment number to set id
	
	/**
	 * The constructor of journey class
	 * It reads four parameters and uses these to initialize attributes.
	 * Auto generate return time.
	 * @param train
	 * @param driver
	 * @param route
	 * @param startTime
	 */
	public Journey(Train train, Driver driver, Route route, int startTime) {
		super();
		this.id = ++count;
		this.train = train;
		this.driver = driver;
		this.route = route;
		this.startTime = startTime;
		this.returnTime = this.startTime + Controller.getSpanTime(route.getId()) + 5;
	}
	
	/**
	 * The getId method of journey class
	 * This method is called when the id of 
	 * journey is needed.
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The getTrain method of journey class
	 * This method is called when the train on 
	 * journey is needed.
	 * @return train
	 */
	public Train getTrain() {
		return train;
	}
	
	/**
	 * The setTrain of journey class
	 * This method is called when the train on
	 * journey is going to change.
	 * @param train
	 */
	public void setTrain(Train train) {
		this.train = train;
	}
	
	/**
	 * The getDriver method of journey class
	 * This method is called when the driver on 
	 * journey is needed.
	 * @return driver
	 */
	public Driver getDriver() {
		return driver;
	}
	
	/**
	 * The setDriver of journey class
	 * This method is called when the driver on
	 * journey is going to change.
	 * @param driver
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	/**
	 * The getRoute method of journey class
	 * This method is called when the route on 
	 * journey is needed.
	 * @return route
	 */
	public Route getRoute() {
		return route;
	}
	
	/**
	 * The setRoute of journey class
	 * This method is called when the route on
	 * journey is going to change.
	 * @param route
	 */
	public void setRoute(Route route) {
		this.route = route;
	}
	
	/**
	 * The getStartTime method of journey class
	 * This method is called when the start time of 
	 * journey is needed.
	 * @return startTime
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * The setStartTime of journey class
	 * This method is called when the start time of
	 * journey is going to change.
	 * @param startTime
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * The getReturnTime method of journey class
	 * This method is called when the return time of 
	 * journey is needed.
	 * @return returnTime
	 */
	public int getReturnTime() {
		return returnTime;
	}
	
	/**
	 * The setReturn of journey class
	 * This method is called when the return time of
	 * journey is going to change.
	 * @param returnTime
	 */
	public void setReturnTime(int returnTime) {
		this.returnTime = returnTime;
	}

}
