package net.neferett.console.commands;

import net.neferett.Main;
import net.neferett.console.commandsManagers.Commands;
import net.neferett.executor.Execute;

public class ExitCommand extends Commands {

	public ExitCommand() {
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
