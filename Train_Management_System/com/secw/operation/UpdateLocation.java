package com.secw.operation;

import com.secw.entity.Driver;
import com.secw.entity.Station;
import com.secw.entity.Train;

/**
 * Title		: UpdateLocation.java
 * Description	: This class is a interface
 * @author Zhang Yufeng, Wang Haozhe
 */
public interface UpdateLocation {
	public abstract String updateLocation(Train train);
	public abstract String updateLocation(Station station);
	public abstract String updateLocation(Driver driver);
}
