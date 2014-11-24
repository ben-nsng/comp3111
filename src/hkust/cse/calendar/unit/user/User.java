package hkust.cse.calendar.unit.user;

import java.io.Serializable;

public class User implements Serializable {

	private String mPassword;				// User password
	private String mID;						// User id
	private String mEmail;
	protected Boolean isAdmin;
	
	// Getter of the user id
	public String ID() {		
		return mID;
	}

	// Constructor of class 'User' which set up the user id and password
	public User(String id, String pass) {
		mID = id;
		mPassword = pass;
		isAdmin = false;
	}

	// Another getter of the user id
	public String toString() {
		return ID();
	}

	// Getter of the user password
	public String Password() {
		return mPassword;
	}

	// Setter of the user password
	public void Password(String pass) {
		mPassword = pass;
	}
	
	// Getter of the email
	public String Email() {
		return mEmail;
	}
	
	// Setter of user email
	public void Email(String email) {
		mEmail = email;
	}
	
	public Boolean IsAdmin() {
		return this.isAdmin;
	}
}
