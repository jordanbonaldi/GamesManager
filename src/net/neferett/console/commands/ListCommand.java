package net.neferett.console.commands;

import java.util.stream.Collectors;

import net.neferett.Main;
import net.neferett.console.commandsManagers.Commands;

public class ListCommand extends Commands{


	public ListCommand() {
		super("list", "Liste de tous les serveurs");
	}
	
	@Override
	public boolean onCommand(String cmd, String[] args) {
		Main.getInstance().getGames().forEach((name, games)->{
			System.out.println("===========" + name + "===========");
			games.getServers().forEach((id, sv)->{
				System.out.println("        " + name + ": " + id);
			});
			System.out.println("==================================");
		});
		
		System.out.println("=========== Ignored Games ===========");
		Main.getInstance().getGames().entrySet().stream().filter(sv -> sv.getValue().isIgnored()).collect(Collectors.toList()).forEach(sv -> {
				System.out.println("        " + sv);
		});
		System.out.println("==================================");
		return true;
	}

}
