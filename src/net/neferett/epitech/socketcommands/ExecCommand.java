package net.neferett.epitech.socketcommands;

import net.neferett.epitech.executor.Execute;

public class ExecCommand extends SocketCommand{
	
	public ExecCommand() {
		super("exec");
	}

	@Override
	public void runCommand(String[] args) {
		String message = this.event.getPacket().getMessage();
		
		String arg = message.substring(args[0].length() + 1, message.length());
		
		String launch = arg.replace("@@", "\n");
		
		new Execute(launch.split(" ")).ExecuteCommand();
	}
}
