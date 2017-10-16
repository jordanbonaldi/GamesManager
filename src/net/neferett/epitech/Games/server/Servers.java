package net.neferett.epitech.Games.server;

import java.io.File;

import net.neferett.epitech.executor.Execute;
import net.neferett.epitech.utils.Utils;

public abstract class Servers {

	protected String	addr;
	protected String	dir;
	protected int		id	= 0;
	protected String	jar;
	protected String	javadir;
	protected String	maxRam;
	protected String	name;
	protected String	path;
	protected int		port;
	protected String	stopcmd;
	protected boolean	update;
	protected Utils		utils;

	public Servers(final int id, final String name, final String path, final String addr, final int port,
			final String stopcmd) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.addr = addr;
		this.port = port;
		this.stopcmd = stopcmd;
	}

	public Servers(final int id, final String name, final String path, final String jar, final String maxRam,
			final int port, final boolean update, final String stopcmd) {
		this.id = id;
		this.javadir = System.getProperty("java.home");
		this.utils = new Utils();
		this.name = name;
		this.path = path;
		this.jar = jar;
		this.update = update;
		this.maxRam = maxRam;
		this.stopcmd = stopcmd;
		this.dir = new File(".").getAbsolutePath();
		this.dir = this.dir.substring(0, this.dir.length() - 1);
		this.port = port;
		this.utils.ConsoleMessage("Registering " + name + " with port " + port);
		this.CreateServer();
	}

	private String[] CmdRet(final String cmd) {
		return ("screen -S " + this.getScreen() + " -p 0 -X stuff " + cmd + "\n").split(" ");
	}

	public abstract void CreateServer();

	private String getJRE() {
		if (this.javadir == null)
			throw new IllegalStateException("java.home");
		else
			return new File(this.javadir, "bin/java").toString();
	}

	protected String getRunningPath() {
		return this.dir + "running/" + this.name + "/" + this.id + "/";
	}

	protected String getScreen() {
		return this.name + "-" + this.id;
	}

	protected String[] JavaLaunch() {
		return ("screen -dmS " + this.getScreen() + " " + this.getJRE() + " -jar -Xincgc -Xmx" + this.maxRam + " "
				+ this.getRunningPath() + this.jar + " -p " + this.port).split(" ");
	}

	public void LaunchCommand(final String cmd) {
		new Execute(this.CmdRet(cmd)).ExecuteCommand();
	}

	public abstract void LaunchServer();

	public abstract void RemoveServer();

	public abstract void sendDistantCommand(String msg);

	public abstract void stop();
}
