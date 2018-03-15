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
		
	}

	
	public boolean checkUserID(int userID) {
		if(userID >= 11 && userID <= 99) {
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getUserID() == userID) {
					return true;
				}
			}
		}
		return false;
	}

	
	public String getUsername(int userID) {
		for(int i = 0; i < users.size(); i++) {
			if (userID == users.get(i).getUserID() ) {
				return users.get(i).getUsername();
			}
		}
		return null;
	}
	
	
	public String getBatchName(int batchID) {
		for(int i = 0; i < batches.size(); i++) {
			if (batchID == batches.get(i).getBatchID()) {
				return batches.get(i).getBatchName();
			}
		}
		return null;
	}
	
	
	public boolean checkBatchId(int batchID) {
		if(batchID >= 1000 && batchID <= 9999) {
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

	
	public void setBatchTara(int batchID, double batchTara) {
		for(int i = 0; i < batches.size(); i++) {
			if (batchID == batches.get(i).getBatchID()) {
				batches.get(i).setBatchTara(batchTara);
			}
		}
	}
	
	
	public double getBatchTara(int batchID) {
		for(int i = 0; i < batches.size(); i++) {
			if (batchID == batches.get(i).getBatchID()) {
				return batches.get(i).getBatchTara();
			}
		}
		return 0.0;	//Findes der en bedre løsning?
	
	}
	
	
	public void setBatchNetto(int batchID, double batchNetto) {
		for(int i = 0; i < batches.size(); i++) {
			if (batchID == batches.get(i).getBatchID()) {
				batches.get(i).setBatchNetto(batchNetto);
			}
		}
	}
	
	
	public void setBatchBrutto(int batchID, double negativeBatchBrutto) {
		for(int i = 0; i < batches.size(); i++) {
			if (batchID == batches.get(i).getBatchID()) {
				batches.get(i).setBatchBrutto(Math.abs(negativeBatchBrutto));
			}
		}
	}
	
}