package hoang.duc.dung.boomoffline.entities.tile;

import hoang.duc.dung.boomoffline.entities.AnimatedEntity;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.level.Coordinates;

import java.awt.*;

public abstract class Tile extends AnimatedEntity {
	
	public Tile(int x, int y, Sprite sprite, SpriteImage image) {
		x_ = x;
		y_ = y;
		sprite_ = sprite;
		image_ = image;
	}

	@Override
	public void update() {}

	@Override
	public boolean collide(Entity e) {
		return false;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity( Coordinates.tileToPixel(x_), Coordinates.tileToPixel(y_), this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		screen.drawEntity( Coordinates.tileToPixel(x_), Coordinates.tileToPixel(y_), this, graphics);
	}
}
