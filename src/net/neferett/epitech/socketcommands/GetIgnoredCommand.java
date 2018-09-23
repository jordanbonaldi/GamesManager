package net.neferett.epitech.socketcommands;

import net.neferett.epitech.Main;
import net.neferett.epitech.Games.GamesManager;

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
