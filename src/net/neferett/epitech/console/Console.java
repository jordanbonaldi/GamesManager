package net.neferett.epitech.console;

public class Console extends ConsoleManager{
	
	public Console()
	{
		super();
	}
	
	public void Disable(){
		unRegisteringCommand();
		this.utils.ConsoleMessage("Interactive console disabled");
	}
}
