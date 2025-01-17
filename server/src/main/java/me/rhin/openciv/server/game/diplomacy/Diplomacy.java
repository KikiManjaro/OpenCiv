package me.rhin.openciv.server.game.diplomacy;

import java.util.ArrayList;

import org.java_websocket.WebSocket;

import com.badlogic.gdx.utils.Json;

import me.rhin.openciv.server.Server;
import me.rhin.openciv.server.game.AbstractPlayer;
import me.rhin.openciv.server.game.Player;
import me.rhin.openciv.server.listener.DeclareWarListener;
import me.rhin.openciv.shared.packet.type.DeclareWarPacket;

public class Diplomacy implements DeclareWarListener {

	private AbstractPlayer player;

	private ArrayList<AbstractPlayer> enemies;
	private ArrayList<AbstractPlayer> allies;

	public Diplomacy(AbstractPlayer player) {
		this.player = player;

		this.enemies = new ArrayList<>();
		this.allies = new ArrayList<>();

		Server.getInstance().getEventManager().addListener(DeclareWarListener.class, this);
	}

	@Override
	public void onDeclareWar(WebSocket conn, DeclareWarPacket packet) {

		AbstractPlayer attacker = Server.getInstance().getPlayerByConn(conn);

		if (!attacker.equals(player))
			return;

		AbstractPlayer defender = null;

		for (AbstractPlayer player : Server.getInstance().getAbstractPlayers()) {
			if (player.getName().equals(packet.getDefender()))
				defender = player;
		}

		declareWar(defender);

		Json json = new Json();
		for (Player player : Server.getInstance().getPlayers()) {
			player.sendPacket(json.toJson(packet));
		}
	}

	@Override
	public String toString() {
		String output = "";
		for (AbstractPlayer player : enemies) {
			output += "WAR: " + player.getName() + "\n";
		}

		return output;
	}

	public void declareWar(AbstractPlayer targetPlayer) {
		System.out.println(player.getName() + " declared war on " + targetPlayer.getName());

		addEnemy(targetPlayer);
		targetPlayer.getDiplomacy().addEnemy(player);
	}

	public void declarWarAll() {
		for (AbstractPlayer otherPlayer : Server.getInstance().getAbstractPlayers()) {
			if (player.equals(otherPlayer))
				continue;

			declareWar(otherPlayer);
		}
	}

	public void addEnemy(AbstractPlayer otherPlayer) {
		enemies.add(otherPlayer);
	}

	public boolean atWar(AbstractPlayer otherPlayer) {
		return enemies.contains(otherPlayer);
	}

}
