package IngenIdeklasser;

import java.util.Scanner;


public class ErDetTUI {
	Scanner scan = new Scanner(System.in);


	public ErDetTUI() {
		// TODO Auto-generated constructor stub
	}

	public boolean checkId(int batchId) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUserId() == userId) {
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

			if(!batchId.checkId(desiredID) && desiredID >= 1000 && desiredID <= 9999) { //Skal have oprettet "checkUsername" i DAO. Skal returnere true, hvis brugernavnet er gyldigt og ledigt.
				batchId = desiredID;
			} else {
				System.out.println("The Batch number, you entered, is either taken or invalid.");
			}
		}

	}
	
	



	public boolean checkUnloaded() {
		if(vægt==0) {
			return true;
		} else {
			return false;
		}
	}
}
