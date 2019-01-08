package com.secw.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Title		: MainGUI.java
 * Description	: This is the main class to create other GUIs
 * @author Zhang Yufeng, Wang Haozhe
 */
@SuppressWarnings("serial")
public class MainGUI extends JFrame  {
	
	/**
	 * The constructor of the mainGUi class
	 * It is used to initialize all conponents.
	 * @exception Exception
	 */
	public MainGUI() {
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		setResizable(false);
		setTitle("Train Management System");
		getContentPane().setLayout(new BorderLayout(10, 10));
		((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 10, 20,10));

		JPanel panel0 = new ImagePanel();
		panel0.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Train Management System", SwingConstants.CENTER);//TODO LABEL 会有warning提示
		title.setFont(new Font("微软雅黑", Font.BOLD, 36));
		title.setBorder(new EmptyBorder(80, 0, 80, 0));
		
		panel0.add("Center",title);
		panel0.add("East", Clock.getClock().getClockLabel());
		getContentPane().add("North", panel0);

		
		// Panel
		JPanel panel1 = new JPanel();
		getContentPane().add("Center", panel1);
		new HomeGUI(panel1);

		// Option buttons
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 7, 10, 10));
		JButton homeButton = new JButton("Home");
		homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeGUI(panel1);
			}
		});
		panel2.add(homeButton);
		JButton trainButton = new JButton("Train");
		trainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TrainGUI(panel1);
			}
		});
		panel2.add(trainButton);
		JButton stationButton = new JButton("Station");
		stationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StationGUI(panel1);
			}
		});
		panel2.add(stationButton);
		JButton driverButton = new JButton("Driver");
		panel2.add(driverButton);
		driverButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DriverGUI(panel1);
			}
		});
		JButton routeButton = new JButton("Route");
		panel2.add(routeButton);
		routeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new RouteGUI(panel1);
			}
		});
		JButton journeyButton = new JButton("Journey");
		panel2.add(journeyButton);
		journeyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new JourneyGUI(panel1);
			}
		});
		JButton timeTableButton = new JButton("Timetable");
		panel2.add(timeTableButton);
		timeTableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TimetableGUI(panel1);
			}
		});
		getContentPane().add("South", panel2);

		new StopBoardGUI();
		new TrainBoardGUI();
	}
}
