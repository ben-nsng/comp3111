package hkust.cse.calendar.gui;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.user.UserManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.util.LinkedList;

public class AvailableTimeListDialog extends JFrame{
	public TimeSpan firstTime;
	private Appt currAppt;
	private ApptStorageControllerImpl _controller;
	private JPanel centerPanel;
	private JLabel aTimeL;
	private JList list;
	private DefaultListModel listModel;
	private JScrollPane sPane;
	private AppScheduler apps;
	private JFrame frame;
	private Timestamp newts;
	private Timestamp newts2;
	private JTextField ATL;
	private String[] item;
	private JLabel STimeL;
	private JLabel ETimeL;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel contentPane;
	
	public AvailableTimeListDialog(Appt a, ApptStorageControllerImpl controller, Timestamp ts){
		currAppt = a;
		_controller = controller;
		setTitle("Avalable Time List(Start Time with 15 minutes interval)");

		/*STimeL = new JLabel("Start Time: ");
		ETimeL = new JLabel("End Time: ");
		contentPane = new JPanel();
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftPanel.add(STimeL);
		contentPane.add(leftPanel);
		rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rightPanel.add(ETimeL);
		contentPane.add(rightPanel);
		//JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		/*centerPanel = new JPanel( new BorderLayout() );
		centerPanel.add( leftPanel, BorderLayout.WEST );
		centerPanel.add( rightPanel, BorderLayout.EAST );
		setContentPane(leftPanel);
		rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rightPanel.add(ETimeL);
		setContentPane(rightPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		rightPanel = new JPanel();*/
		
		this.setLayout(new BorderLayout());
		this.setLocationByPlatform(true);
		this.setSize(500,400);
		listModel = new DefaultListModel(); 
		list = new JList(listModel); 
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		list.setSelectedIndex(0); 
		list.setVisibleRowCount(5); 
		JScrollPane listScrollPane = new JScrollPane(list); 
		JPanel buttonPane = new JPanel(); 
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); 
		add(listScrollPane, BorderLayout.CENTER); 
		add(buttonPane, BorderLayout.PAGE_END); 
		this.setVisible(true);
		
		newts = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), ts.getHours(), ts.getMinutes(), ts.getSeconds(), 0);
		newts2 = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(),ts.getHours(), ts.getMinutes()+15, 0, 0);
		Timestamp end_day_time = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate(), 18, 0, 0, 0);
		AdjustTS(newts);
		AdjustTS(newts2);
		LinkedList<TimeSpan> aTime = new LinkedList<TimeSpan>();

		while(aTime.size()==0){
				while(newts.equals(end_day_time) == false && newts.before(end_day_time)){
					boolean noTimeConflict = true;			
					TimeSpan tspan = new TimeSpan(newts, newts2);	
					UserManagement um = UserManagement.getInstance();			
						for(int i=0; i<currAppt.getWaitingList().size(); i++) {			
							for(int j =0; j<_controller.RetrieveAppts(um.getUser(currAppt.getWaitingList().get(i)), tspan).length; j++)			
								if(_controller.RetrieveAppts(um.getUser(currAppt.getWaitingList().get(i)), tspan)[j].getID()!=currAppt.getID())			
									noTimeConflict = false;			
						}	
						for(int i=0; i<currAppt.getAttendList().size(); i++) {						
							for(int j =0; j<_controller.RetrieveAppts(um.getUser(currAppt.getAttendList().get(i)), tspan).length; j++)			
								if(_controller.RetrieveAppts(um.getUser(currAppt.getAttendList().get(i)), tspan)[j].getID()!=currAppt.getID())			
									noTimeConflict = false;		
						}
					//System.out.println(tspan.StartTime());			
					//System.out.println(tspan.EndTime());			
						if( noTimeConflict ){			
							aTime.add(tspan);
						}
					newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate(),newts.getHours(), newts.getMinutes()+15, 0, 0);			
					newts2 = new Timestamp(newts2.getYear(), newts2.getMonth(), newts2.getDate(),newts2.getHours(), newts2.getMinutes()+15, 0, 0);			
					AdjustTS(newts);			
					AdjustTS(newts2);		
					//System.out.println(aTime.next()); 		
					//System.out.println(tspan.EndTime());			
				}
				if (aTime.size()==0){
					newts = new Timestamp(newts.getYear(), newts.getMonth(), newts.getDate()+1,8, 0, 0, 0);			
					newts2 = new Timestamp(newts2.getYear(), newts2.getMonth(), newts2.getDate()+1,8, 15, 0, 0);	
					end_day_time = new Timestamp(ts.getYear(), ts.getMonth(), ts.getDate()+1, 18, 0, 0, 0);
				}
					
			}
		//	listModel.addElement(aTime);
		for (int i = 0; i < aTime.size(); i++)  
			listModel.addElement(aTime.get(i).StartTime());
		firstTime= aTime.getFirst();
		//System.out.println(aTime.get(i).StartTime());  
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
