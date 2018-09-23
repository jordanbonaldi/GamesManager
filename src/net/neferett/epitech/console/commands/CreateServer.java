package net.neferett.epitech.console.commands;

import net.neferett.epitech.Main;
import net.neferett.epitech.console.commandsManagers.Commands;

public class CreateServer extends Commands {

	public CreateServer() {
		super("newserver", "Créé des serveurs", "create", "cr");
	}

	@Override
	public boolean onCommand(final String cmd, final String[] args) {
		if (args.length < 2)
			System.out.println("Usage: newserver <ServerGroup> <nb>[optional]");
		if (args.length == 2) {
			if (!Main.getInstance().getGames().containsKey(args[1])) {
				System.out.println("Server group unexistant");
				return false;
			} else
				Main.getInstance().getGames().get(args[1]).CreateServer();
			return true;
		} else if (args.length == 3) {
			int nb = Integer.parseInt(args[2]);
			if (!Main.getInstance().getGames().containsKey(args[1])) {
				System.out.println("Server group unexistant");
				return false;
			}
			if (nb > 20) {
				System.out.println("Too many server !");
				return false;
			}
			System.out.println("Creating " + nb + " " + args[1] + "' servers");
			while (--nb >= 0)
				Main.getInstance().getGames().get(args[1]).CreateServer();
		}
		return true;
	}
}
