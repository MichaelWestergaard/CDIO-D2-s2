package data;

public class User {

	int userID;
	String username;
	public User(int userID, String username) {
		super();
		this.userID = userID;
		this.username = username;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
