package me.rhin.openciv.game.research.type;

import com.badlogic.gdx.graphics.g2d.Sprite;

import me.rhin.openciv.asset.TextureEnum;
import me.rhin.openciv.game.research.ResearchTree;
import me.rhin.openciv.game.research.Technology;
import me.rhin.openciv.game.research.TreePosition;

public class MathematicsTech extends Technology {

	public MathematicsTech(ResearchTree researchTree) {
		super(researchTree, new TreePosition(2, 3));
		
		requiredTechs.add(WheelTech.class);
	}

	@Override
	public int getScienceCost() {
		return 105;
	}

	@Override
	public String getName() {
		return "Mathematics";
	}

	@Override
	public Sprite getIcon() {
		return TextureEnum.ICON_MATHEMATICS.sprite();
	}

	@Override
	public String getDesc() {
		return "- Unlocks catapult\n" + "- Unlocks hanging gardens\n" + "- Unlocks courthouse";
	}

}
