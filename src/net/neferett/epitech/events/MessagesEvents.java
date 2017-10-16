package net.neferett.epitech.events;

import java.io.File;

import net.neferett.epitech.Main;
import net.neferett.epitech.Games.games.GamesDistantManager;
import net.neferett.socket.api.FastSendMessage;
import net.neferett.socket.events.manager.EventListener;
import net.neferett.socket.events.manager.SocketEvent;
import net.neferett.socket.packet.event.ReceiveMessageEvent;

public class MessagesEvents implements EventListener {

	private void execCommandOnServer(final String cat, final String id, final String cmd) {
		if (Main.getInstance().getGames().containsKey(cat))
			Main.getInstance().getGames().get(cat).getServers().get(cat + "-" + id).LaunchCommand(cmd);
	}

	private void isExistsServer(final String path) {
		final FastSendMessage msg = new FastSendMessage(Main.getInstance().getMotherIP(),
				Main.getInstance().getMotherPort());
		msg.setMessage(new File(path) != null && new File(path).isDirectory() ? "true" : "false");
		msg.build();
	}

	@SocketEvent
	public void onReceiveMessage(final ReceiveMessageEvent e) {
		final String message = e.getPacket().getMessage();
		if (message == null)
			return;
		final String[] msg = message.split(" ");

		if (!(message.contains("start") || message.contains("stop") || message.contains("exists")))
			return;
		if (msg[0].equals("stop"))
			this.stopServer(msg[1], e);
		else if (msg[0].equals("start"))
			this.startServer(msg[1]);
		else if (msg[0].equals("exists"))
			this.isExistsServer(msg[1]);
		else if (msg[0].equals("exec") && msg.length == 4)
			this.execCommandOnServer(msg[1], msg[2], msg[3]);
		else if (msg[0].equals("sendall"))
			this.sendAll(msg[1]);
	}

	private void sendAll(final String cmd) {
		Main.getInstance().getGames().forEach((s, games) -> {
			if (games.isAccept())
				if (games instanceof GamesDistantManager)
					games.sendAll(cmd);
		});
	}

	private void startServer(final String serv) {
		if (Main.getInstance().getGames().containsKey(serv))
			Main.getInstance().getGames().get(serv).CreateServer();
	}

	private void stopServer(final String msg, final ReceiveMessageEvent e) {
		final String[] path = msg.split("/");
		final String games = path[path.length - 2];

		final int id = Integer.parseInt(path[path.length - 1]);

		if (Main.getInstance().getGames().containsKey(games))
			return;
		try {
			e.getUtils().ConsoleMessage("Unregistering " + games + " id: " + id);
			Thread.sleep(5 * 1000);
			e.getUtils().ConsoleMessage("Removing server's folder");
		} catch (final InterruptedException error) {
			error.printStackTrace();
		}

		Main.getInstance().getGames().get(games).RemoveServer(id);

		if (Main.getInstance().getGames().get(games).isRestart())
			Main.getInstance().getGames().get(games).CreateServer();
	}

}
