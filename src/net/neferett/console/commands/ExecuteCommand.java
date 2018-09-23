package net.neferett.console.commands;

import net.neferett.console.commandsManagers.Commands;
import net.neferett.executor.Execute;

public class ExecuteCommand extends Commands{

	public ExecuteCommand() {
		super("executor", "en developement ne pas utiliser sous peine de crash de l'infrastructure", "ex");
	}

	@Override
	public boolean onCommand(String p0, String[] p1) {
		new Execute(p1).ExecuteCommand();
		return true;
	}
	
}
