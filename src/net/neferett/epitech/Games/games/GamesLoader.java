package net.neferett.epitech.Games.games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import net.neferett.epitech.Main;
import net.neferett.epitech.utils.ReadFile;
import net.neferett.epitech.utils.Utils;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;
import net.neferett.socket.api.FastReceiveMessage;
import net.neferett.socket.api.FastSendMessage;

public class GamesLoader {

	private List<String>					config;
	private final HashMap<String, Games>	games	= new HashMap<>();
	private final Utils						utils;

	public GamesLoader() throws IOException {
		this.utils = new Utils();
		this.loadPath();
	}

	public HashMap<String, Games> getGames() {
		return this.games;
	}

	private void loadMotherServer(final String line) {
		try {
			final JSONObject js = new JSONObject(line);

			Main.getInstance().setMotherIP(js.getString("MotherIP"));
			Main.getInstance().setMotherPort(js.getInt("MotherPort"));
		} catch (final JSONException e) {
			e.printStackTrace();
		}
	}

	public void loadPath() throws IOException {
		this.config = new ReadFile(Main.getInstance().getGamesConfig()).getFile();
		this.config.forEach((line) -> {
			if (!line.contains("libs") && !line.contains("IP") && (line.contains("name") || line.contains("path")))
				this.newGame(line);
			else if (!line.contains("libs") && line.contains("IP") && !line.contains("Mother"))
				this.newDistantGame(line);
			else if (line.contains("libs"))
				this.newLib(line);
			else if (line.contains("MotherServer"))
				this.loadMotherServer(line);
		});
	}

	private void newDistantGame(final String line) {
		JSONObject js = null;
		try {
			js = new JSONObject(line);

			new FastSendMessage(js.getString("IP"), js.getInt("port"), "exists " + js.getString("path"));

			System.out.println(js.getString("IP"));
			System.out.println(js.getInt("port"));

			if (new FastReceiveMessage(js.getString("IP"), js.getInt("port")).build().equals("true")) {
				this.utils.ConsoleMessage("Registering Distant Game : " + js.getString("name")
						+ " from distant machine: " + js.getString("IP"));
				this.games.put(js.getString("name"), new GamesDistantManager(js));
			} else
				this.utils.ConsoleMessage("Probleme with " + js.getString("name")
						+ " path don't match on distant machine :" + js.getString("IP"));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	private void newGame(final String line) {
		JSONObject js = null;
		try {
			js = new JSONObject(line);

			if (new File(js.getString("path")) != null && new File(js.getString("path")).isDirectory()) {
				this.utils.ConsoleMessage("Registering Game : " + js.getString("name"));
				this.games.put(js.getString("name"), new GamesManager(js));
			} else
				this.utils.ConsoleMessage("Probleme with " + js.getString("name") + " path don't match !");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	private void newLib(final String line) {
		JSONObject js = null;
		try {
			js = new JSONObject(line);
			Main.getInstance().setLibspath(js.getString("libspath"));
			Files.walk(Paths.get(Main.getInstance().getLibspath())).forEach(path -> {
				final String name = path.toString().replaceAll(Main.getInstance().getLibspath().substring(1), "")
						.replaceAll("//", "");

				if (Files.isRegularFile(path))
					this.utils.ConsoleMessage("Registering Lib: " + name);
			});
		} catch (final JSONException | IOException e) {
			e.printStackTrace();
		}
	}

}
