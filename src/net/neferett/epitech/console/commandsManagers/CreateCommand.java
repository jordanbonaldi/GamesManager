package net.neferett.epitech.console.commandsManagers;

import java.util.HashMap;

public abstract class CreateCommand {
	
	protected static HashMap<String, Commands> cmd = new HashMap<>();
	
	public static void CreateCmd(String cmdname, Commands obj){
		cmd.put(cmdname, obj);
	}

}
