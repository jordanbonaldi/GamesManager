package net.neferett.events;

import net.neferett.Main;
import net.neferett.socket.events.manager.EventListener;
import net.neferett.socket.events.manager.SocketEvent;
import net.neferett.socket.packet.event.ReceiveMessageEvent;

public class MessagesEvents implements EventListener{
	
	@SocketEvent
	public void onReceiveMessageEvent(final ReceiveMessageEvent e) {
		String message = e.getPacket().getMessage();
		
		if (message == null)
			return;
		
		final String[] msg = message.split(" ");
		
		Main.getInstance().getAdder().getSocketCommands().stream().forEach(name -> {
			if (msg[0].equalsIgnoreCase(name.getName())) {
				name.setEvent(e);
				name.runCommand(msg);
			}
		});
	}

}
