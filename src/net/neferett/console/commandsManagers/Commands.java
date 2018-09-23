package net.neferett.console.commandsManagers;

import java.util.*;

import net.neferett.utils.*;

public abstract class Commands
{
    protected String name;
    protected String desc;
    protected Utils utils;
    protected List<String> alias;
    
    public Commands(final String cmdname, final String description, final String... alias) {
        this.name = cmdname;
        this.desc = description;
        this.alias = Arrays.asList(alias);
        this.utils = new Utils();
    }
    
    public List<String> getAlias() {
        return this.alias;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public abstract boolean onCommand(final String p0, final String[] p1);
}
