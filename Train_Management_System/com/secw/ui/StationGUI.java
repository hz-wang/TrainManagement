package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Station;
import com.secw.manage.Controller;
import com.secw.manage.StationManager;

/**
 * Title		: StationGUI.java
 * Description	: This is the station management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class StationGUI {
	/**
	 * The constructor of the stationGUi class
	 * @param panel
	 */
	public StationGUI(JPanel panel) {
		init(panel);
	}
	
	/**
	 * The init method of stationGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		ArrayList<Station> t = Controller.getStationData();
		
		Vector<Object> row = new Vector<Object>();
		Vector<Object> col = new Vector<Object>();
		col.addElement("Station ID");
		col.addElement("Station Name");
		Vector<Object> cur = new Vector<Object>();
		for(int i = 0; i < t.size(); i++){
			cur = new Vector<Object>();
			cur.addElement(t.get(i).getId());
			cur.addElement(t.get(i).getName());
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
				String s = JOptionPane.showInputDialog("Station Name:");
				if(s != null){
					StationManager.getManager().add(s);
					init(panel);
				}
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i != -1){
					Controller.getStationData().remove(i);
					init(panel);
				}
				else {
					JOptionPane.showMessageDialog(null, "No station is selected!", "Error", JOptionPane.ERROR_MESSAGE);
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
