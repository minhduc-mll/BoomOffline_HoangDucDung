package hoang.duc.dung.boomoffline.entities.tile;


import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class GrassTile extends Tile {

	public GrassTile(int x, int y, Sprite sprite, SpriteImage image) {
		super(x, y, sprite, image);
	}
	
	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
