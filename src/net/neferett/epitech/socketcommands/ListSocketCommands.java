package net.neferett.epitech.socketcommands;

import net.neferett.epitech.Main;

public class ListSocketCommands extends SocketCommand {
	
	public ListSocketCommands() {
		super("list");
	}
	
	@Override
	public void runCommand(String[] args) {
		StringBuilder ad = new StringBuilder();
				
		Main.getInstance().getGames().forEach((name, games) -> {
			ad.append(name + "@@");
		});
		
		this.replyToClient(ad.toString());
	}

}
