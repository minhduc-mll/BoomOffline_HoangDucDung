package hoang.duc.dung.boomoffline.entities.boom;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.AnimatedEntity;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Character;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

import java.awt.*;


public class Explosion extends AnimatedEntity {

	protected boolean last_ = false;
	protected Board board_;

	public Explosion(int x, int y, int direction, boolean last, Board board) {
		x_ = x;
		y_ = y;
		last_ = last;
		board_ = board;
		
		switch (direction) {
			case 0:
				if(!last) {
					sprite_ = Sprite.explosion_vertical2;
					image_ = SpriteImage.images_explosion_vertical_up[0];
				} else {
					sprite_ = Sprite.explosion_vertical_top_last2;
					image_ = SpriteImage.images_explosion_vertical_up[1];
				}
			break;
			case 1:
				if(!last) {
					sprite_ = Sprite.explosion_horizontal2;
					image_ = SpriteImage.images_explosion_horizontal_right[0];
				} else {
					sprite_ = Sprite.explosion_horizontal_right_last2;
					image_ = SpriteImage.images_explosion_horizontal_right[1];
				}
				break;
			case 2:
				if(!last) {
					sprite_ = Sprite.explosion_vertical2;
					image_ = SpriteImage.images_explosion_vertical_down[0];
				} else {
					sprite_ = Sprite.explosion_vertical_down_last2;
					image_ = SpriteImage.images_explosion_vertical_down[1];
				}
				break;
			case 3: 
				if(!last) {
					sprite_ = Sprite.explosion_horizontal2;
					image_ = SpriteImage.images_explosion_horizontal_left[0];
				} else {
					sprite_ = Sprite.explosion_horizontal_left_last2;
					image_ = SpriteImage.images_explosion_horizontal_left[1];
				}
				break;
		}
	}

	@Override
	public void update() {}

	@Override
	public void render(Screen screen) {
		int xt = (int) x_ * Game.TILES_SIZE;
		int yt = (int) y_ * Game.TILES_SIZE;

		screen.renderEntity(xt, yt , this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		int xt = (int) x_ * Game.TILES_SIZE;
		int yt = (int) y_ * Game.TILES_SIZE;

		screen.drawEntity(xt, yt , this, graphics);
	}

	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Character) {
			((Character)e).kill();
		}
		
		return true;
	}
	

}