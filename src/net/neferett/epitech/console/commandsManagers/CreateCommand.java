package net.neferett.epitech.console.commandsManagers;

import java.util.*;

public abstract class CreateCommand
{
    protected static HashMap<String, Commands> cmd;
    
    public HashMap<String, Commands> getCmd() {
		return cmd;
	}
    
    static {
        CreateCommand.cmd = new HashMap<String, Commands>();
    }
    
    public static void CreateCmd(final String cmdname, final Commands obj) {
        CreateCommand.cmd.put(cmdname, obj);
    }
}
