package net.neferett.console.commands;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.Main;
import net.neferett.console.commandsManagers.Commands;
import net.neferett.games.GamesLoader;
import net.neferett.games.GamesManager;

public class RefreshGames extends Commands {

	public RefreshGames() {
		super("reload", "Reload la config des Templates", "rl");
	}

	@Override
	public boolean onCommand(final String cmd, final String[] args) {
		if (args.length != 1)
			System.out.println("Usage: reload");
		try {
			final HashMap<String, GamesManager> games = new GamesLoader().getGames();
			games.forEach((gamename, game) -> {
				if (!Main.getInstance().getGames().containsKey(gamename))
					Main.getInstance().getGames().put(gamename, game);
			});
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
