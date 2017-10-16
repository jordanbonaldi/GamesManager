package net.neferett.epitech.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;

import net.neferett.epitech.console.commandsManagers.Commands;
import net.neferett.epitech.console.commandsManagers.CreateCommand;
import net.neferett.epitech.utils.Utils;

public abstract class ConsoleManager extends CreateCommand implements Runnable {

	protected String	msg;
	protected Thread	thread;
	protected Utils		utils;

	public ConsoleManager() {
		this.utils = new Utils();
		this.StartConsoleAction();
	}

	public void getAppropriateAction() {
		final String msg = this.msg.split(" ")[0];
		if (this.msg == null)
			return;
		boolean executed = false;
		for (final Entry<String, Commands> entry : cmd.entrySet())
			if ((entry.getKey().equalsIgnoreCase(msg) || entry.getValue().getAlias().contains(msg))
					&& (executed = true))
				entry.getValue().onCommand(entry.getKey(), this.ParseArgs());
		if (!executed)
			System.out.println("Unknown command type \"help\" for help");
	}

	public String[] ParseArgs() {
		return this.msg.split(" ");
	}

	@Override
	public void run() {
		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				Thread.sleep(10);
				this.msg = in.readLine();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setCommand(this.msg);
			this.getAppropriateAction();
		}
	}

	public void setCommand(final String cmd) {
		this.msg = cmd;
	}

	public void StartConsoleAction() {
		this.utils.ConsoleMessage("Interactive console activated");
		this.thread = new Thread(this);
		this.thread.start();
	}

	public void unRegisteringCommand() {
		for (final Entry<String, Commands> entry : cmd.entrySet())
			this.utils.ConsoleMessage("UnRegistering command : " + entry.getKey());
		CreateCommand.cmd.clear();
	}

}
