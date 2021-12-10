package hoang.duc.dung.boomoffline.entities;

import hoang.duc.dung.boomoffline.graphics.IRender;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.level.Coordinates;

import java.awt.*;

public abstract class Entity implements IRender {

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;

	protected double x_, y_;
	protected boolean removed_ = false;
	protected static boolean changeBoom_ = false;
	protected Sprite sprite_;
	protected SpriteImage image_;
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);

	@Override
	public abstract void draw(Screen screen, Graphics graphics);

	public abstract boolean collide(Entity e);

	public void remove() {
		removed_ = true;
	}

	public boolean isRemoved() {
		return removed_;
	}

	public Sprite getSprite() {
		return sprite_;
	}

	public SpriteImage getSpriteImage() {
		return image_;
	}

	public double getX() {
		return x_;
	}
	
	public double getY() {
		return y_;
	}
	
	public int getXTile() {
		return Coordinates.pixelToTile(x_ + sprite_.SIZE * 0.5); //plus half block for precision
	}
	
	public int getYTile() {
		return Coordinates.pixelToTile(y_ - sprite_.SIZE * 0.5); //plus half block
	}
}
