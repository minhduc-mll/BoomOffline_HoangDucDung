package hoang.duc.dung.boomoffline.entities.tile.items;

import hoang.duc.dung.boomoffline.entities.tile.Tile;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public abstract class Item extends Tile {

	protected int duration_ = -1; // -1 is infinite, duration in lifes
	protected boolean active_ = false;
	protected int level_;
	
	public Item(int x, int y, int level, Sprite sprite, SpriteImage image) {
		super(x, y, sprite, image);
		level_ = level;
	}
	
	public abstract void setValues();
	
	public void removeLive() {
		if(duration_ > 0)
			duration_--;
		
		if(duration_ == 0)
			active_ = false;
	}
	
	public int getDuration() {
		return duration_;
	}
	
	public int getLevel() {
		return level_;
	}

	public void setDuration(int duration) {
		this.duration_ = duration;
	}

	public boolean isActive() {
		return active_;
	}

	public void setActive(boolean active) {
		this.active_ = active;
	}
}
