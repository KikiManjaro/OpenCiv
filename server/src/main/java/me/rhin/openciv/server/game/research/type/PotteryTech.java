package me.rhin.openciv.server.game.research.type;

import me.rhin.openciv.server.game.research.TechProperty;
import me.rhin.openciv.server.game.research.ResearchTree;
import me.rhin.openciv.server.game.research.Technology;

public class PotteryTech extends Technology {

	public PotteryTech(ResearchTree researchTree) {
		super(researchTree, TechProperty.FOOD, TechProperty.FAITH);
	}

	@Override
	public int getScienceCost() {
		return 35;
	}

	@Override
	public String getName() {
		return "Pottery";
	}
}
