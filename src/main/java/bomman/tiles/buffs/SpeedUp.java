package bomman.tiles.buffs;

import bomman.MainGame;
import bomman.entity.MainCharacter;
import bomman.manager.Sprite;
import bomman.tiles.CommonTiles;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Speed the character up.
 */
public class SpeedUp extends Buff {

	public static boolean isExecuting = false;

	public static void executeBuff(MainCharacter mainCharacter) {
		isExecuting = true;
		mainCharacter.setCharacterVelocity(mainCharacter.getCharacterVelocity() * 4);
	}

	public SpeedUp(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.powerup_speed.getFxImage());
		this.setCoolDown(100);
	}

	@Override
	public void update() {
		this.setCoolDown(this.getCoolDown() - 1);
		if (this.getCoolDown() == 0) {
			this.setCoolDown(0);
			MainCharacter.setCharacterVelocity(MainCharacter.getCharacterVelocity() / 4);
			this.isExecuting = false;
		}
		if (this.getCoolDown() < 0) {
			this.setCoolDown(0);
		}
	}
}
