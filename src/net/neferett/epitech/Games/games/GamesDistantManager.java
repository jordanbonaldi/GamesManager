package net.neferett.epitech.Games.games;

import java.io.IOException;

import net.neferett.epitech.Games.server.ServerDistantManager;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;

public class GamesDistantManager extends Games {

	public GamesDistantManager(final JSONObject js) throws IOException, JSONException {
		super(js, true);
	}

	@Override
	public void CreateServer() {
		if (this.isIgnored()) {
			System.out.println("Can't create ignored server");
			return;
		}
		try {
			this.servers.put(++this.i,
					new ServerDistantManager(this.i, this.js.getString("name"), this.js.getString("path"),
							this.js.getString("IP"), this.js.getInt("port"), this.js.getString("stop")));
			this.servers.get(this.i).LaunchServer();
		} catch (final JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendAll(final String cmd) {
		this.getServers().forEach((s, sv) -> {
			sv.sendDistantCommand(cmd);
		});
	}

}
