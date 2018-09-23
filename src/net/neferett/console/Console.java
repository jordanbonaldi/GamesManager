package net.neferett.console;

public class Console extends ConsoleManager
{
    public void Disable() {
        this.unRegisteringCommand();
        this.utils.ConsoleMessage("Interactive console disabled");
    }
}
