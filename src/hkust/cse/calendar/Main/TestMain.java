package hkust.cse.calendar.Main;

import java.sql.Timestamp;

import hkust.cse.calendar.apptstorage.ApptStorageControllerImpl;
import hkust.cse.calendar.apptstorage.ApptStorageMemImpl;
import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;

public class TestMain {

	public static void main(String[] args) {
		
		TestMain.TestStorage();
		
	}
	
	public static void TestStorage() {
		User user = new User( "noname", "nopass");
		ApptStorageControllerImpl ctrl = new ApptStorageControllerImpl(new ApptStorageMemImpl(user));
		
		TimeSpan time0 = new TimeSpan(new Timestamp(2014, 12, 24, 10, 30, 0, 0), new Timestamp(2014, 12, 24, 11, 30, 0, 0));
		TimeSpan time1 = new TimeSpan(new Timestamp(2014, 12, 24, 10, 0, 0, 0), new Timestamp(2014, 12, 24, 11, 0, 0, 0));
		TimeSpan time2 = new TimeSpan(new Timestamp(2014, 12, 24, 11, 0, 0, 0), new Timestamp(2014, 12, 24, 12, 0, 0, 0));
		
		//create appt 1
		Appt appt = new Appt();
		appt.setTitle("test");
		appt.setTimeSpan(time1);
		ctrl.ManageAppt(appt, ApptStorageControllerImpl.NEW);

		//create appt 2
		appt = new Appt();
		appt.setTimeSpan(time2);
		ctrl.ManageAppt(appt, ApptStorageControllerImpl.NEW);
		
		//retrieve appts
		Appt[] appts = ctrl.RetrieveAppts(user, time0);
		
		//assert appts.length == 2;
		//assert appts.length != 1;
		
		
	}
	
	public static void TestTimeMachine() {
	}

	
}
