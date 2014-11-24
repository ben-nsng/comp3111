package hkust.cse.calendar.gui;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.Location;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AddUserDialog extends JFrame{
	
	private Appt currAppt;
	private ApptStorageControllerImpl _controller;
	private JPanel centerPanel;
	private JLabel sUserL;
	private JList list;
	private DefaultListModel model;
	private JScrollPane sPane;
	private JLabel aUserL;
	private JComboBox aUserCB;
	private JButton addBut;
	private JButton removeBut;
	private JButton confirmBut;
	
	public AddUserDialog(Appt a, ApptStorageControllerImpl controller) {
		setLayout(new BorderLayout());
		currAppt = a;
		_controller = controller;
		centerPanel = new JPanel();
		sUserL = new JLabel("Selected Participants : ");
		model = new DefaultListModel();
		list = new JList(model);
		sPane = new JScrollPane(list);
		aUserL = new JLabel("Available Users : ");
		aUserCB = new JComboBox();
		addBut = new JButton("Add");
		removeBut = new JButton("Remove");
		confirmBut = new JButton("Confirm");
		centerPanel.add(sUserL);
		centerPanel.add(sPane);
		centerPanel.add(aUserL);
		centerPanel.add(aUserCB);
		centerPanel.add(addBut);
		centerPanel.add(removeBut);
		centerPanel.add(confirmBut);
		add(centerPanel, BorderLayout.CENTER);
		this.setSize(300, 300);
		this.show();
	}
	
	public void clearAllList() {
		currAppt.getWaitingList().clear();
		currAppt.getRejectList().clear();
		currAppt.getAttendList().clear();
	}
	
	//select user and place them into waiting list
}
