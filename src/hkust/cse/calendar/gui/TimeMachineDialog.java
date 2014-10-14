package hkust.cse.calendar.gui;

import hkust.cse.calendar.listener.TimeMachineListener;
import hkust.cse.calendar.unit.TimeMachine;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class TimeMachineDialog extends JFrame implements ActionListener, TimeMachineListener {

	private JButton btnStartTime;
	private JButton btnStopTime;
	private TimeMachine machine;
	
	//start date params
	private JLabel sYearL;
	private JTextField sYearF;
	private JLabel sMonthL;
	private JTextField sMonthF;
	private JLabel sDayL;
	private JTextField sDayF;
	
	//end date params
	private JLabel eYearL;
	private JTextField eYearF;
	private JLabel eMonthL;
	private JTextField eMonthF;
	private JLabel eDayL;
	private JTextField eDayF;
	
	//start time params
	private JLabel sTimeHL;
	private JTextField sTimeH;
	private JLabel sTimeML;
	private JTextField sTimeM;
	
	//end time params
	private JLabel eTimeHL;
	private JTextField eTimeH;
	private JLabel eTimeML;
	private JTextField eTimeM;
	
	//time delay
	private JLabel dTimeHL;
	private JTextField dTimeH;
	private JLabel dTimeML;
	private JTextField dTimeM;
	
	public TimeMachineDialog(TimeMachine machine) {

		this.machine = machine;
		
		setTitle("Time Machine");
		
		Container contentPane;
		contentPane = getContentPane();
		
		JPanel psDate = new JPanel();
		Border sDateBorder = new TitledBorder(null, "Start DATE");
		psDate.setBorder(sDateBorder);

		sYearL = new JLabel("YEAR: ");
		psDate.add(sYearL);
		sYearF = new JTextField(6);
		psDate.add(sYearF);
		sMonthL = new JLabel("MONTH: ");
		psDate.add(sMonthL);
		sMonthF = new JTextField(4);
		psDate.add(sMonthF);
		sDayL = new JLabel("DAY: ");
		psDate.add(sDayL);
		sDayF = new JTextField(4);
		psDate.add(sDayF);

		JPanel peDate = new JPanel();
		Border eDateBorder = new TitledBorder(null, "End DATE");
		peDate.setBorder(eDateBorder);

		eYearL = new JLabel("YEAR: ");
		peDate.add(eYearL);
		eYearF = new JTextField(6);
		peDate.add(eYearF);
		eMonthL = new JLabel("MONTH: ");
		peDate.add(eMonthL);
		eMonthF = new JTextField(4);
		peDate.add(eMonthF);
		eDayL = new JLabel("DAY: ");
		peDate.add(eDayL);
		eDayF = new JTextField(4);
		peDate.add(eDayF);

		JPanel psTime = new JPanel();
		Border stimeBorder = new TitledBorder(null, "START TIME");
		psTime.setBorder(stimeBorder);
		sTimeHL = new JLabel("Hour");
		psTime.add(sTimeHL);
		sTimeH = new JTextField(4);
		psTime.add(sTimeH);
		sTimeML = new JLabel("Minute");
		psTime.add(sTimeML);
		sTimeM = new JTextField(4);
		psTime.add(sTimeM);

		JPanel peTime = new JPanel();
		Border etimeBorder = new TitledBorder(null, "END TIME");
		peTime.setBorder(etimeBorder);
		eTimeHL = new JLabel("Hour");
		peTime.add(eTimeHL);
		eTimeH = new JTextField(4);
		peTime.add(eTimeH);
		eTimeML = new JLabel("Minute");
		peTime.add(eTimeML);
		eTimeM = new JTextField(4);
		peTime.add(eTimeM);
		
		JPanel pTime = new JPanel();
		pTime.setLayout(new BorderLayout());
		pTime.add("West", psTime);
		pTime.add("East", peTime);

		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setBorder(new BevelBorder(BevelBorder.RAISED));
		top.add(psDate, BorderLayout.NORTH);
		top.add(peDate, BorderLayout.CENTER);
		top.add(pTime, BorderLayout.SOUTH);

		contentPane.add("North", top);
		
		// start & stop button
		JPanel butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnStartTime = new JButton("Start Time");
		btnStartTime.addActionListener(this);
		butPanel.add(btnStartTime);
		
		btnStopTime = new JButton("Stop Time");
		btnStopTime.addActionListener(this);
		butPanel.add(btnStopTime);
		
		contentPane.add("South", butPanel);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		if(machine.IsStart())
			setEnable(false);
		else
			setEnable(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btnStartTime) {
			machine.start();
			setEnable(false);
		}
		else if(arg0.getSource() == btnStopTime) {
			machine.stop();
			setEnable(true);
		}
	}
	
	public void timeElapsed(TimeMachine sender, Object o) {
		
	}
	
	private void setEnable(Boolean b) {
	}


}
