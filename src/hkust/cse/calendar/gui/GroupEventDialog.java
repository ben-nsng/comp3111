package hkust.cse.calendar.gui;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Location;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class GroupEventDialog extends JDialog{
	private static final long serialVersionUID = 1L;

	private ApptStorageControllerImpl _controller;
	 
	private DefaultListModel listModel;

	private JList list;
	private JScrollPane listScrollPane;
	private JPanel apptPane;
	private JPanel buttonPane;
	private JComboBox availableUsers;
	private JButton addButton;
	private JButton removeButton;
	private JButton nextButton;
	private JButton finishButton;
	private JLabel dateL;
	private JTextField yearF;
	private JTextField monthF;
	private JTextField dayF;
	private JLabel sTimeL;
	private JTextField sTimeH;
	private JTextField sTimeM;
	private JLabel eTimeL;
	private JTextField eTimeH;
	private JTextField eTimeM;
	private JScrollPane availableTimePane;

	public GroupEventDialog(ApptStorageControllerImpl controller) {
		_controller = controller;
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setSize(800, 500);
		
		//new list model
		listModel = new DefaultListModel();	
		list = new JList(listModel);	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		list.setSelectedIndex(0);	
		list.setVisibleRowCount(5);		
		
		
		listScrollPane = new JScrollPane(list);	 
		
		//select users
		availableUsers = new JComboBox();
		
		//add button
		addButton = new JButton("Add");		
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//check if there are any duplicated items
			}
		});	
		addButton.setEnabled(true);	
		
		//remove button
		removeButton = new JButton("Remove");	 
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int index = list.getSelectedIndex();
				if (index != -1){
					listModel.removeElementAt(index);
				}
			}
		});	
		nextButton = new JButton("Next");	
		finishButton = new JButton("Finish");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				addButton.setVisible(false);
				removeButton.setVisible(false);
				nextButton.setVisible(false);
				finishButton.setVisible(true);
				listScrollPane.setVisible(false);
				availableUsers.setVisible(false);
				apptPane.setVisible(true);
				availableTimePane.setVisible(true);
			}
		});
		
		//appointment panel
		apptPane = new JPanel();
		dateL = new JLabel("Appointment Date");
		yearF = new JTextField(6);
		monthF = new JTextField(4);
		dayF = new JTextField(4);
		sTimeL = new JLabel("Start Time");
		sTimeH = new JTextField(4);
		sTimeM = new JTextField(4);
		eTimeL = new JLabel("End Time");
		eTimeH = new JTextField(4);
		eTimeM = new JTextField(4);
		apptPane.add(dateL);
		apptPane.add(yearF);
		apptPane.add(monthF);
		apptPane.add(dayF);
		apptPane.add(sTimeL);
		apptPane.add(sTimeH);
		apptPane.add(sTimeM);
		apptPane.add(eTimeL);
		apptPane.add(eTimeH);
		apptPane.add(eTimeM);
		
		//available time panel
		availableTimePane = new JScrollPane();
		
		//button panel
		buttonPane = new JPanel();	
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));	
		buttonPane.add(availableUsers); 
		buttonPane.add(addButton);			 
		buttonPane.add(removeButton); 
		buttonPane.add(nextButton); 

		buttonPane.add(finishButton); 
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	
		add(apptPane, BorderLayout.NORTH);
		add(listScrollPane, BorderLayout.CENTER);	 
		add(availableTimePane, BorderLayout.WEST);
		add(buttonPane, BorderLayout.PAGE_END); 
		this.setVisible(true);
		finishButton.setVisible(false);
		apptPane.setVisible(false);
		availableTimePane.setVisible(false);
	}
}
