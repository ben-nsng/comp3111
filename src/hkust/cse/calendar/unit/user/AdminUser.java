package hkust.cse.calendar.unit.user;


public class AdminUser extends User {

	public AdminUser(String id, String pass) {
		super(id, pass);
		// TODO Auto-generated constructor stub
		this.isAdmin = true;
	}

}
