package net.neferett.epitech.Games.server;

import net.neferett.socket.api.FastSendMessage;

public class ServerDistantManager extends Servers {

	public ServerDistantManager(final int id, final String name, final String path, final String addr, final int port,
			final String stopcmd) {
		super(id, name, path, addr, port, stopcmd);
	}

	@Override
	public void CreateServer() {}

	@Override
	public void LaunchServer() {
		new FastSendMessage(this.addr, this.port, "start " + this.name).build();
	}

	@Override
	public void RemoveServer() {
		new FastSendMessage(this.addr, this.port, "stop " + this.path).build();
	}

	@Override
	public void sendDistantCommand(final String cmd) {
		new FastSendMessage(this.addr, this.port, "exec " + this.name + " " + this.id + " " + cmd).build();
	}

	@Override
	public void stop() {
		new FastSendMessage(this.addr, this.port, "exec " + this.name + " " + this.id + " " + this.stopcmd).build();
	}

}
