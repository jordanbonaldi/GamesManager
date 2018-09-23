package net.neferett.epitech.socketcommands;

import net.neferett.epitech.Main;
import net.neferett.epitech.Games.GamesManager;

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
