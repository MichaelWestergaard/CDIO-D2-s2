package controller;

import java.util.ArrayList;
import java.util.List;

import data.Batch;
import data.User;


public class DAO {

	private List<User> users = new ArrayList<User>();	
	private List<Batch> batches = new ArrayList<Batch>();
	
	public DAO () {		
		users.add(new User(12, "Anders And"));
		batches.add(new Batch(1234, "Salt"));
	};
	
	
	
	public boolean checkUserID(int userId) {	
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUserID()==userId) {
				return true;
			}
		}
		return false;
	}

	
	
}
