package net.neferett.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import net.neferett.utils.json.JSONException;

public class Plugins {

	private File file;

	private URLClassLoader loader;
	
	private Class<?> clazz;
	
	private Constructor<?> constructor;
	
	private ConfigLoader config;
	
	private PluginInterface instance;
	
	public Plugins(File file) {
		this.file = file;
		
		this.buildConfigLoader();
		
		try {
			this.buildClassLoader();
		} catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildConfigLoader() {
		try {
			System.out.println("Searching into : " + this.file.getPath());
			this.config = new ConfigLoader(this.file.getPath().substring(0, this.file.getPath().length() - 4), "config.json");
			System.out.println("Got package : " + this.config.getPackage());
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void buildClassLoader() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JSONException {
		this.loader = URLClassLoader.newInstance(new URL[] { this.file.toURL() }, this.getClass().getClassLoader());
		this.clazz = Class.forName(this.config.getPackage(), true, this.loader);
		
		this.constructor = this.clazz.getConstructor();
		this.instance = (PluginInterface) this.constructor.newInstance();
	}
	
	public void launchActionOnMethod(String method) {
		try {
			Method m = this.clazz.getDeclaredMethod(method);
			
			m.setAccessible(true);
			
			m.invoke(this.instance);
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void onEnable() {
		this.instance.onEnable();
	}
	
	public void onDisable() {
		this.instance.onDisable();
	}

}
