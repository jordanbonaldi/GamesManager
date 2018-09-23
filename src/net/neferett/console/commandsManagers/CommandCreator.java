package net.neferett.console.commandsManagers;

import java.util.*;

public class CommandCreator
{
    public HashMap<String, Commands> cmd;
    
    public HashMap<String, Commands> getCmd() {
		return cmd;
    }
    
    public CommandCreator() {
    	this.cmd = new HashMap<String, Commands>();
    }
    
    public void addCmd(final String cmdname, final Commands obj) {
        cmd.put(cmdname, obj);
    }
    
    public void clear() {
    	this.cmd.clear();
    }
}
