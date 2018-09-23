package net.neferett.epitech;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.epitech.Games.GamesLoader;
import net.neferett.epitech.Games.GamesManager;
import net.neferett.epitech.console.Console;
import net.neferett.epitech.console.commands.CreateServer;
import net.neferett.epitech.console.commands.ExecuteCommand;
import net.neferett.epitech.console.commands.IgnoreCommand;
import net.neferett.epitech.console.commands.ListCommand;
import net.neferett.epitech.console.commands.RefreshGames;
import net.neferett.epitech.console.commands.RemoveServer;
import net.neferett.epitech.console.commands.exitCommand;
import net.neferett.epitech.console.commands.helpCommand;
import net.neferett.epitech.console.commandsManagers.CreateCommand;
import net.neferett.epitech.events.MessagesEvents;
import net.neferett.epitech.plugins.PluginManager;
import net.neferett.epitech.socketcommands.ExecCommand;
import net.neferett.epitech.socketcommands.GIgnoreCommand;
import net.neferett.epitech.socketcommands.GetIgnoredCommand;
import net.neferett.epitech.socketcommands.ListSocketCommands;
import net.neferett.epitech.socketcommands.OpenGamesCommand;
import net.neferett.epitech.socketcommands.SocketCommandAdder;
import net.neferett.epitech.socketcommands.StartCommand;
import net.neferett.epitech.socketcommands.StopCommand;
import net.neferett.epitech.utils.json.JSONException;
import net.neferett.socket.SockServer;
import net.neferett.socket.packet.Packet;
import net.neferett.socket.packet.PacketAction;

public class Main extends CreateCommand
{
    private static Main instance;
    private Console console;
    private HashMap<String, GamesManager> games;
    private String gamesFolder;
    private GamesManager gamesManager;
    private String libspath;
    private SockServer s;
    private SocketCommandAdder adder;
    
    private PluginManager pluginmanager;
    
    public Main() {
        this.games = new HashMap<String, GamesManager>();
    }
    
    public SocketCommandAdder getAdder() {
		return adder;
	}
    
    
    public static Main getInstance() {
        return Main.instance;
    }
    
	public static void main(final String[] args) throws IOException, JSONException {
        instance = new Main();
        instance.load(args);
    }
    
	public void load(String[] args) throws IOException {
        this.adder = new SocketCommandAdder();
        if (args.length == 0) {
            System.out.println("Make sure to set the port before relaunching the server");
            return;
        }
        if (args.length == 1) {
            this.setGamesConfig("config.yml");
        }
        else {
            this.setGamesConfig(args[1]);
        }
        this.setGames(new GamesLoader().getGames());
        this.pluginmanager = new PluginManager();
        
        this.pluginmanager.loadPluginsFromFolder("./plugins/");
        
        this.pluginmanager.onAllPluginEnable();
        
        CreateCommand.CreateCmd("help", new helpCommand());
        CreateCommand.CreateCmd("exit", new exitCommand());
        CreateCommand.CreateCmd("list", new ListCommand());
        CreateCommand.CreateCmd("newserver", new CreateServer());
        CreateCommand.CreateCmd("delserver", new RemoveServer());
        CreateCommand.CreateCmd("ignore", new IgnoreCommand());
        CreateCommand.CreateCmd("reload", new RefreshGames());
        CreateCommand.CreateCmd("executor", new ExecuteCommand());
        this.addSockCommands();
        this.setConsole(new Console());
        this.s = new SockServer(Integer.parseInt(args[0]), new MessagesEvents());
        this.s.addPacket(new Packet(PacketAction.RECEIVE));
	}
	
	public void addSockCommands() {
		this.adder.addCommand(new StartCommand());
		this.adder.addCommand(new StopCommand());
		this.adder.addCommand(new ExecCommand());
		this.adder.addCommand(new ListSocketCommands());
		this.adder.addCommand(new OpenGamesCommand());
		this.adder.addCommand(new GIgnoreCommand());
		this.adder.addCommand(new GetIgnoredCommand());
	}
	
    public Console getConsole() {
        return this.console;
    }
    
    public HashMap<String, GamesManager> getGames() {
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
    
    public SockServer getSockServer() {
        return this.s;
    }
    
    private void setConsole(final Console cons) {
        this.console = cons;
    }
    
    public void setGames(final HashMap<String, GamesManager> dbs) {
        this.games = dbs;
    }
    
    private void setGamesConfig(final String file) {
        this.gamesFolder = file;
    }
    
    public void setLibspath(final String libspath) {
        this.libspath = libspath;
    }
}
