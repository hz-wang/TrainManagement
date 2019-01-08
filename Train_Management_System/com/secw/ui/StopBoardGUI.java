package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Journey;
import com.secw.manage.Controller;
import com.secw.manage.RouteManager;
import com.secw.tool.Calculate;
import com.secw.tool.GetLocationIMP;

/**
 * Title		: StopBoardGUI.java
 * Description	: This is the stop board GUI
 * @author Zhang Yufeng, Wang Haozhe
 */
public class StopBoardGUI {
	JFrame stopBoard = new JFrame();
	JPanel stopPanel = new JPanel(new BorderLayout());
	JPanel northPanel1 = new JPanel(new GridLayout(1,2,3,3));
	JPanel centerPanel = new JPanel();
	JScrollPane js = new JScrollPane();
	JComboBox<Object> stationBox = new JComboBox<Object>();

	/**
	 * The constructor of the stopBoardGUi class
	 * It is used to set default text.
	 */
	public StopBoardGUI(){
		stopBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stopBoard.setBounds(920, 100, 800, 400);
//		stopBoard.setResizable(false);
		stopBoard.setVisible(true);
		stopBoard.setTitle("Stop Board");
		
		northPanel1.setBorder(new EmptyBorder(0, 20, 0,20));
		
		stationBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					ArrayList<Journey> hasJourney = new ArrayList<Journey>();
					if(Controller.getJourneyData().size() == 0){
						JLabel jl = new JLabel("No train is uncoming at this station.", JLabel.CENTER);
						jl.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD, 18));
						js = new JScrollPane(jl);
					}
					else {
						for (int i = 0; i < Controller.getJourneyData().size(); i++) {
							if (Controller.getJourneyData().get(i).getRoute().getStationList().contains(stationBox.getSelectedItem()) && (new GetLocationIMP().getLocation(Controller.getJourneyData().get(i).getTrain()).equals(stationBox.getSelectedItem().toString()))){
								hasJourney.add(Controller.getJourneyData().get(i));
							}
						}
						JPanel giantPanel = new JPanel(new GridLayout(0, 1, 5, 5));
						for (int i = 0; i < hasJourney.size(); i++) {
							JPanel panel = new JPanel(new BorderLayout());
							JLabel label = new JLabel("Train " + hasJourney.get(i).getTrain().getId());
							label.setBorder(new EmptyBorder(5, 10, 5, 10));
							Vector<Object> row = new Vector<Object>();
							Vector<Object> col = new Vector<Object>();
							Vector<Object> cur = new Vector<Object>();
							col.addElement("Central");
							cur.addElement(Calculate.minToHour(hasJourney.get(i).getStartTime()));

							for (int j = 0; j < hasJourney.get(i).getRoute().getStationList().size(); j++) {
								col.addElement(hasJourney.get(i).getRoute().getStationList().get(j));
								cur.addElement(RouteManager.getManager().getHashMap().get(hasJourney.get(i).getRoute()).getOutWardTimetable(hasJourney.get(i)).get(j));
							}
							int count = 0;
							for (int j = hasJourney.get(i).getRoute().getStationList().size() - 1; j >= 0; j--) {
								col.addElement(hasJourney.get(i).getRoute().getStationList().get(j));
								if(j == hasJourney.get(i).getRoute().getStationList().size() - 1){
									cur.addElement(Calculate.minToHour(hasJourney.get(i).getReturnTime()));
								}
								else{
									cur.addElement(RouteManager.getManager().getHashMap().get(hasJourney.get(i).getRoute()).getReturnTimetable(hasJourney.get(i)).get(j + 1));
									count = j;
								}
							}
							col.addElement("Central");
							cur.addElement(RouteManager.getManager().getHashMap().get(hasJourney.get(i).getRoute()).getReturnTimetable(hasJourney.get(i)).get(count));
							row.addElement(col);
							row.addElement(cur);
							JTable table = new JTable(row,col);
							
							panel.add("West", label);
							panel.add("Center", table);
							
							giantPanel.add(panel);
						}
						js = new JScrollPane(giantPanel);
					}
					init();
				}

			}
		});
		
		JLabel stationLabel = new JLabel("Station:", JLabel.RIGHT);
		
		JButton freshButton = new JButton("Refresh");
		freshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		
		northPanel1.add(freshButton);
		northPanel1.add(stationLabel);
		northPanel1.add(stationBox);
		
		stopPanel.add("North", northPanel1);
		stopPanel.add("Center", centerPanel);
		
		stopBoard.add(stopPanel);
		
		init();
		update();
	}
	
	/**
	 * The init method of stopBoardGUI class
	 * This method is used to refresh components.
	 */
	public void init(){
		centerPanel.removeAll();
		centerPanel.updateUI();
		centerPanel.add(js);
	}
	
	/**
	 * The update method of stopBoardGUI class
	 * This method is used to refresh select items.
	 */
	public void update(){
		stationBox.removeAllItems();
		stationBox.addItem("");
		for(int i = 0; i < Controller.getStationData().size(); i++){
			stationBox.addItem(Controller.getStationData().get(i));
		}
	}
	
}
