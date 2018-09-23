package net.neferett.epitech.console.commands;

import net.neferett.epitech.Main;
import net.neferett.epitech.console.commandsManagers.Commands;
import net.neferett.epitech.executor.Execute;

public class exitCommand extends Commands {

	public exitCommand() {
		super("exit", "Eteint le serveur", "stop", "end");
	}

	@Override
	public boolean onCommand(final String cmd, final String[] args) {
		new Thread(() -> {

			this.utils.ConsoleMessage("Shutting down...");

			Main.getInstance().getSockServer().close();

			Main.getInstance().getGames().forEach((name, games) -> {
				games.getServers().forEach((id, sv) -> {
					this.utils.ConsoleMessage("Stopping and Removing " + name);
					sv.stop();
				});
			});

			Main.getInstance().getConsole().Disable();
			
			try {
				Thread.sleep(10000);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}

			new Execute("rm", "-rf", "running/").ExecuteCommand();
			
			System.exit(0);
		}).start();
		return true;
	}

}
