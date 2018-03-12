package controller;

import java.util.ArrayList;
import java.util.List;

import data.Batch;
import data.User;
import java.util.Scanner;


public class DAO {

	private List<User> users = new ArrayList<User>();	
	private List<Batch> batches = new ArrayList<Batch>();
	Scanner scan = new Scanner(System.in);
	
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


public boolean checkBatchId(int batchId) {
	for(int i = 0; i < batches.size(); i++) {
		if(batches.get(i).getBatchID() == batchId) {
			return true;
		}
	}
	return false;
}


public void insertBatchNo() {
	int batchId = 0;

	while(batchId == 0) {
		System.out.println("Enter Batch Number (1000-9999): ");
		int desiredID = scan.nextInt();

		if(checkBatchId(desiredID) && desiredID >= 1000 && desiredID <= 9999) { //Skal have oprettet "checkUsername" i DAO. Skal returnere true, hvis brugernavnet er gyldigt og ledigt.
			batchId = desiredID;
		} else {
			System.out.println("The Batch number, you entered, invalid.");
		}
	}

}

//public boolean checkUnloaded() {
//	if(vægt==0) {
//		return true;
//	} else {
//		return false;
//	}
//}
}

	
	

