package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class SocketController implements Runnable {
	Socket socket;
	static String readLine = null;
	DAO dao;
	int currentBatchID;

	public SocketController() {
		super();
		dao = new DAO();
	}

	public void init() {
		try {
			socket = new Socket("169.254.2.2", 8000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while(true) {
				readLine = bufferedReader.readLine();
				System.out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//To to self: Fix antal decimaler.
	public double getLoad() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("S");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sleep();		
//		char[] readChar = readLine.toCharArray();
//		double loadValue = Double.parseDouble(new StringBuilder().append(readChar[8]).append(readChar[9]).append(readChar[10]).append(readChar[11]).append(readChar[12]).toString());
		String[] loadArr = readLine.split(" ");
		double loadValue = Double.parseDouble(loadArr[2]);
		return loadValue;
	}

	//To to self: Fix antal decimaler.
	public double getLoadFromString(String loadString) {
//		char[] loadChar = loadString.toCharArray();
//		double loadValue = Double.parseDouble(new StringBuilder().append(loadChar[9]).append(loadChar[10]).append(loadChar[11]).append(loadChar[12]).toString());
		String[] loadArr = loadString.split(" ");
		double loadValue = Double.parseDouble(loadArr[2]);
		return loadValue;
	}

	public void sleep() {
		try {
			TimeUnit.SECONDS.sleep(2);		//Lav til 1500 millisekunder?
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getTara() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("T");
			pw.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
		while(readLine == null) {}
		char[] readchar = readLine.toCharArray();
	}

	public void loginProcedure() {		
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Enter your ID:";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean userConfirmed = false;
			while(!userConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();
				int input = Integer.parseInt(inputArr[2].replace("\"", ""));

				if(dao.checkUserID(input)) {
					msg = dao.getUsername(input) + "?";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();

					inputString = reader.readLine();
					inputArr = inputString.split(" ");

					if(inputArr[1].equals("A")) {
						userConfirmed = true;
						System.out.println("success");
					} else {
						msg = "Enter another ID: ";
						pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
						pw.flush();
					}

				} else {
					msg = "ID not found! Try again.";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void batchProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Enter batch-ID";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean batchConfirmed = false;
			while(!batchConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();
				int input = Integer.parseInt(inputArr[2].replace("\"", ""));

				if(dao.checkBatchId(input)) {					
					msg = "Confirm: " + dao.getBatchName(input) + "?"; 
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();

					currentBatchID = input;

					inputString = reader.readLine();
					inputArr = inputString.split(" ");

					if(inputArr[1].equals("A")) {
						batchConfirmed = true;
						System.out.println("batch success");
					} else {
						msg = "Try another batch-ID:";
						pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
						pw.flush();
					}

				} else {
					msg = "Not found! Try again.";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void unloadProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Is the weight unloaded?";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean unloadedConfirmed = false;
			while(!unloadedConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();

				if(inputArr[1].equals("A")) {
					unloadedConfirmed = true;
					pw.println("T");
					pw.flush();
					sleep();
					System.out.println("unload success");
				} else {
					msg = "Unload the weight and confirm.";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void taraProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Place tara.";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean taraConfirmed = false;
			while(!taraConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();

				if(inputArr[1].equals("A")) {
					taraConfirmed = true;
					pw.println("T");
					pw.flush();
					sleep();
					double taraWeight = getLoadFromString(readLine);
					dao.setBatchTara(currentBatchID, taraWeight);
					System.out.println("tara success");
				} else {
					msg = "Try again and confirm.";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void nettoProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Place netto.";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean nettoConfirmed = false;
			while(!nettoConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();


				if(inputArr[1].equals("A")) {
					double nettoWeight = getLoad();
					dao.setBatchNetto(currentBatchID, nettoWeight);
					nettoConfirmed = true;
					pw.println("T");
					pw.flush();
					sleep();
					System.out.println("tara success");
				} else {
					msg = "Try again!";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void bruttoProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Remove brutto.";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean bruttoConfirmed = false;
			while(!bruttoConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();

				if(inputArr[1].equals("A")) {
					double negativeBruttoWeight = getLoad();
					dao.setBatchBrutto(currentBatchID, negativeBruttoWeight);
					bruttoConfirmed = true;
				} else {
					msg = "Try again and confirm.";
					pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
					pw.flush();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void endBatchProcedure() {
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String msg = "Confirm Batch";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
			pw.flush();	

			boolean endConfirmed = false;
			while(!endConfirmed) {
				String inputString = reader.readLine();
				String[] inputArr = inputString.split(" ");
				sleep();

				if(inputArr[1].equals("A")) {
					pw.println("T");
					pw.flush();
					sleep();
					endConfirmed = true;
				} else {
					batchProcedure();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void completeProcedure() {
		loginProcedure();
		batchProcedure();
		unloadProcedure();
		taraProcedure();
		nettoProcedure();
		bruttoProcedure();
		endBatchProcedure();
	}

}
