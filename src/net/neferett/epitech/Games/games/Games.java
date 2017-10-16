package net.neferett.epitech.Games.games;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.epitech.Games.server.Servers;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;

public abstract class Games {

	protected boolean					accept;
	protected String					addr;
	protected int						i;
	protected boolean					ignored;
	protected boolean					increment;
	protected JSONObject				js;
	protected int						port;
	protected boolean					restart;
	protected HashMap<Integer, Servers>	servers	= new HashMap<>();
	protected String					stopcmd;
	protected boolean					update;

	public Games(final JSONObject js) throws IOException, JSONException {
		this.js = js;
		this.port = js.getInt("port") - 1;
		this.ignored = js.getBoolean("ignored");
		this.restart = js.getBoolean("restart");
		this.update = js.getBoolean("update");
		this.stopcmd = js.getString("stop");
		this.accept = js.getBoolean("accept");
		this.increment = js.getBoolean("increment");
	}

	public Games(final JSONObject js, final boolean isSpec) throws JSONException {
		this.js = js;
		this.addr = js.getString("IP");
		this.port = js.getInt("port");
	}

	public abstract void CreateServer();

	public HashMap<Integer, Servers> getServers() {
		return this.servers;
	}

	public boolean isAccept() {
		return this.accept;
	}

	public boolean isIgnored() {
		return this.ignored;
	}

	public boolean isRestart() {
		return this.restart;
	}

	public void RemoveServer(final int id) {
		this.servers.get(id).RemoveServer();
		this.servers.remove(id);
	}

	public abstract void sendAll(String cmd);

	public void setIgnored(final boolean ignored) {
		this.ignored = ignored;
	}

	public void setServers(final HashMap<Integer, Servers> servers) {
		this.servers = servers;
	}
}
