package net.neferett.epitech;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.epitech.Games.games.Games;
import net.neferett.epitech.Games.games.GamesLoader;
import net.neferett.epitech.Games.games.GamesManager;
import net.neferett.epitech.console.Console;
import net.neferett.epitech.console.commands.CreateServer;
import net.neferett.epitech.console.commands.IgnoreCommand;
import net.neferett.epitech.console.commands.ListCommand;
import net.neferett.epitech.console.commands.RefreshGames;
import net.neferett.epitech.console.commands.RemoveServer;
import net.neferett.epitech.console.commands.exitCommand;
import net.neferett.epitech.console.commands.helpCommand;
import net.neferett.epitech.console.commandsManagers.CreateCommand;
import net.neferett.epitech.events.MessagesEvents;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.socket.SockServer;
import net.neferett.socket.packet.Packet;
import net.neferett.socket.packet.PacketAction;

public class Main extends CreateCommand {

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}
	
	private GamesLoader gl;
	
	public GamesLoader getGl() {
		return gl;
	}

	public static void main(final String[] args) throws IOException, JSONException {
		instance = new Main();
		instance.mainaddr = "149.202.65.5";

		instance.setGamesConfig("config.yml");		
		instance.setGames((instance.gl = new GamesLoader()).getGames());
		
		instance.mainport = args.length == 1 ? Integer.parseInt(args[0]) : instance.getGl().getPort();
		
		System.out.println("Started on port " + instance.mainport);
		
		CreateCmd("help", new helpCommand());
		CreateCmd("exit", new exitCommand());
		CreateCmd("list", new ListCommand());
		CreateCmd("newserver", new CreateServer());
		CreateCmd("delserver", new RemoveServer());
		CreateCmd("ignore", new IgnoreCommand());
		CreateCmd("reload", new RefreshGames());
		instance.console = new Console();
		instance.s = new SockServer(Integer.parseInt(args[0]), new MessagesEvents());
		instance.s.addPacket(new Packet(PacketAction.RECEIVE));
	}

	private Console					console;

	private HashMap<String, Games>	games	= new HashMap<>();

	private String					gamesFolder;

	private GamesManager			gamesManager;

	private String					libspath;

	private String					mainaddr;

	private int						mainport;

	private String					MotherIP;

	private int						MotherPort;

	private SockServer				s;

	public Console getConsole() {
		return this.console;
	}

	public HashMap<String, Games> getGames() {
		return this.games;
	}

	public String getGamesConfig() {
		return this.gamesFolder;
	}

	public GamesManager getGamesManager() {
		return this.gamesManager;
	}

	public String getLibspath() {
		return this.libspath;
	}

	public String getMainaddr() {
		return this.mainaddr;
	}

	public int getMainport() {
		return this.mainport;
	}

	public String getMotherIP() {
		return this.MotherIP;
	}

	public int getMotherPort() {
		return this.MotherPort;
	}

	public SockServer getSockServer() {
		return this.s;
	}

	public void setGames(final HashMap<String, Games> dbs) {
		this.games = dbs;
	}

	private void setGamesConfig(final String file) {
		this.gamesFolder = file;
	}

	public void setLibspath(final String libspath) {
		this.libspath = libspath;
	}

	public void setMainaddr(final String mainaddr) {
		this.mainaddr = mainaddr;
	}

	public void setMainport(final int mainport) {
		this.mainport = mainport;
	}

	public void setMotherIP(final String motherIP) {
		this.MotherIP = motherIP;
	}

	public void setMotherPort(final int motherPort) {
		this.MotherPort = motherPort;
	}

}
