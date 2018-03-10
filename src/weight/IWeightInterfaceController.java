package weight;

public interface IWeightInterfaceController extends Runnable {
	
	void registerObserver(IWeightInterfaceObserver uiObserver);
	void unRegisterObserver(IWeightInterfaceObserver uiObserver);
	
	void showMessagePrimaryDisplay(String string);
	void showMessageSecondaryDisplay(String string);
	void showMessageTernaryDisplay(String string);
	void changeInputType(InputType type);
	void setSoftButtonTexts(String[] texts);
	void setLoad(double load);
	
	public enum InputType {
		UPPER, LOWER, NUMBERS
	}

	void lockUserInputType(boolean b);
}
