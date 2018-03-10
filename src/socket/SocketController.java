package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import socket.SocketInMessage.SocketMessageType;

public class SocketController implements ISocketController {
	Set<ISocketObserver> observers = new HashSet<ISocketObserver>();
	private BufferedReader inStream;
	private PrintWriter outStream;
	//TODO Maybe add some way to keep track of multiple connections?


	@Override
	public void registerObserver(ISocketObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unRegisterObserver(ISocketObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(SocketInMessage message) {
		for (ISocketObserver socketObserver : observers) {
			socketObserver.notify(message);
		}
	}

	@Override
	public void sendMessage(SocketOutMessage message) {
		if (outStream!=null){
			outStream.println(message.getMessage());
			outStream.flush();
		} else {
			//TODO maybe tell someone that connection is closed?
		}
	}

	@Override
	public void run() {
		//TODO some logic for listening to a socket //(Using try with resources for auto-close of socket)
		try (ServerSocket listeningSocket = new ServerSocket(PORT)){ 
			while (true){
				waitForConnections(listeningSocket); 	
			}		
		} catch (IOException e1) {
			// TODO Maybe notify MainController?
			e1.printStackTrace();
		} 


	}

	private void waitForConnections(ServerSocket listeningSocket) {
		try {
			Socket activeSocket = listeningSocket.accept(); //Blocking call
			inStream = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));
			outStream = new PrintWriter(activeSocket.getOutputStream()); 
			String inLine = null;
			//.readLine is a blocking call 
			//TODO How do you handle simultaneous input and output on socket?
			//TODO this only allows for one open connection - how would you handle multiple connections?
			while (true){
				inLine = inStream.readLine();
				System.out.println(inLine);
				if (inLine==null) break;
				String[] inLineArray = inLine.split(" ");
				switch (inLineArray[0]) {
				case "D":
					if (inLineArray.length<2) { 
						sendError();
					} else {
						notifyObservers(new SocketInMessage(SocketMessageType.D, sanitizeInput(inLine, "D ")));
					}
					break;
				case "DW":
					notifyObservers(new SocketInMessage(SocketMessageType.DW, ""));
					break;
				case "P111":
					if (inLineArray.length<2){
						sendError();
					} else {
						notifyObservers(new SocketInMessage(SocketMessageType.P111, sanitizeInput(inLine, "P111 ")));
					}
					break;
				case "T":
					notifyObservers(new SocketInMessage(SocketMessageType.T, ""));
					break;
				case "S":
					notifyObservers(new SocketInMessage(SocketMessageType.S, ""));
					break;
				case "B":
					if (inLineArray.length<2){
						sendError();
					} else {
						notifyObservers(new SocketInMessage(SocketMessageType.B, inLine.split(" " )[1]));
					}
					break;
				case "Q":
					notifyObservers(new SocketInMessage(SocketMessageType.Q,""));
					break;
				case "RM20":
					if (inLineArray.length<3) {
						sendError();
					} else if ("4".equals(inLine.split(" ")[1])){
						notifyObservers(new SocketInMessage(SocketMessageType.RM204, sanitizeInput(inLine, "RM20 4 ")));
					} else if ("8".equals(inLine.split(" ")[1])){
						notifyObservers(new SocketInMessage(SocketMessageType.RM208, sanitizeInput(inLine, "RM20 8 ")));
					} 
					break;
				case "K":
					if (inLine.split(" ").length>1){
						notifyObservers(new SocketInMessage(SocketMessageType.K, inLine.split(" ")[1]));
					}
					break;
				default:
					sendMessage(new SocketOutMessage("Unknown Command"));
					break;
				}
			}
		} catch (IOException e) {
			//TODO maybe notify mainController?
			e.printStackTrace();
		} 
	}


	private String[] sanitizeInput(String inLine, String prefix){
		String t = inLine.replace(prefix, "");
		ArrayList<String> l = new ArrayList<>();
		String word = "";
		boolean inQuotation = false;
		for(char c : t.toCharArray()){
			if(inQuotation){
				if(c == '\"'){
					inQuotation = false;
					word = word.trim();
//					if(!word.isEmpty()){
						l.add(word);
						word = "";
//					}
				} else {
					word += c;
				}
			} else {
				if(c == '\"') {
					inQuotation = true;
				} else if(c == ' '){
					word = word.trim();
					if(!word.isEmpty()){
						l.add(word);
						word = "";
					}
				} else {
					word += c;
				}
			}
		}
		if(!word.isEmpty()){
			l.add(word);
			word = "";
		}
		return l.toArray(new String[l.size()]);
	}
	private void sendError() {
		sendMessage(new SocketOutMessage("Error in command"));
	}
	
	public static void main(String[] args) {
		SocketController s = new SocketController();
//		String[] t = s.sanitizeInput("RM20 8 msg ph unit", "RM20 8");
//		String[] t = s.sanitizeInput("RM20 8 \"msg msg\" \"ph\" \"unit\"", "RM20 8");
//		String[] t = s.sanitizeInput("RM20 8 \"msg msg\" ph unit", "RM20 8");
		String[] t = s.sanitizeInput("RM20 8 \"msg msg\" ph \"\"", "RM20 8");
		System.out.println("------------------------------");
		for(String r : t) System.out.println(r);
		System.out.println("------------------------------");
	}
}

