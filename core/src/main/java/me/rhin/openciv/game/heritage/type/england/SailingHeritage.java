package me.rhin.openciv.game.heritage.type.england;

import com.badlogic.gdx.graphics.g2d.Sprite;

import me.rhin.openciv.asset.TextureEnum;
import me.rhin.openciv.game.heritage.Heritage;

public class SailingHeritage extends Heritage {

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public String getName() {
		return "Ocean Superiority";
	}

	@Override
	public Sprite getIcon() {
		return TextureEnum.UNIT_GALLEY.sprite();
	}

	@Override
	public float getCost() {
		return 20;
	}

	@Override
	public String getDesc() {
		return "20% Production bonus to naval\nunits.";
	}

	@Override
	protected void onStudied() {
	}
}