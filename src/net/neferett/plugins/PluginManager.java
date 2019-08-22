package net.neferett.plugins;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class PluginManager {

	private HashMap<String, Plugins> plugins;
	
	public PluginManager() {
		this.plugins = new HashMap<>();
	}
	
	public void addPlugin(String name, File file) {
		this.plugins.put(name, new Plugins(file));
	}
	
	public Plugins getPlugin(String name) {
		return this.getPlugin(name);
	}
	
	public void loadPluginsFromFolder(String path) {
		File folder = new File(path);
		
		for (final File f : Objects.requireNonNull(folder.listFiles())) {
			if (f.getName().contains(".jar")) {
				String name = f.getName().substring(0, f.getName().length() - 4);
				System.out.println("Loading plugin : " + name);
				this.addPlugin(name, f);
			}
		}
	}
	
	public void actionOnPlugin(String plugin, String action) {
		this.getPlugin(plugin).launchActionOnMethod(action);
	}
	
	public void onAllPluginAction(String action) {
		this.plugins.forEach((a, b) -> {
			b.launchActionOnMethod(action);
		});
	}
	
	public void onAllPluginEnable() {
		this.plugins.forEach((a, b) -> {
			b.onEnable();
		});
	}
	
	public void onAllPluginDisable() {
		this.plugins.forEach((a, b) -> {
			b.onDisable();
		});
	}

	
	public void onPluginEnable(String pl) {
		this.getPlugin(pl).onEnable();
	}
	
	public void onPluginDisable(String pl) {
		this.getPlugin(pl).onDisable();
	}
	
}
