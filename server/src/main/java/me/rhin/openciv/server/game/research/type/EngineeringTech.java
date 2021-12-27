package me.rhin.openciv.server.game.research.type;

import me.rhin.openciv.server.game.research.ResearchTree;
import me.rhin.openciv.server.game.research.TechProperty;
import me.rhin.openciv.server.game.research.Technology;
import me.rhin.openciv.shared.stat.Stat;

public class EngineeringTech extends Technology {

	public EngineeringTech(ResearchTree researchTree) {
		super(researchTree, TechProperty.FOOD, TechProperty.GOLD);

		requiredTechs.add(MathematicsTech.class);
		requiredTechs.add(ConstructionTech.class);
	}

	@Override
	public void onResearched() {
		researchTree.getPlayerOwner().getStatLine().addValue(Stat.MAX_TRADE_ROUTES, 1);
		researchTree.getPlayerOwner().updateOwnedStatlines(false);
	}

	@Override
	public int getScienceCost() {
		return 175;
	}

	@Override
	public String getName() {
		return "Engineering";
	}

}