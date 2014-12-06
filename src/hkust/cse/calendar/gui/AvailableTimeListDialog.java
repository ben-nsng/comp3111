package hkust.cse.calendar.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.sun.glass.events.WindowEvent;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.Location;
import hkust.cse.calendar.gui.LocationsDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class AvailableTimeListDialog extends JFrame{
	private Appt currAppt;
	private ApptStorageControllerImpl _controller;
	private JPanel centerPanel;
	private JLabel aTimeL;
	private JList list;
	private DefaultListModel listModel;
	private JScrollPane sPane;
	private AppScheduler apps;
	private  JFrame frame;
	private Timestamp newts;
	private JTextField ATL;
	private String[] item;
	public AvailableTimeListDialog(Appt a, ApptStorageControllerImpl controller, Timestamp ts){
		currAppt = a;
		_controller = controller;
		
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setSize(300, 200);
		listModel = new DefaultListModel();	
		list = new JList(listModel);	
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		list.setSelectedIndex(0);	
		list.setVisibleRowCount(5);		
		JScrollPane listScrollPane = new JScrollPane(list);	 
		ATL = new JTextField();
		JPanel buttonPane = new JPanel();	
		//buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));	
		buttonPane.add(ATL); 
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));	
		add(listScrollPane, BorderLayout.CENTER);	 
		add(buttonPane, BorderLayout.PAGE_END); 
		this.setVisible(true);
		
		
		/*centerPanel = new JPanel();
		aTimeL = new JLabel("Available Time : ");
		centerPanel.add(aTimeL);
		model = new DefaultListModel();
		list = new JList(model);
		sPane = new JScrollPane(list);
		centerPanel.add(sPane);
		add(centerPanel, BorderLayout.CENTER);
		this.setSize(300, 300);
		this.show();*/

		newts=new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), ts.getHours(), ts.getMinutes(), ts.getSeconds(), 0);
		Timestamp end_day_time = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), 18, 1, 0, 0);
		
		while(newts.equals(end_day_time) ==false && newts.before(end_day_time)){
			int counter=0;
			if (newts.equals(end_day_time))
				break;
			else if (newts.after(currAppt.TimeSpan().StartTime())  && newts.before(currAppt.TimeSpan().EndTime()) ){
				newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate(),newts.getHours(), newts.getMinutes()+15, 0, 0);
				AdjustTS(newts);
			}
			else if (newts.equals(currAppt.TimeSpan().StartTime())){
				newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate(),newts.getHours(), newts.getMinutes()+15, 0, 0);
				AdjustTS(newts);
			}
			else if (newts.equals(currAppt.TimeSpan().EndTime())){
				newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate(),newts.getHours(), newts.getMinutes()+15, 0, 0);
				AdjustTS(newts);
			}
			else {				
				//System.out.println(newts);	
				listModel.addElement(newts);
				newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate(),newts.getHours(), newts.getMinutes()+15, 0, 0);
				AdjustTS(newts);
			}
		}
		
		//System.out.println(ts);
		//System.out.println(currAppt.TimeSpan().StartTime());
		//System.out.println(currAppt.TimeSpan().EndTime());

	}
	
   
    

		
	public void AdjustTS(Timestamp ts){
		if(ts.getMinutes()%15!=0){
			if(ts.getMinutes()>45){
				newts = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), ts.getHours()+1, 0, 0, 0);
			}
			else {
				newts = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), ts.getHours(), (ts.getMinutes()/45 +1)*15, 0, 0);
			}
		}
	}
}

