package me.rhin.openciv.server.game.map.tile;

import me.rhin.openciv.shared.stat.Stat;
import me.rhin.openciv.shared.stat.StatLine;

public enum TileType implements Comparable<TileType> {

	CITY(TileLayer.TOP) {
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			return statLine;
		}
	},
	GRASS(TileLayer.BASE) {
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 2);
			return statLine;
		}
	},
	GRASS_HILL(TileLayer.BASE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.PRODUCTION_GAIN, 2);
			return statLine;
		}

		@Override
		public int getMovementCost() {
			return 2;
		}
	},
	PLAINS(TileLayer.BASE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 1);
			statLine.setValue(Stat.PRODUCTION_GAIN, 1);
			return statLine;
		}
	},
	PLAINS_HILL(TileLayer.BASE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.PRODUCTION_GAIN, 2);
			return statLine;
		}

		@Override
		public int getMovementCost() {
			return 2;
		}
	},
	OCEAN(TileLayer.BASE, TileProperty.WATER) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 1);
			return statLine;
		}
	},
	SHALLOW_OCEAN(TileLayer.BASE, TileProperty.WATER) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 2);
			return statLine;
		}
	},
	MOUNTAIN(TileLayer.MIDDLE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			return statLine;
		}

		@Override
		public int getMovementCost() {
			return 1000000;
		}
	},
	FOREST(TileLayer.MIDDLE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 1);
			statLine.setValue(Stat.PRODUCTION_GAIN, 1);
			return statLine;
		}

		@Override
		public int getMovementCost() {
			return 2;
		}
	},
	JUNGLE(TileLayer.MIDDLE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.FOOD_GAIN, 2);
			return statLine;
		}

		@Override
		public int getMovementCost() {
			return 2;
		}
	},
	HORSES(TileLayer.HIGH, TileProperty.RESOURCE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.PRODUCTION_GAIN, 1);
			return statLine;
		}
	},
	IRON(TileLayer.HIGH, TileProperty.RESOURCE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.PRODUCTION_GAIN, 1);
			return statLine;
		}
	},
	COPPER(TileLayer.HIGH, TileProperty.RESOURCE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.GOLD_GAIN, 2);
			return statLine;
		}
	},
	COTTON(TileLayer.HIGH, TileProperty.RESOURCE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.GOLD_GAIN, 2);
			return statLine;
		}
	},
	GEMS(TileLayer.HIGH, TileProperty.RESOURCE) {
		@Override
		public StatLine getStatLine() {
			StatLine statLine = new StatLine();
			statLine.setValue(Stat.GOLD_GAIN, 3);
			return statLine;
		}
	};

	public enum TileLayer {
		BASE, MIDDLE, HIGH, TOP;
	}

	public enum TileProperty {
		WATER, RESOURCE;
	}

	private TileLayer tileLayer;
	private TileProperty[] tileProperties;

	TileType(TileLayer tileLayer, TileProperty... tileProperties) {
		this.tileLayer = tileLayer;
		this.tileProperties = tileProperties;
	}

	public abstract StatLine getStatLine();

	public int getMovementCost() {
		return 1;
	}

	public int getID() {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].equals(this))
				return i;
		}

		return -1;
	}

	public TileLayer getTileLayer() {
		return tileLayer;
	}

	public TileProperty[] getProperties() {
		return tileProperties;
	}

	public boolean hasProperty(TileProperty targetProperty) {
		if (tileProperties == null)
			return false;

		for (TileProperty tileProperty : tileProperties)
			if (tileProperty == targetProperty)
				return true;

		return false;
	}
}