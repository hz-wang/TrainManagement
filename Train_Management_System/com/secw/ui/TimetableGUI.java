package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.secw.entity.Route;
import com.secw.entity.Timetable;
import com.secw.manage.Controller;
import com.secw.manage.RouteManager;
import com.secw.tool.Calculate;

/**
 * Title		: TimetableGUI.java
 * Description	: This is the timetable management page
 * @author Zhang Yufeng, Wang Haozhe
 */
public class TimetableGUI {
	JTable table = new JTable();
	JComboBox<String> routeBox = new JComboBox<String>();
	
	/**
	 * The constructor of the driverGUi class
	 * It is used to set default text.
	 * @param panel
	 */
	public TimetableGUI(JPanel panel) {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		routeBox.addItem("");
		for(int i = 0; i < Controller.getRouteData().size(); i++){
			routeBox.addItem("" + Controller.getRouteData().get(i).getId());
		}
		
		init(panel);
	}

	/**
	 * The init method of timetableGUI class
	 * This method is used to initialize all conponents.
	 * @param panel
	 */
	private void init(JPanel panel) {
		panel.removeAll();
		panel.updateUI();
		panel.setLayout(new BorderLayout());
		
		routeBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(evt.getStateChange() == ItemEvent.SELECTED){
					Vector<Object> row = new Vector<Object>();
					Vector<Object> col = new Vector<Object>();
					
					
					
					Timetable timetable = RouteManager.getManager().getHashMap().get((Route)Controller.egodic(Integer.parseInt((String)routeBox.getSelectedItem()), "Route"));
					
					int rowNumber = RouteManager.getManager().getStationList(Integer.parseInt((String)routeBox.getSelectedItem())).size();
					
					col.addElement("Route" + routeBox.getSelectedItem());
					int size = Controller.getJourneyData().size();
					for(int i = 0; i < size; i++){
						if(Controller.getJourneyData().get(i).getRoute().getId() == Integer.parseInt((String)routeBox.getSelectedItem()))
							col.addElement("Journey" + Controller.getJourneyData().get(i).getId());
					}
					Vector<Object> cur = new Vector<Object>();
					
					cur.addElement("Central Station");
					for(int i = 0; i < size; i++){
						if(Controller.getJourneyData().get(i).getRoute().getId() == Integer.parseInt((String)routeBox.getSelectedItem()))
							cur.addElement(Calculate.minToHour(Controller.getJourneyData().get(i).getStartTime()));
					}
					row.addElement(cur);
					for(int i = 0; i < rowNumber; i++){	
						cur = new Vector<Object>();
						cur.addElement(RouteManager.getManager().getStationList(Integer.parseInt((String)routeBox.getSelectedItem())).get(i).getName());
						for(int j = 0; j < size; j++){
							if(Controller.getJourneyData().get(j).getRoute().getId() == Integer.parseInt((String)routeBox.getSelectedItem()))
								cur.addElement(timetable.getOutWardTimetable(Controller.getJourneyData().get(j)).get(i));
						}
						row.addElement(cur);
					}
					
					int count = 0;
					for(int i = rowNumber - 1; i >= 0; i--){
						cur = new Vector<Object>();
						cur.addElement(RouteManager.getManager().getStationList(Integer.parseInt((String)routeBox.getSelectedItem())).get(i).getName());
						for(int j = 0; j < size; j++){
							if(Controller.getJourneyData().get(j).getRoute().getId() == Integer.parseInt((String)routeBox.getSelectedItem())){
								if(i == rowNumber - 1){
									cur.addElement(Calculate.minToHour(Controller.getJourneyData().get(j).getReturnTime()));
								}
								else{
									cur.addElement(timetable.getReturnTimetable(Controller.getJourneyData().get(j)).get(i + 1));
									count = i;
								}
							}
						}
						row.addElement(cur);
					}
					cur = new Vector<Object>();
					cur.addElement("Central Station");
					for(int i = 0; i < size; i++){
						if(Controller.getJourneyData().get(i).getRoute().getId() == Integer.parseInt((String)routeBox.getSelectedItem()))
							cur.addElement(timetable.getReturnTimetable(Controller.getJourneyData().get(i)).get(count));
					}
					row.addElement(cur);

					table = new JTable(row, col);
					init(panel);
				}
			}
		});
		
		JLabel rLabel = new JLabel("Route Id: ");
		
		JPanel boxPanel = new JPanel();
		boxPanel.add(rLabel);
		boxPanel.add(routeBox);
		
		routeBox.setBorder(new EmptyBorder(0, 40, 0, 0));
		
		JScrollPane js = new JScrollPane(table);
		
		panel.add("Center",js);
		panel.add("North",boxPanel);
	}

}
