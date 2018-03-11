package data;

public class Batch {
	int BatchID;
	String BatchName;
	
	public Batch(int batchID, String batchName) {
		super();
		BatchID = batchID;
		BatchName = batchName;
	}

	public int getBatchID() {
		return BatchID;
	}

	public void setBatchID(int batchID) {
		BatchID = batchID;
	}

	public String getBatchName() {
		return BatchName;
	}

	public void setBatchName(String batchName) {
		BatchName = batchName;
	}
	
	
}
