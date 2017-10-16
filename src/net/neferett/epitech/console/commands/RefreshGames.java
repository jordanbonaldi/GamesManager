package net.neferett.epitech.console.commands;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.epitech.Main;
import net.neferett.epitech.Games.games.Games;
import net.neferett.epitech.Games.games.GamesLoader;
import net.neferett.epitech.console.commandsManagers.Commands;

public class RefreshGames extends Commands {

	public RefreshGames() {
		super("reload", "Reload la config des Templates", "rl");
	}

	@Override
	public boolean onCommand(final String cmd, final String[] args) {
		if (args.length != 1)
			System.out.println("Usage: reload");
		try {
			final HashMap<String, Games> games = new GamesLoader().getGames();
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
