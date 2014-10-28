package hkust.cse.calendar.Main;

import java.sql.Timestamp;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.apptstorage.ApptStorageMemImpl;
import hkust.cse.calendar.listener.TimeMachineListener;
import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.TimeMachine;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;

public class TestMain implements TimeMachineListener {

	private TimeMachine machine;
	private ApptStorageControllerImpl controller;
	private User user;
	
	public static void main(String[] args) {
		
		TestMain test = new TestMain();
		test.RunTest();
	}
	
	public void RunTest() {
		this.TestStorage();
		this.TestTimeMachine();
	}
	
	public void TestStorage() {
		user = new User( "noname", "nopass");
		controller = new ApptStorageControllerImpl(new ApptStorageMemImpl(user));
		
		TimeSpan time0 = new TimeSpan(new Timestamp(2014 - 1900, 11, 24, 10, 30, 0, 0), new Timestamp(2014 - 1900, 11, 24, 11, 30, 0, 0));
		TimeSpan time1 = new TimeSpan(new Timestamp(2014 - 1900, 11, 24, 10, 0, 0, 0), new Timestamp(2014 - 1900, 11, 24, 11, 0, 0, 0));
		TimeSpan time2 = new TimeSpan(new Timestamp(2014 - 1900, 11, 24, 11, 0, 0, 0), new Timestamp(2014 - 1900, 11, 24, 12, 0, 0, 0));
		
		//create appt 1
		Appt appt = new Appt();
		appt.setTitle("test");
		appt.setTimeSpan(time1);
		controller.ManageAppt(appt, ApptStorageControllerImpl.NEW);

		//create appt 2
		appt = new Appt();
		appt.setTitle("test2");
		appt.setTimeSpan(time2);
		controller.ManageAppt(appt, ApptStorageControllerImpl.NEW);
		
		//retrieve appts
		Appt[] appts = controller.RetrieveAppts(user, time0);
		
		assert appts.length == 2;
		//assert appts.length != 1;
		
	}
	
	public void TestTimeMachine() {
		machine = new TimeMachine();
		machine.addElpasedListener(this);
		
		machine.changeStartTime(new Timestamp(2014 - 1900, 11, 24, 9, 0, 0, 0));
		machine.changeEndTime(new Timestamp(2014 - 1900, 11, 24, 12, 0, 0, 0));
		machine.changeTimeDelay(30 * 60 * 1000);
		
		machine.start();
		
		while(true)
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void timeElapsed(TimeMachine sender) {
		// TODO Auto-generated method stub
		System.out.println("Time: " + sender.getCurrentTime());
		
		String info = "";
		Timestamp next = (Timestamp)sender.getCurrentTime().clone();
		next.setTime(sender.getCurrentTime().getTime() + sender.getTimeDelay() * 2);
		
		Appt[] appts = controller.RetrieveAppts(user, new TimeSpan(sender.getCurrentTime(), next));
		for(int i = 0; i < appts.length; i++) {
			Appt currAppt = appts[i];
			Timestamp startTime =  currAppt.TimeSpan().StartTime();
			if(sender.getCurrentTime().compareTo(startTime) < 0)
				info += startTime.getHours() + ":" + startTime.getMinutes() + " - " + currAppt.getTitle() + "\n";
			
		}
		
		
		System.out.println(info);
		
		System.out.println();
	}

	@Override
	public void timeStopped(TimeMachine sender) {
		// TODO Auto-generated method stub
		
	}

	
}
