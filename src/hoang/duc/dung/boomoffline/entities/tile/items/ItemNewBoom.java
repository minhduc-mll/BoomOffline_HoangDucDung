package hoang.duc.dung.boomoffline.entities.tile.items;

import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.*;

public class ItemNewBoom extends Item {
	public ItemNewBoom(int x, int y, int level, Sprite sprite, SpriteImage image) {
		super(x, y, level, sprite, image);
	}

	@Override
	public void update() {
		animate();
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		image_ = SpriteImage.movingImage(SpriteImage.images_boom_black, animate_, 60);
		super.draw(screen, graphics);
	}

	@Override
	public boolean collide(Entity e) {

		if(e instanceof Player) {
			Sound.sound_item.playSound();

			((Player) e).addItem(this);
			remove();
			return true;
		}

		return true;
	}

	@Override
	public void setValues() {
		active_ = true;
		changeBoom_ = true;
	}

}
