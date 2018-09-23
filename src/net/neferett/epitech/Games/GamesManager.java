package net.neferett.epitech.Games;

import java.util.*;
import java.io.*;
import net.neferett.epitech.utils.json.*;

public class GamesManager
{
    private int i;
    private boolean ignored;
    private final boolean increment;
    private final JSONObject js;
    private int port;
    private final boolean restart;
    private HashMap<Integer, ServerManager> servers;
    private final String stopcmd;
    private final boolean update;
    private final String name;
    
    public GamesManager(final JSONObject js) throws IOException, JSONException {
        this.i = 0;
        this.servers = new HashMap<Integer, ServerManager>();
        this.js = js;
        this.port = js.getInt("port") - 1;
        this.ignored = js.getBoolean("ignored");
        this.restart = js.getBoolean("restart");
        this.update = js.getBoolean("update");
        this.stopcmd = js.getString("stop");
        this.increment = js.getBoolean("increment");
        this.name = js.getString("name");
    }
    
    public void CreateServer() {
        if (this.isIgnored()) {
            System.out.println("Can't create ignored server: " + this.getName());
            return;
        }
        try {
            this.servers.put(++this.i, new ServerManager(this.i, this.js.getString("name"), this.js.getString("path"), this.js.getString("jar"), this.js.getString("maxRam"), this.increment ? (++this.port) : (this.port + 1), this.update, this.stopcmd));
            this.servers.get(this.i).LaunchServer();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public HashMap<Integer, ServerManager> getServers() {
        return this.servers;
    }
    
    public boolean isIgnored() {
        return this.ignored;
    }
    
    public boolean isRestart() {
        return this.restart;
    }
    
    public String getName() {
		return name;
	}
    
    public void RemoveServer(final int id) {
    	if (this.servers.get(id) == null) {
    		System.out.println("Can't remove un-existing folder");
    		return;
    	}
        this.servers.get(id).RemoveServer();
        this.servers.remove(id);
    }
    
    public void setIgnored(final boolean ignored) {
        this.ignored = ignored;
    }
    
    public void setServers(final HashMap<Integer, ServerManager> servers) {
        this.servers = servers;
    }
}
