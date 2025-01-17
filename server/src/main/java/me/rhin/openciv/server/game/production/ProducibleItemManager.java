package me.rhin.openciv.server.game.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.utils.Json;

import me.rhin.openciv.server.Server;
import me.rhin.openciv.server.game.city.City;
import me.rhin.openciv.server.game.city.building.Building;
import me.rhin.openciv.server.game.city.building.type.Amphitheater;
import me.rhin.openciv.server.game.city.building.type.Aqueduct;
import me.rhin.openciv.server.game.city.building.type.Chapel;
import me.rhin.openciv.server.game.city.building.type.Circus;
import me.rhin.openciv.server.game.city.building.type.Colosseum;
import me.rhin.openciv.server.game.city.building.type.Colossus;
import me.rhin.openciv.server.game.city.building.type.Forge;
import me.rhin.openciv.server.game.city.building.type.Garden;
import me.rhin.openciv.server.game.city.building.type.Granary;
import me.rhin.openciv.server.game.city.building.type.GreatLibrary;
import me.rhin.openciv.server.game.city.building.type.GreatPyramids;
import me.rhin.openciv.server.game.city.building.type.HangingGardens;
import me.rhin.openciv.server.game.city.building.type.Library;
import me.rhin.openciv.server.game.city.building.type.Lighthouse;
import me.rhin.openciv.server.game.city.building.type.MachuPicchu;
import me.rhin.openciv.server.game.city.building.type.Market;
import me.rhin.openciv.server.game.city.building.type.Mint;
import me.rhin.openciv.server.game.city.building.type.Monument;
import me.rhin.openciv.server.game.city.building.type.NationalCollege;
import me.rhin.openciv.server.game.city.building.type.Shrine;
import me.rhin.openciv.server.game.city.building.type.Stables;
import me.rhin.openciv.server.game.city.building.type.StatueOfAres;
import me.rhin.openciv.server.game.city.building.type.Stonehenge;
import me.rhin.openciv.server.game.city.building.type.Stoneworks;
import me.rhin.openciv.server.game.city.building.type.Walls;
import me.rhin.openciv.server.game.city.building.type.WaterMill;
import me.rhin.openciv.server.game.city.building.type.Workshop;
import me.rhin.openciv.server.game.unit.type.Archer;
import me.rhin.openciv.server.game.unit.type.Builder;
import me.rhin.openciv.server.game.unit.type.Caravan;
import me.rhin.openciv.server.game.unit.type.CargoShip;
import me.rhin.openciv.server.game.unit.type.Catapult;
import me.rhin.openciv.server.game.unit.type.ChariotArcher;
import me.rhin.openciv.server.game.unit.type.Crossbowman;
import me.rhin.openciv.server.game.unit.type.Galley;
import me.rhin.openciv.server.game.unit.type.Horseman;
import me.rhin.openciv.server.game.unit.type.Legion;
import me.rhin.openciv.server.game.unit.type.Pikeman;
import me.rhin.openciv.server.game.unit.type.Scout;
import me.rhin.openciv.server.game.unit.type.Settler;
import me.rhin.openciv.server.game.unit.type.Spearman;
import me.rhin.openciv.server.game.unit.type.Swordsman;
import me.rhin.openciv.server.game.unit.type.Warrior;
import me.rhin.openciv.server.game.unit.type.WorkBoat;
import me.rhin.openciv.server.listener.NextTurnListener;
import me.rhin.openciv.shared.packet.type.ApplyProductionToItemPacket;
import me.rhin.openciv.shared.packet.type.BuyProductionItemPacket;
import me.rhin.openciv.shared.packet.type.FinishProductionItemPacket;
import me.rhin.openciv.shared.stat.Stat;

/**
 * Manager that handles the available items to be produced by cities. Producible
 * items are determined by whether the ProductionItem interface meets it's
 * production requirements.
 * 
 * @author Ryan
 *
 */

//FIXME: Remove modifiers if this city is captured.

public class ProducibleItemManager implements NextTurnListener {

	private City city;
	private HashMap<String, ProductionItem> possibleItems;

	// Items that the city has already applied production to
	private HashMap<String, ProducingItem> appliedProductionItems;

	// A queue of items to produce
	private Queue<ProducingItem> itemQueue;
	private boolean queueEnabled;

