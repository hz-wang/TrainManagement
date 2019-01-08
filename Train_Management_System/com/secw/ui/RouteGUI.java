package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Route;
import com.secw.entity.Station;
import com.secw.manage.Controller;
import com.secw.manage.RouteManager;
import com.secw.manage.StationManager;

/**
 * Title		: routeGUI.java
 * Description	: This is the driver management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class RouteGUI {
	/**
	 * The constructor of the routeGUi class
	 * @param panel
	 */
	public RouteGUI(JPanel panel) {
		init(panel);
	}
	
	/**
	 * The init method of routeGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		ArrayList<Route> t = Controller.getRouteData();
		
		Vector<Object> row = new Vector<Object>();
		Vector<Object> col = new Vector<Object>();
		col.addElement("Route ID");
		col.addElement("Route Line");
		Vector<Object> cur = new Vector<Object>();
		for(int i = 0; i < t.size(); i++){
			cur = new Vector<Object>();
			cur.addElement(t.get(i).getId());
			cur.addElement(t.get(i).toString());
			row.addElement(cur);
		}
		
		JTable table = new JTable(row, col);
		table.setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);

		JScrollPane js = new JScrollPane(table);
		
		JPanel p = new JPanel(new GridLayout(3, 1, 60,60));
		p.setBorder(new EmptyBorder(20, 20, 20, 20));//TODO
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				f.setVisible(true);
				f.setResizable(false);
				f.setBounds(600, 600, 400, 160);
				JPanel addPanel = new JPanel(new BorderLayout());
				JLabel title = new JLabel("Central Station",JLabel.LEFT);
				title.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD, 12));
				
				JComboBox<String> stationComboBox = new JComboBox<String>();
				stationComboBox.addItem("");
				for(int i = 0; i < Controller.getStationData().size(); i++){
					stationComboBox.addItem("" + Controller.getStationData().get(i).getName());
				}
				JTextField timeSpanField = new JTextField();
				JPanel gridPanel = new JPanel(new GridLayout(1,2,5,5));
				gridPanel.setBorder(new EmptyBorder(5, 30, 5, 30));

				JButton addButton = new JButton("Add");
				ArrayList<Station> currentStationList = new ArrayList<Station>();
				ArrayList<Integer> currentTimeSpanList = new ArrayList<Integer>();
				addButton.addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						String result = title.getText();
						int timeSpan = 0;
						if ((timeSpanField.getText().equals(""))) {
							JOptionPane.showMessageDialog(null, "Running time between two station can not be null!", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else {
							timeSpan = Integer.parseInt(timeSpanField.getText());
							result += "->" + stationComboBox.getSelectedItem();
							title.setText(result);
							//TODO É¾³ý±»Ñ¡¹ýµÄÕ¾
							currentStationList.add(StationManager.getManager().egodic((String) stationComboBox.getSelectedItem()));
							currentTimeSpanList.add(timeSpan);
						}
					}
				});
				JButton finishButton = new JButton("Finish");
				finishButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						RouteManager.getManager().add(currentStationList, currentTimeSpanList);
						init(panel);
						f.dispose();
					}
				});
				JPanel buttonPanel  = new JPanel(new GridLayout(1, 2, 5,5));
				buttonPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
				buttonPanel.add(addButton);
				buttonPanel.add(finishButton);

				gridPanel.add(stationComboBox);
				gridPanel.add( timeSpanField);
				addPanel.add("Center",gridPanel);
				addPanel.add("North",title);
				addPanel.add("South",buttonPanel);
				f.add(addPanel);
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i != -1){
					Controller.getRouteData().remove(i);
					init(panel);
				}
				else {
					JOptionPane.showMessageDialog(null, "No route is selected!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton alterButton = new JButton("Alter");
		
		panel.add("Center", js);
		p.add(addButton);
		p.add(deleteButton);
		p.add(alterButton);
		panel.add("East",p);
	}
}
