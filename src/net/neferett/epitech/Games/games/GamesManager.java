package net.neferett.epitech.Games.games;

import java.io.IOException;

import net.neferett.epitech.Games.server.ServerManager;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;

public class GamesManager extends Games {

	public GamesManager(final JSONObject js) throws IOException, JSONException {
		super(js);
	}

	@Override
	public void CreateServer() {
		if (this.isIgnored()) {
			System.out.println("Can't create ignored server");
			return;
		}
		try {
			this.servers.put(++this.i,
					new ServerManager(this.i, this.js.getString("name"), this.js.getString("path"),
							this.js.getString("jar"), this.js.getString("maxRam"),
							this.increment ? ++this.port : this.port + 1, this.update, this.stopcmd));
			this.servers.get(this.i).LaunchServer();
		} catch (final JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendAll(final String cmd) {
		this.getServers().forEach((s, server) -> {
			server.LaunchCommand(cmd);
		});
	}

}
