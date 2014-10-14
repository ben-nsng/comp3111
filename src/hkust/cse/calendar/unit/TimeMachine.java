package hkust.cse.calendar.unit;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.gui.CalGrid;
import hkust.cse.calendar.listener.TimeMachineListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class TimeMachine implements ActionListener {

	private Timer timer;
	private Timestamp startTime;
	private Timestamp currentTime;
	private Timestamp timeDelay;
	private Boolean isStart;
	//private CalGrid parent;
	private List<TimeMachineListener> listeners;
	
	
	public TimeMachine(Timestamp startTime, Timestamp timeDelay) {
		
		this.startTime = startTime;
		this.currentTime = startTime;
		this.timeDelay = timeDelay;
		this.isStart = false;
		//this.parent = parent;
		this.timer = new Timer(timeDelay.getSeconds() , this);
		this.listeners = new ArrayList<TimeMachineListener>();
	}
	
	public void addElpased(TimeMachineListener listener) {
		this.listeners.add(listener);
	}
	
	public void changeStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	public void changeTimeDelay(Timestamp timeDelay) {
		this.timeDelay = timeDelay;
	}
	
	public void start() {
		if(this.isStart) return;
		this.timer.start();
	}
	
	public void stop() {
		if(!this.isStart) return;
		this.timer.stop();
	}
	
	public Boolean IsStart() {
		return this.isStart;
	}
	
	public Boolean IsStop() {
		return !this.isStart;
	}
	
	public String toString() {
		return this.currentTime.getYear() + "-" + 
				this.currentTime.getMonth() + "-" + 
				this.currentTime.getDay() + " " + 
				this.currentTime.getHours() + ":" + 
				this.currentTime.getMinutes() + ":" + 
				this.currentTime.getSeconds();
	}
	
	public Timestamp getCurrentTime() {
		return this.currentTime;
	}
	
	public Timestamp getTimeDelay() {
		return this.timeDelay;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.timer)	{
			
			//check if there are appointments from currenttime to currenttime + timedelay
			//if yes 
			/*Appt[] appts = parent.controller.RetrieveAppts(parent.mCurrUser, new TimeSpan(this.currentTime, this.timeDelay));
			for(int i = 0; i < appts.length; i++) {
				Appt currAppt = appts[i];
				
			}*/
			for(TimeMachineListener l : listeners) {
				l.timeElapsed(this, null);
			}
			this.currentTime.setSeconds(this.currentTime.getSeconds() + this.timeDelay.getSeconds());
		}
		
	}
	
}
