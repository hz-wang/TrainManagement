package com.secw.tool;

import com.secw.manage.Controller;

/**
 * Title		: Calculate.java
 * Description	: This class is to do calculations
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Calculate {
	/**
	 * The minToHour method of calculate class
	 * This method is used to transform minutes to hours.
	 * @param time
	 * @return string
	 */
	public static String minToHour(int time) {
		int hour = time / 60;
		int min = time % 60;
		return "" + (hour > 9 ? hour : "0" + hour) + ":"
				+ (min > 9 ? min : "0" + min);
	}

	/**
	 * The stringToTime method of calculate class
	 * This method is used to transform time to string format.
	 * @param s
	 * @return result
	 */
	public static int stringToTime(String s){
		int result = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
		return result;
	}

	/**
	 * The checkReturnCentralStation method of calculate class
	 * This method is to check whether a train can be back before 24:00.
	 * @param time
	 * @param routeId
	 * @return true
	 * 	If the train can return before 24:00
	 * @return false
	 * 	If the train cannot return brfore 24:00
	 */
	public static boolean checkReturnCentralStation(int time, int routeId) {
		if(time + Controller.getSpanTime(routeId) * 2 + 5 > 1440){
			return false;
		}
		return true;
	}
}
