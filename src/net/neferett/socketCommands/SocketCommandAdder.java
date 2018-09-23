package net.neferett.socketCommands;

import java.util.ArrayList;
import java.util.List;

public class SocketCommandAdder {
	
	public List<SocketCommand> socketcommands;
	
	public SocketCommandAdder() {
		this.socketcommands = new ArrayList<>();
	}

	public List<SocketCommand> getSocketCommands() {
		return socketcommands;
	}
	
	public void addCommand(SocketCommand b) {
		this.socketcommands.add(b);
	}
	
}
