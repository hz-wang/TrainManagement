package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Train;
import com.secw.manage.Controller;
import com.secw.tool.GetLocationIMP;

/**
 * Title		: TrainBoardGUI.java
 * Description	: This is the train board GUI
 * @author Zhang Yufeng, Wang Haozhe
 */
public class TrainBoardGUI {
	JFrame trainBoard = new JFrame();
	JPanel trainPanel = new JPanel(new BorderLayout());
	JTextField nextStation = new JTextField();
	JPanel northPanel2 = new JPanel(new GridLayout(1,2,3,3));
	JLabel trainLabel = new JLabel("Train Id:", JLabel.RIGHT);
	JComboBox<String> trainBox = new JComboBox<String>();

	/**
	 * The constructor of the driverGUi class
	 * It is used to set default text.
	 */
	public TrainBoardGUI(){
		trainBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trainBoard.setBounds(920, 600, 400, 100);
		trainBoard.setResizable(false);
		trainBoard.setVisible(true);
		trainBoard.setTitle("Train Board");
		
		nextStation.setBackground(Color.BLACK);
		nextStation.setForeground(Color.RED);
		nextStation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		
		northPanel2.setBorder(new EmptyBorder(0, 20, 0,20));
		
		JButton freshButton = new JButton("Refresh");
		freshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
			}
		});
		
		northPanel2.add(freshButton);
		northPanel2.add(trainLabel);
		northPanel2.add(trainBox);
		
		trainPanel.add("North", northPanel2);
		trainPanel.add("Center", nextStation);
		
		trainBoard.add(trainPanel);
		
		trainBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED && !((String)trainBox.getSelectedItem()).equals("")){
					nextStation.setText("Train " + (String)trainBox.getSelectedItem() + " Next Stop:" + new GetLocationIMP().getLocation((Train)Controller.egodic(Integer.parseInt((String)trainBox.getSelectedItem()), "AllTrain")));
				}
			}
		});
		
		init();
	}
	
	/**
	 * The init method of trainBoardGUI class
	 * This method is used to refresh components.
	 */
	public void init(){
		trainBox.removeAllItems();
		trainBox.addItem("");
		
		for(int i = 0; i < Controller.getTrainData().size(); i++){
			trainBox.addItem("" + Controller.getTrainData().get(i).getId());
		}
		
	}
}
