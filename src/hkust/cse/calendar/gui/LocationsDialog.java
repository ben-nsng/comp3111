package hkust.cse.calendar.gui;


import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import javax.swing.BoxLayout;

import javax.swing.DefaultListModel;

import javax.swing.JButton;

import javax.swing.JList;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.ListSelectionModel;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;

import javax.swing.JFrame;

import hkust.cse.calendar.unit.Location;

import javax.swing.JTextField;

import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class LocationsDialog extends JFrame {

	
	private static final long serialVersionUID = 1L;

	private ApptStorageControllerImpl _controller;
	 
	private DefaultListModel listModel;

	private JList list;

	private JTextField locNameText;

	public LocationsDialog(ApptStorageControllerImpl controller) {
		_controller = controller;
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setSize(300, 200);
		
		//new list model
		listModel = new DefaultListModel();	
		list = new JList(listModel);	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		list.setSelectedIndex(0);	
		//list.addListSelectionListener(new ListSelectionListener(){});
		list.setVisibleRowCount(5);		
		
		
		JScrollPane listScrollPane = new JScrollPane(list);	 
		
		//add button
		JButton addButton = new JButton("Add");		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//remove any space at the beginning or end of text
				locNameText.setText(locNameText.getText().trim());
				
				//check if location input is empty
				if(locNameText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Empty Location" ,"INPUT ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//check if there are any duplicated items
				Location[] locations = _controller.getLocationList();
				for(int i = 0; i < locations.length; i++) {
					if (locations[i].getName().equals(locNameText.getText())) {
						JOptionPane.showMessageDialog(null, "Duplicate location" ,"INPUT ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				//add the item to the list
				listModel.addElement(new Location(locNameText.getText()));
				
				//save the location into storage
				saveLocations();
				
				//focus the location input
				locNameText.setText("");
				locNameText.requestFocus();
				
			}
		});	
		addButton.setEnabled(true);	
		
		//remove button
		JButton removeButton = new JButton("Remove");	 
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index != -1){
					listModel.removeElementAt(index);
					saveLocations();
				}
			}
		});	
		locNameText = new JTextField();
		
		//button panel
		JPanel buttonPane = new JPanel();	
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));	
		buttonPane.add(locNameText); 
		buttonPane.add(addButton);			 
		buttonPane.add(removeButton); 
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	
		add(listScrollPane, BorderLayout.CENTER);	 
		add(buttonPane, BorderLayout.PAGE_END); 
		this.setVisible(true);
		
		loadLocations();
		setLocationRelativeTo(null);
		
		//focus input
		locNameText.requestFocus();
	}

	private void loadLocations() {
		Location[] locations = _controller.getLocationList();
		listModel.clear();
		if (locations.length != 0) {
			for(Location loc:locations)
				listModel.addElement(loc);
		}
	}

	private void saveLocations(){
		int count = listModel.getSize();
		Location[] locations = new Location[count];
		for (int i = 0; i < count; i++) {
			locations[i] = (Location) listModel.elementAt(i);
		}
		_controller.setLocationList(locations);
	}

}