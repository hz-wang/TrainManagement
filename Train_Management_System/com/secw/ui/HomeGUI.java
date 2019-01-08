package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.secw.entity.Driver.DriverStatus;
import com.secw.entity.Train.TrainStatus;
import com.secw.manage.Controller;
import com.secw.tool.GetLocationIMP;

/**
 * Title		: HomeGUI.java
 * Description	: This is the home page of the project
 * @author Zhang Yufeng, Wang Haozhe
 */
public class HomeGUI {
	int journeyCount;
	JLabel jLabel = new JLabel("", SwingConstants.CENTER);
	
	/**
	 * The constructor of the homeGUi class
	 * It is used to set default text.
	 * @param panel
	 */
	public HomeGUI(JPanel panel) {
		jLabel.setText(("Welcome to Train Management System!    "
				+ "No Train is running."));
		jLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		init(panel);
	}
	
	/**
	 * The init method of homeGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		journeyCount = Controller.getJourneyData().size();
		if (journeyCount == 0){
			panel.add("North", jLabel);
		}
		else {
			panel.setLayout(new GridLayout((journeyCount > 7 ? journeyCount : 7), 1, 10, 10));
			
			for(int  i = 0; i < journeyCount; i++){
				if(Controller.getJourneyData().get(i).getTrain().getStatus() == TrainStatus.running ||
						(Controller.getJourneyData().get(i).getTrain().getStatus() == TrainStatus.stopped &&
								((Controller.getJourneyData().get(i).getStartTime() <= Clock.getTime() && Controller.getJourneyData().get(i).getReturnTime() - 5 > Clock.getTime())
								|| Controller.getJourneyData().get(i).getReturnTime() <= Clock.getTime()))){
					JPanel borderPanel = new JPanel();				
					borderPanel.setLayout(new BorderLayout(10, 10));
					panel.add(borderPanel);
					
					JLabel text = new JLabel("Journey " + (i+1));
					borderPanel.add("West",text);
					JPanel runPanel = new JPanel(new GridLayout(2, 1, 0, 0));
					JProgressBar progressbar = new JProgressBar();
					JLabel label = new JLabel("",JLabel.CENTER);
					
					runPanel.add(label);
					runPanel.add(progressbar);
					progressbar.setStringPainted(true);
					progressbar.setForeground(Color.blue);
					boolean flag;
					if(Clock.getTime() >= Controller.getJourneyData().get(i).getReturnTime()){
						progressbar.setValue(((Clock.getTime() - Controller.getJourneyData().get(i).getReturnTime()) * 100) / (Controller.getJourneyData().get(i).getReturnTime() - 5 - Controller.getJourneyData().get(i).getStartTime()));
						flag = false;
					}
					else{
						progressbar.setValue(((Clock.getTime() - Controller.getJourneyData().get(i).getStartTime()) * 100) / (Controller.getJourneyData().get(i).getReturnTime() - 5 - Controller.getJourneyData().get(i).getStartTime()));
						flag = true;
					}
					
					borderPanel.add("Center",runPanel);
					
					Timer timer = new Timer();
					
					JButton button = new JButton("Stop");
					if(Controller.getJourneyData().get(i).getTrain().getStatus() == TrainStatus.stopped){
						button.setText("Start");
					}
					
					timer.schedule(new MyTask(progressbar,panel, button, i, flag, label), 0, 600);
					borderPanel.add("East",button);
				}
			}
		}
	}
	
	/**
	 * The inner class of homeGUI
	 * It is used to run each progressbar.
	 * @author Zhang Yufeng, Wang Haozhe
	 */
	class MyTask extends TimerTask {  
		JProgressBar progressbar;
		JPanel panel;
		JButton button;
		int id;
		boolean flag;
		JLabel label;
		
		/**
		 * The constructor of the inner class
		 * It is invoked to start a progressbar and
		 * store nesseccery variables.
		 * @param progressbar
		 * @param panel
		 * @param button
		 * @param id
		 * @param flag
		 * @param label
		 */
		public MyTask(JProgressBar progressbar,JPanel panel, JButton button, int id, boolean flag, JLabel label){
			this.progressbar = progressbar;
			this.panel = panel;
			this.button = button;
			this.id = id;
			this.flag = flag;
			this.label = label;
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(Controller.getJourneyData().get(id).getTrain().getStatus() == TrainStatus.running){
						button.setText("Start");
						Controller.getJourneyData().get(id).getTrain().setStatus(TrainStatus.stopped);
					}
					else{
						button.setText("Stop");
						Controller.getJourneyData().get(id).getTrain().setStatus(TrainStatus.running);
					}
					JOptionPane.showMessageDialog(null, "Information Sent!", "Success", 0);
				}
			});
		}
		
		/**
		 * The run method of the inner class
		 * This is used to change progressbar value every second.
		 */
	    @Override  
	    public void run() {
	    	if(button.getText() == "Stop"){
	    		int value = progressbar.getValue();
	    		if (value < 100){
	    			label.setText("Next Stop: " + new GetLocationIMP().getLocation(Controller.getJourneyData().get(id).getTrain()));
	    			if(flag){
	    				progressbar.setValue(((Clock.getTime() - Controller.getJourneyData().get(id).getStartTime()) * 100) / (Controller.getJourneyData().get(id).getReturnTime() - 5 - Controller.getJourneyData().get(id).getStartTime()));
	    			}
	    			else {
	    				progressbar.setValue(((Clock.getTime() - Controller.getJourneyData().get(id).getReturnTime()) * 100) / (Controller.getJourneyData().get(id).getReturnTime() - 5 - Controller.getJourneyData().get(id).getStartTime()));
	    			}
	    		}	
	    		if (value >= 100){
	    			panel.remove(progressbar.getParent().getParent());
	    			if(flag){
	    				Controller.getJourneyData().get(id).getTrain().setStatus(TrainStatus.stopped);
	    			}
	    			else {
	    				Controller.getJourneyData().get(id).getTrain().setStatus(TrainStatus.available);
	    				Controller.getAvailableTrainData().add(Controller.getJourneyData().get(id).getTrain());
	    				Controller.getJourneyData().get(id).getDriver().setStatus(DriverStatus.available);
	    				Controller.getAvailableDriverData().add(Controller.getJourneyData().get(id).getDriver());
//	    				journeyCount--;
	    			}
	    			panel.updateUI();
	    			this.cancel();
	    		}
	    	}
	    }  
	  
	}  
	
}
