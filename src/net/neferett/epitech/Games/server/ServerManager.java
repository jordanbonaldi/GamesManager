package net.neferett.epitech.Games.server;

import java.io.File;

import net.neferett.epitech.Main;
import net.neferett.epitech.executor.Execute;

public class ServerManager extends Servers {

	public ServerManager(final int id, final String name, final String path, final String jar, final String maxRam,
			final int port, final boolean update, final String stopcmd) {
		super(id, name, path, jar, maxRam, port, update, stopcmd);
	}

	@Override
	public void CreateServer() {
		this.utils.ConsoleMessage("Creating server " + this.name + " in " + "running/" + this.name + "/" + this.id);
		new File(this.getRunningPath()).mkdirs();
		new Execute("cp", "-r", this.path + "/.", this.getRunningPath()).ExecuteCommand();
		if (this.jar.contains("spigot") && this.update)
			new Execute("cp", "-r", Main.getInstance().getLibspath(), this.getRunningPath() + "/plugins/.")
					.ExecuteCommand();
	}

	@Override
	public void LaunchServer() {
		this.utils.ConsoleMessage("Launching " + this.name + " " + this.id + " on SCREEN : " + this.getScreen());
		new Execute(this.JavaLaunch()).ExecuteCommand(new File(this.getRunningPath()));
	}

	@Override
	public void RemoveServer() {
		new Execute("rm", "-rf", this.getRunningPath()).ExecuteCommand();
	}

	@Override
	public void sendDistantCommand(final String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		this.LaunchCommand(this.stopcmd);
	}

}
