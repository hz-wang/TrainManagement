package com.secw.entity;

/**
 * Title		: Station.java
 * Description	: This class is to store station information
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Station {
	private int id; //The identification of station
	private String name; //The name of station
	private static int count = 0; //Auto-increment number to set id

	/**
	 * The constructor of station class
	 * It reads no parameters and set attributes default values.
	 */
	public Station(String name) {
		super();
		this.id = ++count;
		this.name = name;
	}

	/**
	 * The constructor of station class
	 * It reads two parameters and uses these to initialize attributes.
	 * Mainly used to read from files.
	 * @param id
	 * @param name
	 */
	public Station(int id, String name) {
		super();
		count = count > id ? count : id;
		this.id = id;
		this.name = name;
	}
	
	/**
	 * The getId method of station class
	 * This method is called when the id of 
	 * driver is needed.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The getName method of station class
	 * This method is called when the name of 
	 * station is needed.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The setName of station class
	 * This method is called when the name of
	 * station is going to change.
	 * @param status
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The toString method of station class
	 * This method is called when id in string format
	 * is needed.
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
}
