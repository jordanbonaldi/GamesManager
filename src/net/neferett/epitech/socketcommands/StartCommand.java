package net.neferett.epitech.socketcommands;

import net.neferett.epitech.Main;

public class StartCommand extends SocketCommand{

	public StartCommand() {
		super("start");
	}
	
	@Override
	public void runCommand(String[] args) {
		String server = args[1];
		if (Main.getInstance().getGames().containsKey(server))
			Main.getInstance().getGames().get(server).CreateServer();
	}

}
