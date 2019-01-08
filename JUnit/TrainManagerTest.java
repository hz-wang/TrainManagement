package com.secw.JUnit;

import com.secw.entity.Train.TrainStatus;
import com.secw.manage.TrainManager;

public class TrainManagerTest extends junit.framework.TestCase{
	public void testTrainManager(){
		TrainManager.getManager().add(1, TrainStatus.available);
		assertEquals(TrainStatus.available, TrainManager.getManager().getTrainStatus(0));
		assertTrue(TrainManager.getManager().remove(0));
	}
}
