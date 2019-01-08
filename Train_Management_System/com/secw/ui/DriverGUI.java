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

import com.secw.entity.Driver;
import com.secw.manage.Controller;
import com.secw.manage.DriverManager;

/**
 * Title		: DriverGUI.java
 * Description	: This is the driver management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class DriverGUI {
	/**
	 * The constructor of the driverGUi class
	 * @param panel
	 */
	public DriverGUI(JPanel panel) {
		init(panel);
	}
	
	/**
	 * The init method of driverGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		ArrayList<Driver> t = Controller.getDriverData();
		
		Vector<Object> row = new Vector<Object>();
		Vector<Object> col = new Vector<Object>();
		col.addElement("Driver ID");
		col.addElement("Driver State");
		Vector<Object> cur = new Vector<Object>();
		for(int i = 0; i < t.size(); i++){
			cur = new Vector<Object>();
			cur.addElement(t.get(i).getId());
			cur.addElement(t.get(i).getStatus());
			row.addElement(cur);
		}
		
		JTable table = new JTable(row, col);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		//table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.setFillsViewportHeight(true);

		JScrollPane js = new JScrollPane(table);

		JPanel p = new JPanel(new GridLayout(3, 1, 60,60));
		p.setBorder(new EmptyBorder(20, 20, 20, 20));
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DriverManager.getManager().add();
				init(panel);
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i != -1){
					Controller.removeAvailableDriver(i);
					if(DriverManager.getManager().remove(i) == false){
						JOptionPane.showMessageDialog(null, "Selected driver is running!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					init(panel);
				}
				else {
					JOptionPane.showMessageDialog(null, "No driver is selected!", "Error", JOptionPane.ERROR_MESSAGE);
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
