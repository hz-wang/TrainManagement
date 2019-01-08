package com.secw.entity;

/**
 * Title		: Driver.java
 * Description	: This class is to store driver information
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Driver {
	public enum DriverStatus{unavailable,available}; //Two statuses of driver
	private int id; //The identification of driver
	private DriverStatus status; //Current status of driver
	private static int count = 0; //Auto-increment number to set id
	
	/**
	 * The constructor of driver class
	 * It reads no parameters and set attributes default values.
	 */
	public Driver() {
		super();
		this.id = ++count;
		this.status = DriverStatus.available;
	}
	
	/**
	 * The constructor of driver class
	 * It reads two parameters and uses these to initialize attributes.
	 * Mainly used to read from files.
	 * @param id
	 * @param status
	 */
	public Driver(int id, DriverStatus status) {
		super();
		count = count > id ? count : id;
		this.id = id;
		this.status = status;
	}
	
	/**
	 * The getId method of driver class
	 * This method is called when the id of 
	 * driver is needed.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getStatus method of driver class
	 * This method is called when the status of 
	 * driver is needed.
	 * @return status
	 */
	public DriverStatus getStatus() {
		return status;
	}
	
	/**
	 * The setStatus of driver class
	 * This method is called when the status of
	 * driver is going to change.
	 * @param status
	 */
	public void setStatus(DriverStatus status) {
		this.status = status;
	}

	/**
	 * The toString method of driver class
	 * This method is called when id in string format
	 * is needed.
	 * @return id
	 */
	@Override
	public String toString() {
		return "" + this.id;
	}
	
}
