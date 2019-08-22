package net.neferett.console;

import java.io.*;
import java.util.*;

import net.neferett.Main;
import net.neferett.console.commandsManagers.*;
import net.neferett.utils.*;

public abstract class ConsoleManager implements Runnable
{
    private String msg;
    protected Utils utils;
    private Thread thread;
    
    private CommandCreator creator;
    
    public ConsoleManager() {
        this.utils = new Utils();
        this.StartConsoleAction();
        this.creator = Main.getInstance().getCreator();
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
        for (final Map.Entry<String, Commands> entry : this.creator.getCmd().entrySet()) {
            this.utils.ConsoleMessage("UnRegistering command : " + entry.getKey());
        }
        Main.getInstance().getCreator().clear();
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
        for (final Map.Entry<String, Commands> entry : this.creator.getCmd().entrySet()) {
            if ((entry.getKey().equalsIgnoreCase(msg) || entry.getValue().getAlias().contains(msg)) && (executed = true)) {
                entry.getValue().onCommand(entry.getKey(), this.ParseArgs());
            }
        }
        if (!executed) {
            System.out.println("Unknown command type \"help\" for help");
        }
    }
}
