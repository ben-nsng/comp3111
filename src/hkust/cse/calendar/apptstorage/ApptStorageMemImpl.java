package hkust.cse.calendar.apptstorage;

import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.Location;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;

import java.util.*;

public class ApptStorageMemImpl extends ApptStorage {

	private User defaultUser = null;
	
	public ApptStorageMemImpl( User user )
	{
		defaultUser = user;
		mAssignedApptID = 0;
		mAppts = new HashMap<Integer, Appt>();
		_locations = new Location[0];
	}
	
	public Location[] getLocationList(){
		return _locations;
	}
	
	public void setLocationList(Location[] locations) {
		Location[] _locationsTemp = new Location[_locations.length];
		for(int i=0;i<_locations.length;i++)
			_locationsTemp[i]=_locations[i];
		int newLength = _locationsTemp.length+locations.length;
		_locations = new Location[newLength];
		for(int j=0;j<_locationsTemp.length;j++)
			_locations[j]=_locationsTemp[j];
		for(int k=_locationsTemp.length;k<newLength;k++)
			_locations[k]=locations[k];
	}
	
	@Override
	public void SaveAppt(Appt appt) {
		// TODO Auto-generated method stub
			mAppts.put(mAssignedApptID, appt);
			appt.setID(mAssignedApptID);
			mAssignedApptID++;
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
			mAppts.put(appt.getID(), appt);
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
