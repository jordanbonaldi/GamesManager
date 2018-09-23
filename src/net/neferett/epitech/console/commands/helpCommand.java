package net.neferett.epitech.console.commands;

import java.util.Map.Entry;

import net.neferett.epitech.console.commandsManagers.Commands;
import net.neferett.epitech.console.commandsManagers.CreateCommand;

public class helpCommand extends Commands{

	private String fh = "================================";

	public helpCommand() {
		super("help", "Affiche le menu des commandes");
	}

	public String getName(Entry<String, Commands> entry)
	{
		StringBuilder sb = new StringBuilder();
		
		if (entry.getValue().getAlias().isEmpty())
			return (entry.getKey());
		else
		{
			sb.append(entry.getKey() + " {alias: ");
			entry.getValue().getAlias().forEach((tmp) ->{
				sb.append(tmp + ", ");
			});
			sb.setCharAt(sb.length() - 2, '}');
			sb.substring(sb.length() - 1);
			return (sb.toString());
		}
	}
	
	@Override
	public boolean onCommand(String cmd, String[] args) {
		System.out.println(fh);
		System.out.println("");
		for (Entry<String, Commands> entry : CreateCommand.cmd.entrySet())
			System.out.println(getName(entry) + "  --  " + entry.getValue().getDesc());
		System.out.println("");
		System.out.println(fh);
		return true;
	}

}
