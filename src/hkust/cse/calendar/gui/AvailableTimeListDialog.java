package hkust.cse.calendar.gui;

import java.awt.BorderLayout;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Appt;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AvailableTimeListDialog extends JFrame{
	private Appt currAppt;
	private ApptStorageControllerImpl _controller;
	private JPanel centerPanel;
	private JLabel aTimeL;
	private JList list;
	private DefaultListModel model;
	private JScrollPane sPane;
	
	public AvailableTimeListDialog(Appt a, ApptStorageControllerImpl controller){
		currAppt = a;
		_controller = controller;
		centerPanel = new JPanel();
		aTimeL = new JLabel("Available Time : ");
		centerPanel.add(aTimeL);
		model = new DefaultListModel();
		list = new JList(model);
		sPane = new JScrollPane(list);
		centerPanel.add(sPane);
		add(centerPanel, BorderLayout.CENTER);
		this.setSize(300, 200);
		this.show();
	}
}
