package net.neferett.epitech.console.commandsManagers;

import java.util.Arrays;
import java.util.List;

import net.neferett.epitech.utils.Utils;

public abstract class Commands extends CreateCommand{
	
	protected String		name;
	protected String 		desc;
	protected Utils			utils;
	protected List<String>	alias;
	
	public Commands(String cmdname, String description, String... alias){
		this.name = cmdname;
		this.desc = description;
		this.alias = Arrays.asList(alias);
		this.utils = new Utils();
	}

	public List<String> getAlias()
	{
		return (this.alias);
	}
	
	public String getDesc()
	{
		return (this.desc);
	}
	
	public abstract boolean onCommand(String cmd, String[] args);
}
