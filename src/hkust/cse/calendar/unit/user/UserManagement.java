package hkust.cse.calendar.unit.user;

import java.io.Serializable;
import java.util.ArrayList;

public class UserManagement implements Serializable {

	public static UserManagement getInstance() {
		if(um == null) {
			um = new UserManagement();
			
			//init and hard code some users
			um.AddUser(new AdminUser("admin", "admin"));
			um.AddUser(new AdminUser("admin2", "admin2"));
			
			um.AddUser(new RegularUser("user", "user"));
			um.AddUser(new RegularUser("user2", "user2"));
			um.AddUser(new RegularUser("user3", "user3"));
			um.AddUser(new RegularUser("user4", "user4"));
			um.AddUser(new RegularUser("user5", "user5"));
			um.AddUser(new RegularUser("user6", "user6"));
			um.AddUser(new RegularUser("user7", "user7"));
		}
		return um;
	}
	
	private static UserManagement um = null;
	private ArrayList<User> users;
	private String lastError;
	private User lastAuthUser;
	
	public UserManagement() {
		this.users = new ArrayList<User>();
		this.lastError = "";
	}
	
	private Boolean AddUser(User user) {
		if(!DuplicateID(user.ID())) {
			users.add(user);
			return true;
		}
		return false;
	}
	
	public Boolean Signup(String name, String pass) {
		if(name.equals("") || pass.equals("")) {
			this.setLastError("Name or password must be filled in.");
			return false;
		}
		
		return AddUser(new RegularUser(name, pass));
	}
	
	public Boolean Auth(String name, String pass) {
		for(User user : this.users) {
			if(user.ID().equals(name)) {
				if(user.Password().equals(pass)) {
					this.setLastAuthUser(user);
					return true;
				}
				//the password is not correct
				this.setLastError("The authentication is not correct.");
				return false;
			}
		}
		//no user in the lists
		this.setLastError("The authentication is not correct.");
		return false;
	}
	
	public Boolean DuplicateID(String name) {
		for(User user : this.users) {
			if(user.ID().equals(name)) {
				this.setLastError("Duplicate User Name.");
				return true;
			}
		}
		
		return false;
	}
	
	private void setLastError(String err) {
		this.lastError = err;
	}
	
	private void setLastAuthUser(User user) {
		this.lastAuthUser = user;
	}
	
	public String getLastError() {
		return this.lastError;
	}
	
	public User getLastAuthUser() {
		return this.lastAuthUser;
	}
	
	public Boolean removeUser(User tobeRemoved, User submitter) {
		if(submitter.IsAdmin()) {
			this.users.remove(tobeRemoved);
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getAllUserIDs() {
		ArrayList<String> lists = new ArrayList<String>();
		
		for(User user : this.users) {
			if(!user.IsAdmin())
				lists.add(user.ID());
		}
		
		return lists;
	}
	
	public User getUser(String ID) {
		for(User user : this.users) {
			if(user.ID().equals(ID)) return user;
		}
		return null;
	}
	
}
