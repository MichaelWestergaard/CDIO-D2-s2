package socket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SocketInMessage {
	private SocketMessageType type;
	private List<String> parameters = new ArrayList<>();

	public SocketInMessage(SocketMessageType type, String... message) {
		this.parameters.addAll(Arrays.asList(message));
		this.type=type;
	}
	
	public String getMessage() {
		return parameters.get(0);
	}
	
	public List<String> getParameters(){
		return parameters;
	}
	
	public SocketMessageType getType() {
		return type;
	}
	
	public enum SocketMessageType{
		RM204, RM208, D, DW, T, S, B, Q, P111, K
	}
}
