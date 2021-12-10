package hoang.duc.dung.boomoffline.entities.boom;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.AnimatedEntity;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Character;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.level.Coordinates;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.*;

public class Boom extends AnimatedEntity {
	protected double timeToExplode_ = 120; // Thời gian chờ bom nổ
	public int timeAfter_ = 20; // Thời gian vụ nổ biến mất
	
	protected Board board_;
	protected DirectionalExplosion[] explosions_ = null;
	protected boolean exploded_ = false; // Kiểm tra đã nổ
	protected boolean allowedToPassThrough_ = true; // Cho phép đi qua

	public Boom(int x, int y, Board board) {
		x_ = x;
		y_ = y;
		board_ = board;
		sprite_ = Sprite.boom;
		image_ = SpriteImage.images_boom[0];
	}
	
	@Override
	public void update() {
		if(timeToExplode_ > 0)
			timeToExplode_--;
		else {
			if(!exploded_)
				explosion();
			else
				updateExplosions();
			
			if(timeAfter_ > 0)
				timeAfter_--;
			else
				remove();
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(exploded_) {
			sprite_ =  Sprite.boom_exploded2;
			renderExplosions(screen);
		} else {
			sprite_ = Sprite.movingSprite(Sprite.boom, Sprite.boom_1, Sprite.boom_2, animate_, 60);
		}
		
		int xt = (int) x_ * Game.TILES_SIZE;
		int yt = (int) y_ * Game.TILES_SIZE;
		
		screen.renderEntity(xt, yt , this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		if (changeBoom_) {
			if (exploded_) {
				image_ =  SpriteImage.images_explosion_mid[0];
				drawExplosions(screen, graphics);
			} else {
				image_ = SpriteImage.movingImage(SpriteImage.images_boom_black, animate_, 60);
			}
		} else {
			if (exploded_) {
				image_ =  SpriteImage.images_explosion_mid[0];
				drawExplosions(screen, graphics);
			} else {
				image_ = SpriteImage.movingImage(SpriteImage.images_boom, animate_, 60);
			}
		}

		int xt = (int) x_ * Game.TILES_SIZE;
		int yt = (int) y_ * Game.TILES_SIZE;

		screen.drawEntity(xt, yt , this, graphics);
	}

	public void updateExplosions() {
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i].update();
		}
	}

	public void renderExplosions(Screen screen) {
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i].render(screen);
		}
	}

	public void drawExplosions(Screen screen, Graphics graphics) {
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i].draw(screen, graphics);
		}
	}

	public void explode() {
		timeToExplode_ = 0;
	}
	
	protected void explosion() {
		allowedToPassThrough_ = true;
		exploded_ = true;
		Sound.sound_boom_explosion.playSound();
		
		Character a = board_.getCharacterAt(x_, y_);
		if(a != null)  {
			a.kill();
		}
		
		explosions_ = new DirectionalExplosion[4];
		
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i] = new DirectionalExplosion((int) x_, (int) y_, i, Game.getBoomRadius(), board_);
		}
	}
	
	public Explosion explosionAt(int x, int y) {
		if(!exploded_)
			return null;
		
		for (int i = 0; i < explosions_.length; i++) {
			if(explosions_[i] == null)
				return null;

			Explosion e = explosions_[i].explosionAt(x, y);
			if(e != null)
				return e;
		}
		
		return null;
	}

	/**
	 *
	 * @param e thực thể có thể là player hoặc flame của quả bom mới nổ
	 */
	@Override
	public boolean collide(Entity e) {
		// xử lý khi Player đi ra sau khi vừa đặt bom (_allowedToPassThroght)
		if(e instanceof Player) {
			double diffX = e.getX() - Coordinates.tileToPixel(getX());
			double diffY = e.getY() - Coordinates.tileToPixel(getY());
			
			if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) { // differences to see if the player has moved out of the boom, tested values
				allowedToPassThrough_ = false;
			}
			
			return allowedToPassThrough_;
		}
		
		if(e instanceof DirectionalExplosion) {
			explode();
			return true;
		}

		if(e instanceof Boom) {
			if (((Boom) e).isExploded() == true) {
				this.timeToExplode_ = 0;
				return true;
			}
		}
		
		return false;
	}

	// 2 hàm cho AI
	@Override
	public int getXTile() {
		return (int) this.x_;
	}

	@Override
	public int getYTile() {
		return (int) this.y_;
	}

	public boolean isExploded() {
		return exploded_;
	}

	public boolean isAllowedToPassThrough() {
		return allowedToPassThrough_;
	}

}
