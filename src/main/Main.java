package main;

import controller.SocketController;

public class Main {
	
	public static void main(String[] args) {	
		SocketController socketcontroller = new SocketController();
		socketcontroller.init();
		new Thread(socketcontroller).start();
		//socketcontroller.write("Test");
		socketcontroller.getLoad();
	
		
		//socketcontroller.loginProcedure();
	}
}