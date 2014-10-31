package hkust.cse.calendar.apptstorage;

import hkust.cse.calendar.unit.Appt;

import hkust.cse.calendar.unit.Location;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;
import java.sql.Timestamp;

import java.util.*;

public class ApptStorageMemImpl extends ApptStorage {

	private User defaultUser = null;
	
	public ApptStorageMemImpl( User user )
	{
		defaultUser = user;
		mAssignedApptID = 1;
		mAppts = new HashMap<Integer, Appt>();
		_locations = new Location[0];
	}
	
	public Location[] getLocationList() {
		return _locations;
	}
	
	public void setLocationList(Location[] locations) {
		_locations = locations.clone();
	}
	
	@Override
	public void SaveAppt(Appt appt) {
		// TODO Auto-generated method stub
		/*if(mAppts.containsKey(appt.getID())){
			mAppts.put(appt.getID(), appt);
		}*/
		//else{
			mAppts.put(mAssignedApptID, appt);
			appt.setID(mAssignedApptID);
			mAssignedApptID++;
		//}
	}

	@Override
	public Appt[] RetrieveAppts(TimeSpan d) {
		// TODO Auto-generated method stub
		List<Appt> tempList = new ArrayList<Appt>();
		int apptNum = 0;
		for(int num = 0;num < mAssignedApptID;num++){
			if(mAppts.containsKey(num)){
				Appt apptAtD = (Appt)mAppts.get(num);
				switch(apptAtD.getFrequency()){
				case Appt.SINGLE:
					if(apptAtD.TimeSpan().Overlap(d)){
						tempList.add(apptAtD);
						apptNum++;
					}
					break;
				case Appt.DAILY:
					apptAtD.setTimeSpan(new TimeSpan(
							new Timestamp(
									d.StartTime().getYear(),
									d.StartTime().getMonth(),
									d.StartTime().getDate(),
									apptAtD.TimeSpan().StartTime().getHours(),
									apptAtD.TimeSpan().StartTime().getMinutes(),
									apptAtD.TimeSpan().StartTime().getSeconds(),
									apptAtD.TimeSpan().StartTime().getNanos()
								),
							new Timestamp(
									d.EndTime().getYear(),
									d.EndTime().getMonth(),
									d.EndTime().getDate(),
									apptAtD.TimeSpan().EndTime().getHours(),
									apptAtD.TimeSpan().EndTime().getMinutes(),
									apptAtD.TimeSpan().EndTime().getSeconds(),
									apptAtD.TimeSpan().EndTime().getNanos()
								)
							)
						);
					
					if(apptAtD.TimeSpan().Overlap(d)){
						tempList.add(apptAtD);
						apptNum++;
					}
					
					break;
				case Appt.WEEKLY:
					if(apptAtD.TimeSpan().StartTime().getDay() == d.StartTime().getDay()){
						apptAtD.setTimeSpan(
								new TimeSpan(
										new Timestamp(
												d.StartTime().getYear(),
												d.StartTime().getMonth(),
												d.StartTime().getDate(),
												apptAtD.TimeSpan().StartTime().getHours(),
												apptAtD.TimeSpan().StartTime().getMinutes(),
												apptAtD.TimeSpan().StartTime().getSeconds(),
												apptAtD.TimeSpan().StartTime().getNanos()
												),
										new Timestamp(
												d.EndTime().getYear(),
												d.EndTime().getMonth(),
												d.EndTime().getDate(),
												apptAtD.TimeSpan().EndTime().getHours(),
												apptAtD.TimeSpan().EndTime().getMinutes(),
												apptAtD.TimeSpan().EndTime().getSeconds(),
												apptAtD.TimeSpan().EndTime().getNanos()
												)
										)
								);
						
						if(apptAtD.TimeSpan().Overlap(d)){
							tempList.add(apptAtD);
							apptNum++;
						}
					}
					break;
				case Appt.MONTHLY:
					if(apptAtD.TimeSpan().StartTime().getDate() == d.StartTime().getDate()){
						apptAtD.setTimeSpan(
								new TimeSpan(
										new Timestamp(
												d.StartTime().getYear(),
												d.StartTime().getMonth(),
												apptAtD.TimeSpan().StartTime().getDate(),
												apptAtD.TimeSpan().StartTime().getHours(),
												apptAtD.TimeSpan().StartTime().getMinutes(),
												apptAtD.TimeSpan().StartTime().getSeconds(),
												apptAtD.TimeSpan().StartTime().getNanos()
											),
										new Timestamp(
												d.EndTime().getYear(),
												d.EndTime().getMonth(),
												apptAtD.TimeSpan().EndTime().getDate(),
												apptAtD.TimeSpan().EndTime().getHours(),
												apptAtD.TimeSpan().EndTime().getMinutes(),
												apptAtD.TimeSpan().EndTime().getSeconds(),
												apptAtD.TimeSpan().EndTime().getNanos()
											)
									)
								);
						
						if(apptAtD.TimeSpan().Overlap(d)){
							tempList.add(apptAtD);
							apptNum++;
						}
					}
					break;
				}
			}
		}
			Appt[] timeAppt = new Appt[apptNum];
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
