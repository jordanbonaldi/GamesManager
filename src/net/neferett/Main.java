package net.neferett;

import java.io.IOException;
import java.util.HashMap;

import net.neferett.console.Console;
import net.neferett.console.commands.CreateServer;
import net.neferett.console.commands.ExecuteCommand;
import net.neferett.console.commands.IgnoreCommand;
import net.neferett.console.commands.ListCommand;
import net.neferett.console.commands.RefreshGames;
import net.neferett.console.commands.RemoveServer;
import net.neferett.console.commands.ExitCommand;
import net.neferett.console.commands.HelpCommand;
import net.neferett.console.commandsManagers.CommandCreator;
import net.neferett.events.MessagesEvents;
import net.neferett.games.GamesLoader;
import net.neferett.games.GamesManager;
import net.neferett.plugins.PluginManager;
import net.neferett.socket.SockServer;
import net.neferett.socket.packet.Packet;
import net.neferett.socket.packet.PacketAction;
import net.neferett.socketCommands.ExecCommand;
import net.neferett.socketCommands.GIgnoreCommand;
import net.neferett.socketCommands.GetIgnoredCommand;
import net.neferett.socketCommands.ListSocketCommands;
import net.neferett.socketCommands.OpenGamesCommand;
import net.neferett.socketCommands.SocketCommandAdder;
import net.neferett.socketCommands.StartCommand;
import net.neferett.socketCommands.StopCommand;
import net.neferett.utils.json.JSONException;

public class Main
{
    private static Main instance;
    private Console console;
    private HashMap<String, GamesManager> games;
    private String gamesFolder;
    private GamesManager gamesManager;
    private String libspath;
    private SockServer s;
    private SocketCommandAdder adder;
    
    private CommandCreator creator;
    
    private PluginManager pluginmanager;
    
    public Main() {
        this.games = new HashMap<String, GamesManager>();
    }
    
    public SocketCommandAdder getAdder() {
		return adder;
	}
    
    
    public CommandCreator getCreator() {
		return creator;
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
        this.creator = new CommandCreator();
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
        
        this.creator.addCmd("help", new HelpCommand());
        this.creator.addCmd("exit", new ExitCommand());
        this.creator.addCmd("list", new ListCommand());
        this.creator.addCmd("newserver", new CreateServer());
        this.creator.addCmd("delserver", new RemoveServer());
        this.creator.addCmd("ignore", new IgnoreCommand());
        this.creator.addCmd("reload", new RefreshGames());
        this.creator.addCmd("executor", new ExecuteCommand());
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
