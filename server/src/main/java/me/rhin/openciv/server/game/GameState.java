package me.rhin.openciv.server.game;

import java.util.ArrayList;

import org.java_websocket.WebSocket;

import com.badlogic.gdx.utils.Json;

import me.rhin.openciv.server.Server;
import me.rhin.openciv.server.game.ai.AIPlayer;
import me.rhin.openciv.server.game.ai.type.BarbarianPlayer;
import me.rhin.openciv.server.listener.SendChatMessageListener;
import me.rhin.openciv.shared.packet.type.SendChatMessagePacket;

public abstract class GameState implements SendChatMessageListener {

	protected ArrayList<Player> players;
	protected ArrayList<AIPlayer> aiPlayers;

	public GameState() {
		players = Server.getInstance().getPlayers();
		aiPlayers = Server.getInstance().getAIPlayers();

		Server.getInstance().getEventManager().addListener(SendChatMessageListener.class, this);
	}

	@Override
	public void onSendChatMessage(WebSocket conn, SendChatMessagePacket packet) {
		packet.setPlayerName(getPlayerByConn(conn).getName());

		if (packet.getMessage().startsWith("/")) {
			Server.getInstance().getCommandProcessor().proccessCommand(packet.getMessage().substring(1));
		}

		Json json = new Json();
		for (Player player : players) {
			player.sendPacket(json.toJson(packet));
		}
	}

	public abstract void onStateBegin();

	public abstract void onStateEnd();

	public abstract void stop();

	public abstract String toString();

	public BarbarianPlayer getBarbarianPlayer() {
		for (AIPlayer aiPlayer : aiPlayers)
			if (aiPlayer instanceof BarbarianPlayer)
				return (BarbarianPlayer) aiPlayer;

		return null;
	}

	public ArrayList<AIPlayer> getAIPlayers() {
		return aiPlayers;
	}

	public Player getPlayerByConn(WebSocket conn) {
		return Server.getInstance().getPlayerByConn(conn);
	}
}
