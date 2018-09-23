package net.neferett.console.commands;

import net.neferett.Main;
import net.neferett.console.commandsManagers.Commands;

public class RemoveServer extends Commands{
	
	public RemoveServer() {
		super("delserver", "Supprime des serveurs", "del", "remove");
	}

	@Override
	public boolean onCommand(String cmd, String[] args) {
		if (args.length < 2){
			System.out.println("Usage: newserver <ServerGroup> <id>[optional]");
		}
		if (args.length == 2){
			if (!Main.getInstance().getGames().containsKey(args[1])){
				System.out.println("Server group unexistant");
				return false;
			}else
				System.out.println("Removing all " + args[1] + "' servers");
				Main.getInstance().getGames().get(args[1]).getServers().forEach((id, sv)->{
					sv.stop();
				});
			return true;
		}
		else if (args.length == 3){
			int nb = Integer.parseInt(args[2]);
			if (!Main.getInstance().getGames().containsKey(args[1])){
				System.out.println("Server group unexistant");
				return false;
			}
			else if (Main.getInstance().getGames().get(args[1]).getServers().get(nb) == null){
				System.out.println("Server id: " + args[2] + " unexistant");
				return false;
			}
			System.out.println("Removing " + args[1] + " " + nb);
			Main.getInstance().getGames().get(args[1]).getServers().get(nb).stop();
		}
		return true;
	}
}
