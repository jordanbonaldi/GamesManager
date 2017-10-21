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

	private List<String>							config;
	private final HashMap<String, Games>	games	= new HashMap<>();
	private final Utils							utils;
	private int									port;
	
	public int getPort() {
		return port;
	}

	public GamesLoader() throws IOException {
		this.utils = new Utils();
		this.loadPath();
	}

	public HashMap<String, Games> getGames() {
		return this.games;
	}

	public void loadMotherServer(JSONObject js) throws JSONException {
		
		Main.getInstance().setMotherIP(js.getString("MotherIP"));
		Main.getInstance().setMotherPort(js.getInt("MotherPort"));
		
	}

	public void port_loader(JSONObject j) throws JSONException{
		this.port = j.getInt("port");
	}
	
	public void loadPath() throws IOException {
		this.config = new ReadFile(Main.getInstance().getGamesConfig()).getFile();
		this.config.forEach((line) -> {
			
			JSONObject js = null;
			
			try {
				js = new JSONObject(line);
				
				this.getClass().getMethod(js.getString("method"), JSONObject.class).invoke(this, js);		
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	public void distantGame_loader(JSONObject js) throws JSONException, IOException {

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
	}

	public void game_loader(JSONObject js) throws JSONException, IOException {
		if (new File(js.getString("path")) != null && new File(js.getString("path")).isDirectory()) {
			this.utils.ConsoleMessage("Registering Game : " + js.getString("name"));
			this.games.put(js.getString("name"), new GamesManager(js));
		} else
			this.utils.ConsoleMessage("Probleme with " + js.getString("name") + " path don't match !");
	}

	public void lib_loader(JSONObject js) throws JSONException, IOException {
		Main.getInstance().setLibspath(js.getString("libspath"));
		Files.walk(Paths.get(Main.getInstance().getLibspath())).forEach(path -> {
			final String name = path.toString().replaceAll(Main.getInstance().getLibspath().substring(1), "")
					.replaceAll("//", "");

			if (Files.isRegularFile(path))
				this.utils.ConsoleMessage("Registering Lib: " + name);
		});
	}

}
