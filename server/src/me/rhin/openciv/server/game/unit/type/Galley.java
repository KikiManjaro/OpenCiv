package me.rhin.openciv.server.game.unit.type;

import me.rhin.openciv.server.game.Player;
import me.rhin.openciv.server.game.city.City;
import me.rhin.openciv.server.game.map.tile.Tile;
import me.rhin.openciv.server.game.map.tile.TileType.TileProperty;
import me.rhin.openciv.server.game.unit.Unit;
import me.rhin.openciv.server.game.unit.UnitItem;

public class Galley extends UnitItem {

	public static class GalleyUnit extends Unit {

		public GalleyUnit(Player playerOwner, Tile standingTile) {
			super(playerOwner, standingTile);
		}

		@Override
		public int getMovementCost(Tile tile) {
			if (!tile.getTileType().hasProperty(TileProperty.WATER))
				return 1000000;
			else
				return tile.getTileType().getMovementCost();
		}
	}

	@Override
	public int getProductionCost() {
		return 45;
	}

	@Override
	public boolean meetsProductionRequirements() {
		return true;
	}
}
