package net.neferett.socketCommands;

import lombok.SneakyThrows;
import net.neferett.Main;
import net.neferett.games.GamesManager;

public class StopCommand extends SocketCommand {

	private int id;
	
	private GamesManager game;
	
	public StopCommand() {
		super("stop");
	}

	@SneakyThrows
	public void getGames(String[] args) {
		final String[] path;
		final String games;
		int id = 0;
		
		if (args[1].contains("/")) {
			path = args[1].split("/");
			games = path[path.length - 2];
			try {
				id = Integer.parseInt(path[path.length - 1]);
			} catch (Exception ignored) { }
		} else {
			games = args[1];
			id = Integer.parseInt(args[2]);
		}
		
		if (!Main.getInstance().getGames().containsKey(games)) {
			this.game = null;
		}
		
		this.game = Main.getInstance().getGames().get(games);
		this.id = id;
	}
	
	@Override
	public void runCommand(String[] args) {
		this.getGames(args);
		
		if (this.game == null) {
			return;
		}
		try {
			this.event.getUtils().ConsoleMessage("Unregistering " + this.game.getName() + " id: " + this.id);
			Thread.sleep(5 * 1000);
			this.event.getUtils().ConsoleMessage("Removing server's folder");
		} catch (final InterruptedException error) {
			error.printStackTrace();
		}

		this.game.RemoveServer(this.id);
		
		if (this.game.isRestart())
			this.game.CreateServer();
	}
	
}
