package com.secw.entity;

/**
 * Title		: Train.java
 * Description	: This class is to store train information
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Train {
	public enum TrainStatus{running,stopped,available};	//Three statuses of train
	private int id; //The identification of train
	private TrainStatus status; //Current status of train
	private static int count = 0; //Auto-increment number to set id
	
	/**
	 * The constructor of train class
	 * It reads no parameters and set attributes default values.
	 */
	public Train() {
		super();
		this.id = ++count;
		this.status = TrainStatus.available;
	}
	
	/**
	 * The constructor of train class
	 * It reads two parameters and uses these to initialize attributes.
	 * Mainly used to read from files.
	 * @param id
	 * @param status
	 */
	public Train(int id, TrainStatus status) {
		super();
		count = count > id ? count : id;
		this.id = id;
		this.status = status;
	}
	
	
	/**
	 * The getId method of train class
	 * This method is called when the id of 
	 * train is needed.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getStatus method of train class
	 * This method is called when the status of 
	 * train is needed.
	 * @return status
	 */
	public TrainStatus getStatus() {
		return status;
	}
	
	/**
	 * The setStatus of train class
	 * This method is called when the status of
	 * train is going to change.
	 * @param status
	 */
	public void setStatus(TrainStatus status) {
		this.status = status;
	}
	
	/**
	 * The toString method of train class
	 * This method is called when id in string format
	 * is needed.
	 * @return id
	 */
	@Override
	public String toString() {
		return "" + this.id;
	}
}
