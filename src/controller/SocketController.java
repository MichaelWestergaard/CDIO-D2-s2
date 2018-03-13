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
	public static String readLine = "";
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
		
		while(readLine.equals("")) {
			
		}
		
		char[] readChar = readLine.toCharArray();
		double loadValue = Double.parseDouble(new StringBuilder().append(readChar[9]).append(readChar[10]).append(readChar[11]).toString());
		System.out.println(loadValue);
		
	}

	public void loginProcedure() {
		write("Enter your ID: \nAnd press \"OK\"");
		boolean userConfirmed = false;
		
		while(!userConfirmed) {
			String[] inputArr = readLine.split(" ");
			String input = inputArr[2].replace("\"", "");
			
			if(dao.checkUserID(Integer.parseInt(input))) {
				write("Confirm, that this is your name: " + dao.getUsername() + "\n1 = \"OK\", 0 = \"Cancel\"");				
				inputArr = readLine.split(" ");
				input = inputArr[2].replace("\"", "");
				
				if(Integer.parseInt(input) == 1) {
					userConfirmed = true;
				} else {
					write("Enter another ID: ");
				}
			} else {
				write("ID not found! \nTry again.");
			}
		}		
	}	
}
