package data;

public class Batch {
	int BatchID;
	String BatchName;
	double BatchTara, BatchNetto, BatchBrutto;
	
	public Batch(int batchID, String batchName) {
		super();
		BatchID = batchID;
		BatchName = batchName;
		BatchTara = 0.0;
		BatchNetto = 0.0;
		BatchBrutto = 0.0;
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
	
	public double getBatchTara() {
		return BatchTara;
	}
	
	public void setBatchTara(double batchTara) {
		BatchTara = batchTara;
	}
	
	public double getBatchNetto() {
		return BatchNetto;
	}
	
	public void setBatchNetto(double batchNetto) {
		BatchNetto = batchNetto;
	}
	
	public double getBatchBrutto() {
		return BatchBrutto;
	}
	
	public void setBatchBrutto(double batchBrutto) {
		BatchBrutto = batchBrutto;
	}
	
	
}
