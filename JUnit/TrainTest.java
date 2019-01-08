package com.secw.JUnit;

import com.secw.entity.Train;
import com.secw.entity.Train.TrainStatus;

public class TrainTest extends junit.framework.TestCase{
	public void testTrain() {
		Train train1 = new Train(1, TrainStatus.available);
		assertEquals(1, train1.getId());
		assertEquals(TrainStatus.available, train1.getStatus());
		
		Train train2 = new Train(2, TrainStatus.running);
		assertEquals(2, train2.getId());
		assertEquals(TrainStatus.running, train2.getStatus());
		
		Train train3 = new Train(3, TrainStatus.stopped);
		assertEquals(3, train3.getId());
		assertEquals(TrainStatus.stopped, train2.getStatus());
	}
}
