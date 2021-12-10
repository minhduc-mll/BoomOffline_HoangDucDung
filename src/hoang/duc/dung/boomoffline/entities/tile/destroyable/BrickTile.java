package hoang.duc.dung.boomoffline.entities.tile.destroyable;

import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.boom.DirectionalExplosion;
import hoang.duc.dung.boomoffline.entities.character.enemy.Ghost;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.level.Coordinates;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.*;
import java.util.ArrayList;

public class BrickTile extends DestroyableTile {
	private SpriteImage curImage;

	public static ArrayList<Integer> xTileBroken_ = new ArrayList();
	public static ArrayList<Integer> yTileBroken_ = new ArrayList();
	
	public BrickTile(int x, int y, Sprite sprite, SpriteImage image) {
		super(x, y, sprite, image);
		curImage = image;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		int x = Coordinates.tileToPixel(x_);
		int y = Coordinates.tileToPixel(y_);
		
		if(destroyed_) {
			sprite_ = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
			
			screen.renderEntityWithBelowSprite(x, y, this, belowSprite_);
		}
		else
			screen.renderEntity( x, y, this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		int x = Coordinates.tileToPixel(x_);
		int y = Coordinates.tileToPixel(y_);

		if(destroyed_) {
			image_ = movingSpriteImage(belowSpriteImage_, curImage);

			screen.drawEntityWithBelowSprite(x, y, this, image_, graphics);
		}
		else
			screen.drawEntity( x, y, this, graphics);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof DirectionalExplosion) {
			Sound.sound_brick_destroy.playSound();

			addXTileBroken((int) x_);
			addYTileBroken((int) y_);

			destroy();
		}
		
		if(e instanceof Ghost) {
			return true;
		}
			
		return false;
	}

	public static void addXTileBroken(int x) {
		xTileBroken_.add(x);
	}

	public static void addYTileBroken(int y) {
		yTileBroken_.add(y);
	}
}
