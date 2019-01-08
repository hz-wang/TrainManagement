package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Journey;
import com.secw.manage.Controller;
import com.secw.manage.JourneyManager;
import com.secw.tool.Calculate;

/**
 * Title		: JourneyGUI.java
 * Description	: This is the journey management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class JourneyGUI {
	/**
	 * The constructor of the journeyGUi class
	 * @param panel
	 */
	public JourneyGUI(JPanel panel) {
		init(panel);
	}
	
	/**
	 * The init method of journeyGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		ArrayList<Journey> journeyList = Controller.getJourneyData();
		
		Vector<Object> row = new Vector<Object>();
		Vector<Object> col = new Vector<Object>();
		col.addElement("Journey ID");
		col.addElement("Train ID");
		col.addElement("Driver ID");
		col.addElement("Route ID");
		col.addElement("StartTime");
		col.addElement("State");
		Vector<Object> cur = new Vector<Object>();
		for(int i = 0; i < journeyList.size(); i++){
			cur = new Vector<Object>();
			cur.addElement(journeyList.get(i).getId());
			cur.addElement(journeyList.get(i).getTrain().getId());
			cur.addElement(journeyList.get(i).getDriver().getId());
			cur.addElement(journeyList.get(i).getRoute().getId());
			cur.addElement(Calculate.minToHour(journeyList.get(i).getStartTime()));
			cur.addElement(journeyList.get(i).getTrain().getStatus());
			row.addElement(cur);
		}
		
		JTable table = new JTable(row, col);
		table.setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);

		JScrollPane js = new JScrollPane(table);
		
		JPanel boxPanel = new JPanel(new GridLayout(1, 8, 10, 0));
		JLabel tLabel = new JLabel("Train:",SwingConstants.RIGHT);
		JComboBox<String> tBox = new JComboBox<String>();
		tBox.addItem("");
		for(int i = 0; i < Controller.getAvailableTrainData().size(); i++){
			tBox.addItem("" + Controller.getAvailableTrainData().get(i).getId());
		}
		JLabel dLabel = new JLabel("Driver:",SwingConstants.RIGHT);
		JComboBox<String> dBox = new JComboBox<String>();
		dBox.addItem("");
		for(int i = 0; i < Controller.getAvailableDriverData().size(); i++){
			dBox.addItem("" + Controller.getAvailableDriverData().get(i).getId());
		}
		JLabel rLabel = new JLabel("Route:",SwingConstants.RIGHT);
		JComboBox<String> rBox = new JComboBox<String>();
		rBox.addItem("");
		for(int i = 0; i < Controller.getRouteData().size(); i++){
			rBox.addItem("" + Controller.getRouteData().get(i).getId());
		}
		JLabel sLabel = new JLabel("Time:",SwingConstants.RIGHT);

		JTextField sField = new JTextField();
		boxPanel.add(tLabel);
		boxPanel.add(tBox);
		boxPanel.add(dLabel);
		boxPanel.add(dBox);
		boxPanel.add(rLabel);
		boxPanel.add(rBox);
		boxPanel.add(sLabel);
		boxPanel.add(sField);
		
		JPanel showPanel = new JPanel(new BorderLayout());
		showPanel.add("Center", js);
		showPanel.add("North", boxPanel);
		
		JPanel p = new JPanel(new GridLayout(3, 1, 60,60));
		p.setBorder(new EmptyBorder(20, 20, 20, 20));	//TODO
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tBox.getSelectedItem().toString() != "" && dBox.getSelectedItem().toString() != "" &&
						rBox.getSelectedItem().toString() != "" && !(sField.getText().equals(""))){ 
					int time = Calculate.stringToTime(sField.getText());
					if(Clock.getTime() > time){
						JOptionPane.showMessageDialog(null, "Start time is too late!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else if(Calculate.checkReturnCentralStation(time, Integer.parseInt((String) rBox.getSelectedItem()))){
						JourneyManager.add(
							Controller.egodic(Integer.parseInt(tBox.getSelectedItem().toString()), "Train"),
							Controller.egodic(Integer.parseInt(dBox.getSelectedItem().toString()), "Driver"),
							Controller.egodic(Integer.parseInt(rBox.getSelectedItem().toString()), "Route"),
							time);
						Controller.changeStatus(Integer.parseInt(tBox.getSelectedItem().toString()), Integer.parseInt(dBox.getSelectedItem().toString()));
						init(panel);
					}
					else {
						JOptionPane.showMessageDialog(null, "Train can not be back before 24:00!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Input!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton deleteButton = new JButton("Delete");
		JButton alterButton = new JButton("Alter");
		
		panel.add("Center", showPanel);
		p.add(addButton);
		p.add(deleteButton);
		p.add(alterButton);
		panel.add("East",p);
	}
}
