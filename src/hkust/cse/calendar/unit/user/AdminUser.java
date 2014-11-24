package hkust.cse.calendar.unit.user;

import java.io.Serializable;


public class AdminUser extends User implements Serializable {

	public AdminUser(String id, String pass) {
		super(id, pass);
		// TODO Auto-generated constructor stub
		this.isAdmin = true;
	}

}
