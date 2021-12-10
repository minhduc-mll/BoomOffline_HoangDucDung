package hoang.duc.dung.boomoffline.entities.tile.items;

import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.sound.Sound;

public class ItemFlames extends Item {

	public ItemFlames(int x, int y, int level, Sprite sprite, SpriteImage image) {
		super(x, y, level, sprite, image);
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
		Game.addBoomRadius(1);
	}
	


}
