package com.secw.tool;

import com.secw.entity.Journey;
import com.secw.entity.Train;
import com.secw.entity.Train.TrainStatus;
import com.secw.manage.Controller;
import com.secw.operation.GetLocation;
import com.secw.ui.Clock;

/**
 * Title		: GetLocationIMP.java
 * Description	: This class is to implement getlocation method
 * @author Zhang Yufeng, Wang Haozhe
 */
public class GetLocationIMP implements GetLocation{
	
	/**
	 * The getLocation method
	 * This is the implement of the method.
	 * @param train
	 * @return str
	 */
	@Override
	public String getLocation(Train train) {
		String str = "";
		if (train.getStatus() == TrainStatus.available){
			str = " none.";
		}
		else {
			int i;
			for (i = 0; i < Controller.getJourneyData().size(); i++) {
				if(train.getId() == Controller.getJourneyData().get(i).getTrain().getId())
					break;
			}
			Journey journey = (Journey)Controller.egodic(Controller.getJourneyData().get(i).getId(), "Journey");
			int time = journey.getStartTime();
			for (int j = 0; j < 2 * journey.getRoute().getTimeSpanList().size(); j++) {
				if(j < journey.getRoute().getTimeSpanList().size()){
					time += journey.getRoute().getTimeSpanList().get(j);
				}
				else if(j == journey.getRoute().getTimeSpanList().size()){
					time += 5 + journey.getRoute().getTimeSpanList().get(journey.getRoute().getTimeSpanList().size() - 1);
				}
				else{
					time += journey.getRoute().getTimeSpanList().get(2 * journey.getRoute().getTimeSpanList().size() - 1 - j);
				}
				
				if (time > Clock.getTime()) {
					if(j == 2 * journey.getRoute().getTimeSpanList().size() - 1){
						str = "Central Station";
					}
					else {
						str = journey.getRoute().getStationList().get(j >= journey.getRoute().getTimeSpanList().size() ? 2 * journey.getRoute().getTimeSpanList().size() - 2 - j : j).getName();
					}
					break;
				}
			}
		}
		return str;
	}
	
}
