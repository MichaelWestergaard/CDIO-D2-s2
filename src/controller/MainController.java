package controller;

import java.util.Locale;

import socket.ISocketController;
import socket.ISocketObserver;
import socket.SocketInMessage;
import socket.SocketOutMessage;
import weight.IWeightInterfaceController;
import weight.IWeightInterfaceController.InputType;
import weight.IWeightInterfaceObserver;
import weight.KeyPress;
/**
 * MainController - integrating input from socket and ui. Implements ISocketObserver and IUIObserver to handle this.
 * @author Christian Budtz
 * @version 0.1 2017-01-24
 *
 */
public class MainController implements IMainController, ISocketObserver, IWeightInterfaceObserver {
	public enum KeyState { 
		K1, //        Execute function, don't send
		K2, // Do not execute function, don't send
		K3, // Do not execute function, but send keycode
		K4  //        Execute function, and send keycode
		// Send "K A 1" when Tara
		// Send "K A 2" when Zero
		// Send "K A 3" when Send (print)
	}
	public enum WeightState { READY, RM20 }

	private ISocketController socketHandler;
	private IWeightInterfaceController weightController;
	private KeyState keyState = KeyState.K4;
	private WeightState weightState = WeightState.READY;
	private String weightInput = "";
	private String unit = ""; // used with RM20
	double load; // load in g
	double tare; // tare in g
	
	public MainController(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) {
		this.init(socketHandler, weightInterfaceController);
	}

	@Override
	public void init(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) {
		this.socketHandler = socketHandler;
		this.weightController=weightInterfaceController;
	}

	@Override
	public void start() {
		if (socketHandler!=null && weightController!=null){
			//Makes this controller interested in messages from the socket
			socketHandler.registerObserver(this);
			//Starts socketHandler in own thread
			new Thread(socketHandler).start();
			//Sign up for consoleInput
			weightController.registerObserver(this);
			//Start uiController in own thread
			new Thread(weightController).start();
		} else {
			System.err.println("No controllers injected!");
		}
	}

	//Listening for socket input
	@Override
	public void notify(SocketInMessage message) {
		switch(weightState){
		case READY:
			handleMessageReady(message);
			break;
		case RM20:
			socketHandler.sendMessage(new SocketOutMessage("RM20 waiting for input"));
			break;		
		}	
		
	}
	private void handleMessageReady(SocketInMessage message) {
		switch(message.getType()){
		case B:
			load = Double.parseDouble(message.getMessage());
			System.out.println("Load = "+load+"\nTare = "+tare);
			weightController.setLoad(load);
			break;
		case Q:
			System.exit(1);
			break;
		case D:
			weightController.showMessagePrimaryDisplay(message.getMessage());
			socketHandler.sendMessage(new SocketOutMessage("D A"));
			break;
		case DW:
			showWeight();
			socketHandler.sendMessage(new SocketOutMessage("DW A"));
			break;
		case S:
			System.out.println("sending S S message");
			socketHandler.sendMessage(new SocketOutMessage("S S      " + String.format(Locale.US, "%.3f", (load-tare)/1000) + "kg"));
			break;
		case T:
			tare = load;
			socketHandler.sendMessage(new SocketOutMessage("T S      " + String.format(Locale.US, "%.3f", tare/1000) + "kg"));
			showWeight();
			break;
		case P111:
			weightController.showMessageTernaryDisplay(message.getMessage());
			socketHandler.sendMessage(new SocketOutMessage("P111 A"));
			break;
		case RM204:
			weightController.changeInputType(InputType.NUMBERS);
			weightController.lockUserInputType(true);
			// Fall through to RM208
		case RM208:
			weightController.showMessageTernaryDisplay(message.getMessage());
			String placeholder = message.getParameters().get(1);
			unit = message.getParameters().get(2);
			unit = handleUnit(unit);
			weightController.showMessageSecondaryDisplay(placeholder+" "+unit);
			weightState=WeightState.RM20;
			weightController.setSoftButtonTexts(new String[]{"", "Erase", "<--", "-->", "OK", "Cancel"});;
			socketHandler.sendMessage(new SocketOutMessage("RM20 B"));
			break;
		case K:
			handleKMessage(message);
			break;
		}
	}
	private void handleKMessage(SocketInMessage message) {
		switch (message.getMessage()) {
		case "1" :
			this.keyState = KeyState.K1;
			break;
		case "2" :
			this.keyState = KeyState.K2;
			break;
		case "3" :
			this.keyState = KeyState.K3;
			break;
		case "4" :
			this.keyState = KeyState.K4;
			break;
		default:
			socketHandler.sendMessage(new SocketOutMessage("ES"));
			break;
		}
	}
	private String handleUnit(String unit){
		if(unit.startsWith("&1")){ weightController.changeInputType(InputType.UPPER); return unit.substring(2); }
		if(unit.startsWith("&2")){ weightController.changeInputType(InputType.LOWER); return unit.substring(2); }
		if(unit.startsWith("&3")){ weightController.changeInputType(InputType.NUMBERS); return unit.substring(2); }
		return unit;
	}
	
	//Listening for UI input
	@Override
	public void notifyKeyPress(KeyPress keyPress) {
		switch (keyPress.getType()) {
		case SOFTBUTTON:
			System.out.println("Softbutton " + keyPress.getKeyNumber() + " pressed");
			if (weightState==WeightState.RM20 && keyPress.getKeyNumber() == 4){
				weightState= WeightState.READY;
				weightController.lockUserInputType(false);
				socketHandler.sendMessage(new SocketOutMessage("RM20 A \""+ weightInput + "\""));
				weightInput="";
				weightController.showMessageSecondaryDisplay(weightInput);
				weightController.setSoftButtonTexts(new String[]{"","","","","",""});
				showWeight();
			}
			break;
		case TARA:
			System.out.println("TARA pressed");
			tare = load;
			showWeight();
			break;
		case TEXT:
			System.out.println("Character " + keyPress.getCharacter() + " pressed");
			weightInput += keyPress.getCharacter();
			weightController.showMessageSecondaryDisplay(weightInput + " " + unit);
			break;
		case ZERO:
			System.out.println("ZERO pressed");
			if (weightState==WeightState.RM20){
				weightState= WeightState.READY;
				weightController.lockUserInputType(false);
				socketHandler.sendMessage(new SocketOutMessage("RM20 A "+ weightInput));
				weightInput="";
				weightController.showMessageSecondaryDisplay(weightInput);
			}
			load = 0;
			tare = 0;
			weightController.setLoad(load);
			showWeight();
			break;
		case CANCEL:
			System.out.println("C pressed");
			break;
		case EXIT:
			System.exit(1);
			break;
		case SEND:
			System.out.println(keyState);
			if (keyState.equals(KeyState.K4) || keyState.equals(KeyState.K3) ){
				socketHandler.sendMessage(new SocketOutMessage("K A 3 "+String.format(Locale.US, "%.3f", tare/1000) + "kg"));
			}
			break;
		}
	}

	@Override
	public void notifyWeightChange(double newWeight) {
		load = newWeight;
		showWeight();
	}
	private void showWeight() {
		String str = String.format(Locale.US, "%.3f", (load-tare)/1000)+"kg";
		weightController.showMessagePrimaryDisplay(str);
	}
}
