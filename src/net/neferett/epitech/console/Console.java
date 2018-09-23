package net.neferett.epitech.console;

public class Console extends ConsoleManager
{
    public void Disable() {
        this.unRegisteringCommand();
        this.utils.ConsoleMessage("Interactive console disabled");
    }
}
