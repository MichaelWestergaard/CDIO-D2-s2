package controller;

import java.util.ArrayList;
import java.util.List;

import data.Batch;
import data.User;
import java.util.Scanner;


public class DAO {

	private double load;
	private double Tara;
	private List<User> users = new ArrayList<User>();	
	private List<Batch> batches = new ArrayList<Batch>();
	Scanner scan = new Scanner(System.in);

	public DAO () {		
		users.add(new User(12, "Anders And"));
		batches.add(new Batch(1234, "Salt"));
		
	};

	public boolean checkUserID(int userID) {
		if(userID >= 1000 && userID <= 9999) {
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUserID() == userID) {
					return true;
				}
			}
		}
		return false;
	}

	public String getUsername(int UserID) {
		for(int i = 0; i < users.size(); i++) {
			if (UserID == users.get(i).getUserID() ) {
				return users.get(i).getUsername();
			}
		}
		return null;
	}
	
	public boolean checkBatchId(int batchID) {
		if(batchID >= 11 && batchID <= 99) {
			for(int i = 0; i < batches.size(); i++) {
				if(batches.get(i).getBatchID() == batchID) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkUnloaded(double weight) {
		if(weight == 0.0) {
			return true;
		} else {
			return false;
		}
	}

}