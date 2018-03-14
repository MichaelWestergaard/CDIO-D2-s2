package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Main;

public class SocketController implements Runnable {
	Socket socket;
	static String readLine = null;
	DAO dao;
	
	public SocketController() {
		super();
		dao = new DAO();
	}

	
	public void init() {
		try {
			socket = new Socket("localhost", 8000);
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
	
	
	public void write(String message) {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("RM20 8 \""+ message +"\" \"\" \"&3\" crlf");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void getLoad() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("S crlf");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(readLine == null) {
			
		}
		
		char[] readChar = readLine.toCharArray();
		//Push
		
	}
	
	public void getTara() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("T crlf");
			pw.flush();
			} catch(IOException e) {
				e.printStackTrace();
			}
		while(readLine == null) {
			
		}
		char[] readchar = readLine.toCharArray();
	}


	public void loginProcedure() {		
		try {
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String msg = "Enter your ID:";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\" crlf");
			pw.flush();	
			
			boolean userConfirmed = false;
			while(!userConfirmed) {
				String[] inputArr = reader.readLine().split(" ");
				int input = Integer.parseInt(inputArr[2].replace("\"", ""));
				
				if(dao.checkUserID(input)) {
					msg = "Confirm: " + dao.getUsername(input) + "? 1=Y, 0=N";
					pw.println("RM20 8 " + msg);
					pw.flush();
					
					inputArr = reader.readLine().split(" ");
					input = Integer.parseInt(inputArr[2].replace("\"", ""));
					
					if(input == 1) {
						userConfirmed = true;
						System.out.println("success");
					} else {
						msg = "Enter another ID: ";
						pw.println("RM20 8 " + msg);
						pw.flush();
					}
					
				} else {
					msg = "ID not found! Try again.";
					pw.println("RM20 8 " + msg);
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
			
			String msg = "Enter batch-number";
			pw.println("RM20 8 \"" + msg + "\" \"\" \"&3\" crlf");
			pw.flush();	
			
			boolean batchConfirmed = false;
			while(!batchConfirmed) {
				String[] inputArr = reader.readLine().split(" ");
				int input = Integer.parseInt(inputArr[2].replace("\"", ""));
				
				if(dao.checkBatchId(input)) {
					msg = "Confirm: " + dao.getBatchName(input) + "? 1=Y, 0=N";
					pw.println("RM20 8 " + msg);
					pw.flush();
					
					inputArr = reader.readLine().split(" ");
					input = Integer.parseInt(inputArr[2].replace("\"", ""));
					
					if(input == 1) {
						batchConfirmed = true;
						System.out.println("batch success");
					} else {
						msg = "Enter another batch-number: ";
						pw.println("RM20 8 " + msg);
						pw.flush();
					}
					
				} else {
					msg = "Batch not found! Try again.";
					pw.println("RM20 8 " + msg);
					pw.flush();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
