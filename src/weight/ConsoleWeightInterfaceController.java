package weight;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import weight.KeyPress.KeyPressType;

public class ConsoleWeightInterfaceController implements IWeightInterfaceController{
	Set<IWeightInterfaceObserver> observers = new HashSet<IWeightInterfaceObserver>();
	List<String> softKeyTexts;
	private String primaryDisp;
	private String secondaryDisp;
	private String ternDisp;
	private String inputType = KeyPressType.TEXT.toString();
	private double brutto;

	@Override
	public void run() {
		// TODO Some logic to catch some input and notify the observers
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		showMenu();
		while (true){
			showMenu();
			String input = scan.nextLine();
			String[] inputSplit = input.split(" ");
			if (inputSplit[0].equals("S")){
				for (IWeightInterfaceObserver observer : observers) {
					observer.notifyKeyPress(KeyPress.SoftButton(Integer.parseInt(inputSplit[1])));
				}
			} else if (inputSplit[0].equals("B")){
				for (IWeightInterfaceObserver observer : observers) {
					brutto = Double.parseDouble(inputSplit[1]);
					observer.notifyWeightChange(brutto);
				}
			} else if (inputSplit[0].equals("TARA")){
				for (IWeightInterfaceObserver observer: observers){
					observer.notifyKeyPress(KeyPress.Tara());
				}
			} else {
				char[] chars = input.toCharArray();
				for (IWeightInterfaceObserver observer : observers){
					for(int i=0;i<chars.length; i++){
						observer.notifyKeyPress(KeyPress.Character(chars[i]));
					}
				}
			}
		}
	}

	private void showMenu() {
		clearDisp();
		System.out.println("PrimaryDisp: " + nilString(primaryDisp));
		System.out.println("Secondary: " + nilString(secondaryDisp));
		System.out.println("Ternary: " + nilString(ternDisp));
		System.out.println("InputType: " + inputType);
		System.out.println("----");
		System.out.println("Brutto Belastning:" + brutto );
		System.out.println("tast B x.xxx for v�gt�ndring");
		System.out.println("tast Tast S X for softkey nummer X");
		System.out.println("tast TARA for tara");
		System.out.println("SoftKeys:");
		if (softKeyTexts!=null){
			for (int i=0; i<softKeyTexts.size();i++){
				System.out.print(i + ": " + softKeyTexts.get(i) + "  ");
			}
		}
		System.out.println("indtast");
	}

	private String nilString(Object object) {
		return object==null?"" :object.toString();
	}

	private void clearDisp() {
		for (int i=0;i<20;i++){
			System.out.println("");
		}
	}

	@Override
	public void registerObserver(IWeightInterfaceObserver uiObserver) {
		observers.add(uiObserver);
	}

	@Override
	public void unRegisterObserver(IWeightInterfaceObserver uiObserver) {
		observers.remove(uiObserver);	
	}


	@Override
	public void showMessagePrimaryDisplay(String string) {
		primaryDisp=string;
		showMenu();
	}

	@Override
	public void showMessageSecondaryDisplay(String string) {
		secondaryDisp = string;
		showMenu();

	}

	@Override
	public void showMessageTernaryDisplay(String string) {
		ternDisp = string;
		showMenu();
	}
	
	@Override
	public void changeInputType(InputType type) {
		this.inputType= type.toString();

	}

	@Override
	public void setSoftButtonTexts(String[] texts) {
		setSoftButtonTexts(texts);
		showMenu();
	}

	@Override
	public void lockUserInputType(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoad(double loadinkg) {
		// Ignored
	}
}
