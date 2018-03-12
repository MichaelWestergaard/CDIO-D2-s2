package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main implements Runnable {
	Socket socket;
	
	//Skal nok flyttes ind i en anden klasse, som står for kommunikationen mellem sim og socket
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.init();
		new Thread(main).start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		//substring og find ud af hvad outputtet er
		main.write();

	}

	private void write() {
		try {
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(outputStream);
			pw.println("RM20 8 \"test\" \"\" \"&3\" crlf");
			pw.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void init() {
		try {
			socket = new Socket("localhost", 8000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			
			InputStream inputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while(true) {
			String readLine = bufferedReader.readLine();
			System.out.println(readLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}