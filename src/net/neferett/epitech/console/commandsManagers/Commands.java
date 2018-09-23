package net.neferett.epitech.console.commandsManagers;

import net.neferett.epitech.utils.*;
import java.util.*;

public abstract class Commands extends CreateCommand
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