	public ProducibleItemManager(City city) {
		this.city = city;
		this.possibleItems = new HashMap<>();
		this.appliedProductionItems = new HashMap<>();
		this.itemQueue = new LinkedList<>();
		this.queueEnabled = false;

		// Units
		possibleItems.put("Warrior", new Warrior(city));
		possibleItems.put("Settler", new Settler(city));
		possibleItems.put("Scout", new Scout(city));
		possibleItems.put("Galley", new Galley(city));
		possibleItems.put("Builder", new Builder(city));
		possibleItems.put("Work Boat", new WorkBoat(city));
		possibleItems.put("Archer", new Archer(city));
		possibleItems.put("Chariot Archer", new ChariotArcher(city));
		possibleItems.put("Catapult", new Catapult(city));
		possibleItems.put("Spearman", new Spearman(city));
		possibleItems.put("Caravan", new Caravan(city));
		possibleItems.put("Swordsman", new Swordsman(city));
		possibleItems.put("Legion", new Legion(city));
		possibleItems.put("Horseman", new Horseman(city));
		possibleItems.put("Cargo Ship", new CargoShip(city));
		possibleItems.put("Pikeman", new Pikeman(city));
		possibleItems.put("Crossbowman", new Crossbowman(city));

		// Buildings
		possibleItems.put("Granary", new Granary(city));
		possibleItems.put("Monument", new Monument(city));
		possibleItems.put("Market", new Market(city));
		possibleItems.put("Library", new Library(city));
		possibleItems.put("Water Mill", new WaterMill(city));
		possibleItems.put("Walls", new Walls(city));
		possibleItems.put("Shrine", new Shrine(city));
		possibleItems.put("Circus", new Circus(city));
		possibleItems.put("Colosseum", new Colosseum(city));
		possibleItems.put("Stables", new Stables(city));
		possibleItems.put("National College", new NationalCollege(city));
		possibleItems.put("Lighthouse", new Lighthouse(city));
		possibleItems.put("Stoneworks", new Stoneworks(city));
		possibleItems.put("Forge", new Forge(city));
		possibleItems.put("Workshop", new Workshop(city));
		possibleItems.put("Aqueduct", new Aqueduct(city));
		possibleItems.put("Mint", new Mint(city));
		possibleItems.put("Amphitheater", new Amphitheater(city));
		possibleItems.put("Garden", new Garden(city));
		possibleItems.put("Chapel", new Chapel(city));

		// Wonders
		possibleItems.put("Great Pyramids", new GreatPyramids(city));
		possibleItems.put("Great Library", new GreatLibrary(city));
		possibleItems.put("Hanging Gardens", new HangingGardens(city));
		possibleItems.put("Statue Of Ares", new StatueOfAres(city));
		possibleItems.put("Stonehenge", new Stonehenge(city));
		possibleItems.put("Machu Picchu", new MachuPicchu(city));
		possibleItems.put("Colossus", new Colossus(city));

		Server.getInstance().getEventManager().addListener(NextTurnListener.class, this);
	}

	public HashMap<String, ProductionItem> getPossibleItems() {
		return possibleItems;
	}

	public ArrayList<ProductionItem> getProducibleItems() {
		ArrayList<ProductionItem> producibleItems = new ArrayList<>();

		// FIXME: I believe this returns items that DONT meet production requirements
		for (ProductionItem item : possibleItems.values()) {
			if (item.meetsProductionRequirements())
				producibleItems.add(item);
		}

		return producibleItems;
	}

	public void setProducingItem(String itemName) {

		if (possibleItems.get(itemName) == null) {
			throw new NullPointerException();
		}
		// Prevent buildings being added twice in the queue
		if (possibleItems.get(itemName) instanceof Building) {
			for (ProducingItem producingItem : itemQueue) {
				if (producingItem.getProductionItem().getName().equals(itemName))
					return;
			}
		}

		if (!queueEnabled) {
			// TODO: Implement appliedProductionItems here to save applied production.
			itemQueue.clear();
		}

		// System.out.println("Adding to queue");
		itemQueue.add(new ProducingItem(possibleItems.get(itemName)));
	}

	public void buyProducingItem(String itemName) {
		if (possibleItems.get(itemName) == null)
			return;

		ProducingItem item = new ProducingItem(possibleItems.get(itemName));

		if (city.getPlayerOwner().getStatLine().getStatValue(Stat.GOLD) < item.getProductionItem().getGoldCost())
			return;

		city.getPlayerOwner().getStatLine().subValue(Stat.GOLD, item.getProductionItem().getGoldCost());
		item.getProductionItem().create();

		Json json = new Json();

		BuyProductionItemPacket packet = new BuyProductionItemPacket();
		packet.setProductionItem(city.getName(), item.getProductionItem().getName());
		city.getPlayerOwner().sendPacket(json.toJson(packet));

		city.updateWorkedTiles();
		city.getPlayerOwner().updateOwnedStatlines(false);

		if (itemQueue.peek() != null && itemQueue.peek().getProductionItem().equals(item.getProductionItem())
				&& item.getProductionItem() instanceof Building) {
			clearProducingItem();
		}
	}

	@Override
	public void onNextTurn() {
		ProducingItem producingItem = itemQueue.peek();

		if (producingItem == null)
			return;

		Json json = new Json();

		if (!producingItem.getProductionItem().meetsProductionRequirements()) {
			itemQueue.remove();

			FinishProductionItemPacket packet = new FinishProductionItemPacket();
			packet.setCityName(city.getName());
			city.getPlayerOwner().sendPacket(json.toJson(packet));

			return;
		}

		producingItem.applyProduction(city.getStatLine().getStatValue(Stat.PRODUCTION_GAIN));

		if (producingItem.getAppliedProduction() >= producingItem.getProductionItem().getProductionCost()) {
			itemQueue.remove();

			// FIXME: Not only should this apply to the queue. We should save the leftover
			// production if the queue is not enabled.
			if (itemQueue.peek() != null) {
				itemQueue.peek().applyProduction(itemQueue.peek().getAppliedProduction()
						- producingItem.getProductionItem().getProductionCost());
			}

			producingItem.getProductionItem().create();

			FinishProductionItemPacket packet = new FinishProductionItemPacket();
			packet.setProductionItem(city.getName(), producingItem.getProductionItem().getName());
			city.getPlayerOwner().sendPacket(json.toJson(packet));

			city.updateWorkedTiles();
			city.getPlayerOwner().updateOwnedStatlines(false);
			return;
		}

		ApplyProductionToItemPacket packet = new ApplyProductionToItemPacket();
		packet.setProductionItem(city.getName(), producingItem.getProductionItem().getName(),
				city.getStatLine().getStatValue(Stat.PRODUCTION_GAIN));
		city.getPlayerOwner().sendPacket(json.toJson(packet));
	}

	public void clearProducingItem() {
		itemQueue.clear();
	}

	public boolean canProduce(String itemName) {
		if (possibleItems.get(itemName) == null)
			return false;
		return possibleItems.get(itemName).meetsProductionRequirements();
	}

	public boolean isProducingItem() {
		return itemQueue.size() > 0;
	}

	public ProducingItem getProducingItem() {
		return itemQueue.peek();
	}
}
