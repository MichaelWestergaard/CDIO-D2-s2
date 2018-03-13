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
			String readLine = bufferedReader.readLine();
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
	
	
	public double getLoad() {
		//Tim
		
		
		
		return 8.0;
	}


	public void loginProcedure() {
		write("Enter your ID: ");
		String[] split = readLine.split(" ");
	}
	
	
	
	
	
	
	
}
