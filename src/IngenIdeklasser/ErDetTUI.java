package IngenIdeklasser;

import java.util.Scanner;


public class ErDetTUI {
	Scanner scan = new Scanner(System.in);


	public ErDetTUI() {
		// TODO Auto-generated constructor stub
	}

	public void insertBatchNo() {
		int batchId = 0;

		while(batchId == 0) {
			System.out.println("Enter Batch Number (1000-9999): ");
			int desiredID = scan.nextInt();

			if(!batchNo.checkId(desiredID) && desiredID >= 11 && desiredID <= 99) { //Skal have oprettet "checkUsername" i DAO. Skal returnere true, hvis brugernavnet er gyldigt og ledigt.
				batchNo = desiredID;
			} else {
				System.out.println("The user ID, you entered, is either taken or invalid.");
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
