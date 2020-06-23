package me.rhin.openciv.ui.window.type;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import me.rhin.openciv.asset.TextureEnum;
import me.rhin.openciv.game.city.City;
import me.rhin.openciv.ui.background.BlankBackground;
import me.rhin.openciv.ui.button.ButtonManager;
import me.rhin.openciv.ui.button.type.CityInfoCloseButton;
import me.rhin.openciv.ui.label.CustomLabel;
import me.rhin.openciv.ui.overlay.GameOverlay;
import me.rhin.openciv.ui.window.AbstractWindow;

public class CityInfoWindow extends AbstractWindow {

	public static final int WIDTH = 175;
	public static final int HEIGHT = 300;

	private ButtonManager buttonManager;
	private City city;
	private Sprite foodIcon, productionIcon, goldIcon, scienceIcon, heritageIcon;
	private CustomLabel foodDescLabel, productionDescLabel, goldDescLabel, scienceDescLabel, heritageDescLabel;
	private CustomLabel foodLabel, productionLabel, goldLabel, scienceLabel, heritageLabel;

	public CityInfoWindow(City city) {
		this.buttonManager = new ButtonManager(this);
		this.city = city;
		
		buttonManager.addButton(new CityInfoCloseButton(viewport.getWorldWidth() / 2 - 150 / 2,
				50, 150, 45));
		
		BlankBackground blankBackground = new BlankBackground(2,
				viewport.getWorldHeight() - (300 + GameOverlay.HEIGHT + 2), WIDTH, HEIGHT);
		addActor(blankBackground);

		float originX = 5;
		float originY = viewport.getWorldHeight() - (GameOverlay.HEIGHT * 2 + 2);

		this.foodIcon = TextureEnum.ICON_FOOD.sprite();
		foodIcon.setSize(16, 16);
		foodIcon.setPosition(originX, originY);

		this.foodDescLabel = new CustomLabel("Food:");
		originX += foodIcon.getWidth() + 5;
		foodDescLabel.setPosition(originX, originY + foodDescLabel.getHeight() / 2);
		addActor(foodDescLabel);

		this.foodLabel = new CustomLabel("+0");
		foodLabel.setPosition(WIDTH - (foodLabel.getWidth() + 2), originY + foodDescLabel.getHeight() / 2);
		addActor(foodLabel);

		originX = 5;

		this.productionIcon = TextureEnum.ICON_PRODUCTION.sprite();
		originY -= foodIcon.getHeight();
		productionIcon.setSize(16, 16);
		productionIcon.setPosition(originX, originY);

		this.productionDescLabel = new CustomLabel("Production:");
		originX += productionIcon.getWidth() + 5;
		productionDescLabel.setPosition(originX, originY + productionDescLabel.getHeight() / 2);
		addActor(productionDescLabel);

		this.productionLabel = new CustomLabel("+0");
		originX += productionIcon.getWidth() + 2;
		productionLabel.setPosition(WIDTH - (productionLabel.getWidth() + 2), originY + foodDescLabel.getHeight() / 2);
		addActor(productionLabel);

		originX = 5;

		this.goldIcon = TextureEnum.ICON_GOLD.sprite();
		originY -= foodIcon.getHeight();
		goldIcon.setSize(16, 16);
		goldIcon.setPosition(originX, originY);

		this.goldDescLabel = new CustomLabel("Gold:");
		originX += goldIcon.getWidth() + 5;
		goldDescLabel.setPosition(originX, originY + goldDescLabel.getHeight() / 2);
		addActor(goldDescLabel);

		this.goldLabel = new CustomLabel("+0");
		goldLabel.setPosition(WIDTH - (goldLabel.getWidth() + 2), originY + foodDescLabel.getHeight() / 2);
		addActor(goldLabel);

		originX = 5;

		this.scienceIcon = TextureEnum.ICON_RESEARCH.sprite();
		originY -= foodIcon.getHeight();
		scienceIcon.setSize(16, 16);
		scienceIcon.setPosition(originX, originY);

		this.scienceDescLabel = new CustomLabel("Gold:");
		originX += scienceIcon.getWidth() + 5;
		scienceDescLabel.setPosition(originX, originY + scienceDescLabel.getHeight() / 2);
		addActor(scienceDescLabel);

		this.scienceLabel = new CustomLabel("+0");
		scienceLabel.setPosition(WIDTH - (goldLabel.getWidth() + 2), originY + foodDescLabel.getHeight() / 2);
		addActor(scienceLabel);

		originX = 5;

		this.heritageIcon = TextureEnum.ICON_HERITAGE.sprite();
		originY -= foodIcon.getHeight();
		heritageIcon.setSize(16, 16);
		heritageIcon.setPosition(originX, originY);

		this.heritageDescLabel = new CustomLabel("Heritage:");
		originX += heritageIcon.getWidth() + 5;
		heritageDescLabel.setPosition(originX, originY + heritageDescLabel.getHeight() / 2);
		addActor(heritageDescLabel);

		this.heritageLabel = new CustomLabel("+0");
		heritageLabel.setPosition(WIDTH - (goldLabel.getWidth() + 2), originY + foodDescLabel.getHeight() / 2);
		addActor(heritageLabel);
	}

	@Override
	public void draw() {
		super.draw();
		Batch batch = getBatch();
		batch.begin();
		foodIcon.draw(batch);
		productionIcon.draw(batch);
		goldIcon.draw(batch);
		scienceIcon.draw(batch);
		heritageIcon.draw(batch);
		batch.end();
	}

	@Override
	public boolean disablesInput() {
		return false;
	}

	@Override
	public boolean closesOtherWindows() {
		return true;
	}

}
