package hoang.duc.dung.boomoffline.entities.character.enemy;

import java.awt.*;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.Message;
import hoang.duc.dung.boomoffline.entities.boom.DirectionalExplosion;
import hoang.duc.dung.boomoffline.entities.character.Character;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AI;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.level.Coordinates;
import hoang.duc.dung.boomoffline.sound.Sound;

public abstract class Enemy extends Character {

	protected int points_;
	
	protected double speed_; //Speed should change on level transition
	protected AI ai_;
	
	//necessary to correct move
	protected final double MAX_STEPS;
	protected final double REST;
	protected double steps_;
	
	protected int finalAnimation_ = 30;
	protected Sprite deadSprite_;
	protected SpriteImage[] deadSpriteImage_;
	
	public Enemy(int x, int y, Board board, Sprite dead, SpriteImage[] deadImage, double speed, int points) {
		super(x, y, board);
		
		points_ = points;
		speed_ = speed;
		
		MAX_STEPS = Game.TILES_SIZE / speed_;
		REST = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
		steps_ = MAX_STEPS;
		
		timeAfter_ = 20;
		deadSprite_ = dead;
		deadSpriteImage_ = deadImage;
	}
	
	/*
	|--------------------------------------------------------------------------
	| Character Update, Render and Draw
	|--------------------------------------------------------------------------
	 */
	@Override
	public void update() {
		animate();
		
		if(alive_ == false) {
			afterKill();
			return;
		}
		
		if(alive_)
			calculateMove();
	}
	
	@Override
	public void render(Screen screen) {
		
		if(alive_)
			chooseSprite();
		else {
			if(timeAfter_ > 0) {
				sprite_ = deadSprite_;
				animate_ = 0;
			} else {
				sprite_ = Sprite.movingSprite(Sprite.character_dead1, Sprite.character_dead2, Sprite.character_dead3, animate_, 60);
			}
				
		}
			
		screen.renderEntity((int) x_, (int) y_ - sprite_.SIZE, this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {

		if(alive_)
			chooseSprite();
		else {
			if(timeAfter_ > 0) {
				image_ = deadSpriteImage_[0];
				animate_ = 0;
			} else {
				sprite_ = Sprite.movingSprite(Sprite.character_dead1, Sprite.character_dead2, Sprite.character_dead3, animate_, 60);
				image_ = SpriteImage.movingImage(deadSpriteImage_, animate_, 60);
			}

		}

		screen.drawEntity((int) x_, (int) y_ - sprite_.SIZE, this, graphics);
	}
	
	/*
	|--------------------------------------------------------------------------
	| Character Move
	|--------------------------------------------------------------------------
	 */
	@Override
	public void calculateMove() {
		int xa = 0, ya = 0;
		if(steps_ <= 0){
			direction_ = ai_.calculateDirection();
			steps_ = MAX_STEPS;
		}
			
		if(direction_ == 0) ya--;
		if(direction_ == 2) ya++;
		if(direction_ == 3) xa--;
		if(direction_ == 1) xa++;
		
		if(canMove(xa, ya)) {
			steps_ -= 1 + REST;
			move(xa * speed_, ya * speed_);
			moving_ = true;
		} else {
			steps_ = 0;
			moving_ = false;
		}
	}
	
	@Override
	public void move(double xa, double ya) {
		if(!alive_) return;

			y_ += ya;
			x_ += xa;
	}
	
	@Override
	public boolean canMove(double x, double y) {
		
		double xr = x_, yr = y_ - 16; //subtract y to get more accurate results
		
		//the thing is, subract 15 to 16 (sprite size), so if we add 1 tile we get the next pixel tile with this
		//we avoid the shaking inside tiles with the help of steps
		if(direction_ == 0) { yr += sprite_.getSize() -1 ; xr += sprite_.getSize()/2; }
		if(direction_ == 1) {yr += sprite_.getSize()/2; xr += 1;}
		if(direction_ == 2) { xr += sprite_.getSize()/2; yr += 1;}
		if(direction_ == 3) { xr += sprite_.getSize() -1; yr += sprite_.getSize()/2;}
		
		int xx = Coordinates.pixelToTile(xr) +(int)x;
		int yy = Coordinates.pixelToTile(yr) +(int)y;
		
		Entity a = board_.getEntity(xx, yy, this); //entity of the position we want to go
		
		return a.collide(this);
	}
	
	/*
	|--------------------------------------------------------------------------
	| Character Colide & Kill
	|--------------------------------------------------------------------------
	 */
	@Override
	public boolean collide(Entity e) {
		if(e instanceof DirectionalExplosion) {
			kill();
			return false;
		}
		
		if(e instanceof Player) {
			((Player) e).kill();
			return false;
		}
		
		return true;
	}
	
	@Override
	public void kill() {
		if(alive_ == false)
			return;
		alive_ = false;

		Sound.sound_enemy_die.playSound();
		
		board_.addPoints(points_);

		Message msg = new Message("+" + points_, getXMessage(), getYMessage(), 2, Color.white, 14);
		board_.addMessage(msg);
	}
	
	
	@Override
	protected void afterKill() {
		if(timeAfter_ > 0) --timeAfter_;
		else {
			
			if(finalAnimation_ > 0) --finalAnimation_;
			else
				remove();
		}
	}
	
	protected abstract void chooseSprite();
}
