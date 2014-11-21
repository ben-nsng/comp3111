package hkust.cse.calendar.gui;

import java.awt.BorderLayout;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Appt;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvailableTimeListDialog extends JFrame{
	private Appt currAppt;
	private ApptStorageControllerImpl _controller;
	private JPanel centerPanel;
	private JLabel aTimeL;
	
	public AvailableTimeListDialog(Appt a, ApptStorageControllerImpl controller){
		currAppt = a;
		_controller = controller;
		centerPanel = new JPanel();
		aTimeL = new JLabel("Available Time : ");
		centerPanel.add(aTimeL);
		add(centerPanel, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.show();
	}
}
