package hkust.cse.calendar.apptstorage;

import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;

import java.util.*;

public class ApptStorageMemImpl extends ApptStorage {

	private User defaultUser = null;
	
	public ApptStorageMemImpl( User user )
	{
		defaultUser = user;
		mAssignedApptID=0;
		mAppts = new HashMap<Integer, Appt>();
	}
	
	@Override
	public void SaveAppt(Appt appt) {
		// TODO Auto-generated method stub
		if(mAppts.containsKey(appt.getID())){
			mAppts.put(appt.getID(), appt);
		}
		else{
			mAppts.put(mAssignedApptID, appt);
			appt.setID(mAssignedApptID);
			mAssignedApptID++;
		}
	}

	@Override
	public Appt[] RetrieveAppts(TimeSpan d) {
		// TODO Auto-generated method stub
		List<Appt> tempList=new ArrayList<Appt>();
		int apptNum=0;
		for(int num=0;num<mAssignedApptID;num++){
			if(mAppts.containsKey(num)){
				Appt apptAtD= (Appt)mAppts.get(num);
				if(apptAtD.TimeSpan().Overlap(d)){
					tempList.add(apptAtD);
					apptNum++;
				}
			}
		}
			Appt[] timeAppt=new Appt[apptNum];
			tempList.toArray(timeAppt);
			return timeAppt;
	}

	@Override
	public Appt[] RetrieveAppts(User entity, TimeSpan time) {
		// TODO Auto-generated method stub
			return RetrieveAppts(time);
	}

	@Override
	public Appt RetrieveAppts(int joinApptID) {
		// TODO Auto-generated method stub
		if(mAppts.containsKey(joinApptID))
			return (Appt)mAppts.get(joinApptID);
		else
			return null;
	}

	@Override
	public void UpdateAppt(Appt appt) {
		// TODO Auto-generated method stub
		SaveAppt(appt);
	}

	@Override
	public void RemoveAppt(Appt appt) {
		// TODO Auto-generated method stub
		mAppts.remove(appt.getID());
	}

	@Override
	public User getDefaultUser() {
		// TODO Auto-generated method stub
		return defaultUser;
	}

	@Override
	public void LoadApptFromXml() {
		// TODO Auto-generated method stub

	}

}
