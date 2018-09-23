package net.neferett.socketCommands;

import net.neferett.Main;
import net.neferett.games.GamesManager;

public class GetIgnoredCommand extends SocketCommand{

	public GetIgnoredCommand() {
		super("getignore");
	}
	
	@Override
	public void runCommand(String[] args) {
		String server = args[1];		

		GamesManager gm = Main.getInstance().getGames().get(server);
		
		if (gm == null)
			return;
		
		this.replyToClient("" + gm.isIgnored());
	}

}
