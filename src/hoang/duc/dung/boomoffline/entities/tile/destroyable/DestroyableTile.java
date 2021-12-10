package hoang.duc.dung.boomoffline.entities.tile.destroyable;

import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.boom.DirectionalExplosion;
import hoang.duc.dung.boomoffline.entities.tile.Tile;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class DestroyableTile extends Tile {
	protected boolean destroyed_ = false;
	protected int timeToDisappear_ = 30;
	protected Sprite belowSprite_; //default
	protected SpriteImage belowSpriteImage_; //default
	
	public DestroyableTile(int x, int y, Sprite sprite, SpriteImage image) {
		super(x, y, sprite, image);
		belowSprite_ = sprite;
		belowSpriteImage_ = image;
	}
	
	@Override
	public void update() {
		if(destroyed_) {
			if(timeToDisappear_ > 0) {
				timeToDisappear_--;
			}
			else {
				remove();
			}
			animate();
		}
	}

	@Override
	public boolean collide(Entity e) {

		if(e instanceof DirectionalExplosion)
			destroy();

		return false;
	}

	public boolean isDestroyed() {
		return destroyed_;
	}

	public void destroy() {
		destroyed_ = true;
	}

	public void addBelowSprite(Sprite sprite) {
		belowSprite_ = sprite;
	}

	public void addBelowSpriteImage(SpriteImage image) {
		belowSpriteImage_ = image;
	}
	
	protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
		int time = 30;
		int calc = animate_ % time;
		
		if (calc < 10) {
			return normal;
		}
			
		if (calc < 20) {
			return x1;
		}
			
		return x2;
	}

	protected SpriteImage movingSpriteImage(SpriteImage normal, SpriteImage x1) {
		int time = 10;
		int calc = animate_ % time;

		if (calc < 5) {
			return normal;
		}

		return x1;
	}
	
}
