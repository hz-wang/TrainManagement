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

import com.secw.entity.Train;
import com.secw.manage.Controller;
import com.secw.manage.TrainManager;

/**
 * Title		: TrainGUI.java
 * Description	: This is the train management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class TrainGUI {
	/**
	 * The constructor of the trainGUi class
	 * @param panel
	 */
	public TrainGUI(JPanel panel) {
		init(panel);
	}
	
	/**
	 * The init method of trainGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	public void init(JPanel panel){
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		ArrayList<Train> t = Controller.getTrainData();
		
		Vector<Object> row = new Vector<Object>();
		Vector<Object> col = new Vector<Object>();
		col.addElement("Train ID");
		col.addElement("Train State");
		Vector<Object> cur = new Vector<Object>();
		for(int i = 0; i < t.size(); i++){
			cur = new Vector<Object>();
			cur.addElement(t.get(i).getId());
			cur.addElement(t.get(i).getStatus());
			row.addElement(cur);
		}
		
		JTable table = new JTable(row, col);
		table.setVisible(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		//table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.setFillsViewportHeight(true);

		JScrollPane js = new JScrollPane(table);

		JPanel p = new JPanel(new GridLayout(3, 1, 60,60));
		p.setBorder(new EmptyBorder(20, 20, 20, 20));//TODO
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TrainManager.getManager().add();
				init(panel);
			}
		});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i != -1){
					Controller.removeAvailableTrain(i);
					if(TrainManager.getManager().remove(i) == false){
						JOptionPane.showMessageDialog(null, "Selected train is running!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					init(panel);
				}
				else {
					JOptionPane.showMessageDialog(null, "No train is selected!", "Error", JOptionPane.ERROR_MESSAGE);
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
