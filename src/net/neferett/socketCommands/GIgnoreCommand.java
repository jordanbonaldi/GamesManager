package net.neferett.socketCommands;

import net.neferett.Main;
import net.neferett.games.GamesManager;

public class GIgnoreCommand extends SocketCommand{

	public GIgnoreCommand() {
		super("ignore");
	}
	
	@Override
	public void runCommand(String[] args) {
		String server = args[1];		
		
		GamesManager gm = Main.getInstance().getGames().get(server);
		
		if (gm == null)
			return;
	
		if (gm.isIgnored())
			gm.setIgnored(false);
		else
			gm.setIgnored(true);

	}

}
