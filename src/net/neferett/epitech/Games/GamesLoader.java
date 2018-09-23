package net.neferett.epitech.Games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import net.neferett.epitech.Main;
import net.neferett.epitech.utils.Utils;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.epitech.utils.json.JSONObject;

public class GamesLoader
{
    private List<String> config;
    private final HashMap<String, GamesManager> games;
    private final Utils utils;
    
    public GamesLoader() throws IOException {
        this.games = new HashMap<String, GamesManager>();
        this.utils = new Utils();
        this.loadPath();
    }
    
    public HashMap<String, GamesManager> getGames() {
        return this.games;
    }
    
	public void loadPath() throws IOException {
		this.config = new ReadFile(Main.getInstance().getGamesConfig()).getFile();
		this.config.forEach((line) -> {
			if (!line.contains("libs") && !line.contains("IP") && (line.contains("name") || line.contains("path")))
				this.newGame(line);
			else if (line.contains("libs"))
				this.newLib(line);
		});
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