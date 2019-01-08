package com.secw.ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.secw.entity.Train.TrainStatus;
import com.secw.manage.Controller;

/**
 * Title		: Clock.java
 * Description	: This class is to run the clock
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Clock implements Runnable{
	private static Clock clock = null;
	
	private static int time = 0;
	private JLabel clockLabel = new JLabel("", JLabel.LEFT);

	/**
	 * The constructor of clock class
	 * It can only be created privately.
	 */
	private Clock() {
	}
	
	/**
	 * The getClock method of clock class
	 * The instance of the class can only be created once
	 * @return clock
	 */
	public static Clock getClock(){
		if(clock == null){
			clock = new Clock();
		}
		
		return clock;
	}

	/**
	 * The getClockLabel method of clock class
	 * This method is used to provide a visible label of the clock.
	 * @return clockLable
	 */
	public JLabel getClockLabel() {
		clockLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 40));
		clockLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		new Thread(this).start(); //Counting time
		
		return clockLabel;
	}

	/**
	 * The startJourney method of clock class
	 * This method is used to start any journey on time.
	 */
	public void startJournry() {
		for (int i = 0; i < Controller.getJourneyData().size(); i++) {
			if (Controller.getJourneyData().get(i).getStartTime() == time || Controller.getJourneyData().get(i).getReturnTime() == time) {
				Controller.getJourneyData().get(i).getTrain()
						.setStatus(TrainStatus.running);
			}
		}
	}

	/**
	 * The getTime method of clock class
	 * This method is used to get current time.
	 * @return time
	 */
	public static int getTime() {
		return time;
	}

	/**
	 * The run method of clock class
	 * This method is used to count minutes.
	 */
	@Override
	public void run() {
		while (true) {
			int hour = time / 60;
			int min = time % 60;
			clockLabel.setText((hour > 9 ? hour : ("0" + hour)) + ":"
					+ (min > 9 ? min : ("0" + min)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			startJournry();
			
			time++;
		}
	}

}
