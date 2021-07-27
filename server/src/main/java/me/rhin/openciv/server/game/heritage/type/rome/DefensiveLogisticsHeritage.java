package me.rhin.openciv.server.game.heritage.type.rome;

import me.rhin.openciv.server.game.Player;
import me.rhin.openciv.server.game.heritage.Heritage;

public class DefensiveLogisticsHeritage extends Heritage {

	public DefensiveLogisticsHeritage(Player player) {
		super(player);
	}

	@Override
	public int getLevel() {
		return 2;
	}

	@Override
	public String getName() {
		return "Defensive Logistics";
	}

	@Override
	public float getCost() {
		return 60;
	}

	@Override
	protected void onStudied() {
		// TODO: No idea.
	}
}