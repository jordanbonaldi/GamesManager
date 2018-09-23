package net.neferett.socketCommands;

import net.neferett.socket.api.FastReplyMessage;
import net.neferett.socket.packet.event.ReceiveMessageEvent;

public abstract class SocketCommand {

	private String name;
	
	protected ReceiveMessageEvent event;
	
	public void setEvent(ReceiveMessageEvent event) {
		this.event = event;
	}
	
	public SocketCommand(String name) {
		this.name = name;
	}
	
	public ReceiveMessageEvent getEvent() {
		return event;
	}
	
	protected void replyToClient(String msg)
	{
		new FastReplyMessage(this.event.getPacket().getS(), msg).build();
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void runCommand(String[] args);

}
