package net.neferett.console.commands;

import net.neferett.Main;
import net.neferett.console.commandsManagers.Commands;

public class IgnoreCommand extends Commands{
		
	public IgnoreCommand() {
		super("ignore", "Ignore la cr�ation d'un serveur", "ig");
	}

	@Override
	public boolean onCommand(String cmd, String[] args) {
		if (args.length != 3){
			System.out.println("Usage: ignore <ServerGroup> true/false");
			return false;
		}
		if (!Main.getInstance().getGames().containsKey(args[1])){
			System.out.println("Server group unexistant");
			return false;
		}else{
			if (args[2].equalsIgnoreCase("true") && !Main.getInstance().getGames().get(args[1]).isIgnored()){
				Main.getInstance().getGames().get(args[1]).setIgnored(true);
				System.out.println("Server " + args[1] + " ignored");
				return (true);
			} else if (args[2].equalsIgnoreCase("true")) {
				System.out.println("Server already ignored !");
				return false;
			} else if (args[2].equalsIgnoreCase("false") && Main.getInstance().getGames().get(args[1]).isIgnored()){
				Main.getInstance().getGames().get(args[1]).setIgnored(false);
				System.out.println("Server " + args[1] + " unignored");
				return (true);
			} else {
				System.out.println("Server already not ignored !");
				return false;
			}
		}
	}
}
