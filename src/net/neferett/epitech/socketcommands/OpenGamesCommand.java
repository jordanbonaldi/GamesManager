package net.neferett.epitech.socketcommands;

import net.neferett.epitech.Main;

public class OpenGamesCommand extends SocketCommand{

	public OpenGamesCommand() {
		super("open");
	}

	@Override
	public void runCommand(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		Main.getInstance().getGames().forEach((name, games)->{
			games.getServers().forEach((id, sv)->{
				sb.append(name + "-" + id + "@@");
			});
		});
		
		this.replyToClient(sb.toString());
	}
	
	

}
