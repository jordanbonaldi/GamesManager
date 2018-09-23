package net.neferett.epitech.console;

import net.neferett.epitech.utils.*;
import java.io.*;
import net.neferett.epitech.console.commandsManagers.*;
import java.util.*;

public abstract class ConsoleManager extends CreateCommand implements Runnable
{
    protected String msg;
    protected Utils utils;
    protected Thread thread;
    
    public ConsoleManager() {
        this.utils = new Utils();
        this.StartConsoleAction();
    }
    
    public void StartConsoleAction() {
        this.utils.ConsoleMessage("Interactive console activated");
        (this.thread = new Thread(this)).start();
    }
    
    @Override
    public void run() {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                Thread.sleep(10L);
                this.msg = in.readLine();
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            this.setCommand(this.msg);
            this.getAppropriateAction();
        }
    }
    
    public void unRegisteringCommand() {
        for (final Map.Entry<String, Commands> entry : ConsoleManager.cmd.entrySet()) {
            this.utils.ConsoleMessage("UnRegistering command : " + entry.getKey());
        }
        CreateCommand.cmd.clear();
    }
    
    public void setCommand(final String cmd) {
        this.msg = cmd;
    }
    
    public String[] ParseArgs() {
        return this.msg.split(" ");
    }
    
    public void getAppropriateAction() {
        final String msg = this.msg.split(" ")[0];
        if (this.msg == null) {
            return;
        }
        boolean executed = false;
        for (final Map.Entry<String, Commands> entry : ConsoleManager.cmd.entrySet()) {
            if ((entry.getKey().equalsIgnoreCase(msg) || entry.getValue().getAlias().contains(msg)) && (executed = true)) {
                entry.getValue().onCommand(entry.getKey(), this.ParseArgs());
            }
        }
        if (!executed) {
            System.out.println("Unknown command type \"help\" for help");
        }
    }
}
