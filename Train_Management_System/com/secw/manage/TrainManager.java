package com.secw.manage;

import java.io.IOException;
import java.util.ArrayList;

import com.secw.entity.Train;
import com.secw.entity.Train.TrainStatus;
import com.secw.tool.IO;

/**
 * Title		: TrainManager.java
 * Description	: This class is to manage all trains
 * @author Zhang Yufeng, Wang Haozhe
 */
public class TrainManager extends Manager{
	private static ArrayList<Train> allTrainList = new ArrayList<Train>();
	private static ArrayList<Train> availableTrainList = new ArrayList<Train>();
	
	private static TrainManager trainManager = null;
	
	/**
	 * The constructor of trainManager class
	 * It can only be created privately.
	 * @exception IOException
	 */
	private TrainManager() {
		try {
			ArrayList<String> result = IO.readData("data/Train.txt");
			for(int i = 0; i < result.size(); i++){
				String temp[] = result.get(i).split("#");
				add(Integer.parseInt(temp[0]), TrainStatus.valueOf(temp[1]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The getManager method of trainManager class
	 * The instance of the class can only be created once
	 * @return trainManager
	 */
	public static TrainManager getManager(){
		if(trainManager == null){
			trainManager = new TrainManager();
		}
		
		return trainManager;
	}

	/**
	 * The add method of trainManager class
	 * This method is called when new train is created.
	 */
	public void add(){
		Train train = new Train();
		allTrainList.add(train);
		availableTrainList.add(train);
	}

	/**
	 * The add method of trainManager class
	 * This method is called when new train is created.
	 * Mainly used to read from files.
	 * @param id
	 * @param status
	 */
	public void add(int id, TrainStatus status){
		Train train = new Train(id, status);
		allTrainList.add(train);
		availableTrainList.add(train);
	}
	
	/**
	 * The remove method of trainManager class
	 * This method is called when train need to be deleted.
	 * @param train
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(Train train){
		if (train.getStatus() == TrainStatus.available){
			allTrainList.remove(train);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * The remove method of trainManager class
	 * This method is called when traindriver need to be deleted.
	 * @param index
	 * @return true
	 * 	if success.
	 * false
	 * 	if failed
	 */
	public boolean remove(int index){
		if (allTrainList.get(index).getStatus() == TrainStatus.available){
			allTrainList.remove(index);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * The removeAvailable method of trainManager class
	 * The method is called when a train becomes unavailable.
	 * @param train
	 */
	public void removeAvailable(Train train){
		availableTrainList.remove(train);
	}
		
	/**
	 * The getTrainStatus method of trainManager class
	 * The method is called to get a specific train's status.
	 * @param index
	 * @return status
	 */
	public TrainStatus getTrainStatus(int index){
		return allTrainList.get(index).getStatus();
	}
	
	/**
	 * The update method of trainManager class
	 * This method is called when a train's status need to change.
	 * @param index
	 * @param trainStatus
	 */
	public void update(int index, TrainStatus trainStatus){
		allTrainList.get(index).setStatus(trainStatus);
	}
	
	/**
	 * The update method of trainManager class
	 * This method is called when a train's status need to change.
	 * @param train
	 * @param trainStatus
	 */
	public void update(Train train, TrainStatus trainStatus){
		train.setStatus(trainStatus);
	}
	
	/**
	 * The getAllTrainList method of trainManager class
	 * This method is called when all trains are needed.
	 * @return allTrainList
	 */
	public ArrayList<Train> getAllTrainList() {
		return allTrainList;
	}

	/**
	 * The getAvailableTrainList method of trainManager class
	 * This method is called when only trains that are available are needed.
	 * @return availableTrainList
	 */
	public ArrayList<Train> getAvailableTrainList() {
		return availableTrainList;
	}

	/**
	 * The getId method of trainManager class
	 * This method is called when a train's id is needed.
	 * @param index
	 * @return id
	 */
	@Override
	public int getId(int index) {
		
		return availableTrainList.get(index).getId();
	}
	
	/**
	 * The getId method of trainManager class
	 * This method is called when a train's id is needed.
	 * @param index
	 * @param str
	 * @return id
	 */
	@Override
	public int getId(int index, String str) {
		
		return allTrainList.get(index).getId();
	}
	
}
