package com.secw.operation;

import java.awt.EventQueue;

import com.secw.manage.Controller;
import com.secw.ui.MainGUI;

/**
 * Title		: Main.java
 * Description	: This is the entrance of the project
 * @author Zhang Yufeng, Wang Haozhe
 */
public class Main {
	/**
	 * Main method of the class
	 * To launch the project.
	 * @param args
	 * @exception Exception
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Controller();
					new MainGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
