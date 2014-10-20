package hkust.cse.calendar.gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import javax.swing.JFrame;
import hkust.cse.calendar.unit.Location;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class LocationsDialog extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ApptStorageControllerImpl _controller;
	
	private DefaultListModel<Location> listModel;
	private JList<Location> list;
	private JTextField locNameText;
	private JButton btnAdd;
	private JButton btnRemove;
	
	public LocationsDialog(ApptStorageControllerImpl controller){
		
		JPanel contentPane = new JPanel();
		locNameText = new JTextField(30);
		btnAdd = new JButton("Add");
		btnRemove = new JButton("Add");
		
		contentPane.add(locNameText);
		contentPane.add(btnAdd);
		contentPane.add(btnRemove);
		
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		
		_controller = controller;
		
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setSize(300, 200);
		
		listModel = new DefaultListModel<Location>();
		
		list = new JList<Location>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener(){
			
		}

}
}