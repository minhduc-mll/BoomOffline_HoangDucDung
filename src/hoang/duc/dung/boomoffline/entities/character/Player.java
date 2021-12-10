package hoang.duc.dung.boomoffline.entities.character;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.Message;
import hoang.duc.dung.boomoffline.entities.boom.Boom;
import hoang.duc.dung.boomoffline.entities.boom.DirectionalExplosion;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;
import hoang.duc.dung.boomoffline.entities.tile.items.Item;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.input.Keyboard;
import hoang.duc.dung.boomoffline.level.Coordinates;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Character {
	
	private List<Boom> booms_;
	protected Keyboard input_;
	private SpriteImage imageAnimation_;
	
	protected int timeBetweenPutBooms_ = 0;
	
	public static List<Item> items_ = new ArrayList<Item>();
	
	
	public Player(int x, int y, Board board) {
		super(x, y, board);
		booms_ = board_.getBooms();
		input_ = board_.getInput();
		sprite_ = Sprite.player_right;
		image_ = SpriteImage.images_player_down[0];
		imageAnimation_ = SpriteImage.images_animation[0];
	}
	
	
	// --------------------------------------------------------------------------
	// | Update, Render, Draw and Collide
	// --------------------------------------------------------------------------
	@Override
	public void update() {
		clearBooms();
		if(alive_ == false) {
			afterKill();
			return;
		}
		
		if(timeBetweenPutBooms_ < -7500) timeBetweenPutBooms_ = 0; else timeBetweenPutBooms_--; //dont let this get tooo big
		
		animate();
		
		calculateMove();
		
		detectPlaceBoom();
	}
	
	@Override
	public void render(Screen screen) {
		// calculateXOffset();
		calculateOffset();
		
		if(alive_)
			chooseSprite();
		else
			sprite_ = Sprite.player_dead1;
		
		screen.renderEntity((int) x_, (int) y_ - sprite_.SIZE, this);
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		// calculateXOffset();
		calculateOffset();

		if (alive_) {
			chooseSprite();
			imageAnimation_ = SpriteImage.movingImage(SpriteImage.images_animation, animate_, 60);
		} else {
			image_ = SpriteImage.movingImage(SpriteImage.images_player_dead, animate_, 60);
		}

		screen.drawEntity((int) x_, (int) y_ - sprite_.SIZE, this, graphics);
		screen.drawAnimation((int) x_, (int) y_ - sprite_.SIZE,
				48, 48, imageAnimation_, graphics);
	}

	@Override
	public boolean collide(Entity e) {
		if(e instanceof DirectionalExplosion) {
			kill();
			return false;
		}

		if(e instanceof Enemy) {
			kill();
			return true;
		}

		return true;
	}


	public void calculateOffset() {
		int xScroll = Screen.calculateXOffset(board_, this);
		int yScroll = Screen.calculateYOffset(board_, this);
		Screen.setOffset(xScroll, yScroll);
	}
	
	public void calculateXOffset() {
		int xScroll = Screen.calculateXOffset(board_, this);
		Screen.setOffset(xScroll, 0);
	}

	public void calculateYOffset() {
		int yScroll = Screen.calculateYOffset(board_, this);
		Screen.setOffset(0, yScroll);
	}


	// --------------------------------------------------------------------------
	// | Character Unique
	// --------------------------------------------------------------------------
	private void detectPlaceBoom() {
		if(input_.space_ && Game.getBoomRate() > 0 && timeBetweenPutBooms_ < 0) {
			
			int xt = Coordinates.pixelToTile(x_ + sprite_.getSize() / 2);
			int yt = Coordinates.pixelToTile( (y_ + sprite_.getSize() / 2) - sprite_.getSize() ); //subtract half player height and minus 1 y position
			
			placeBoom(xt,yt);
			Game.addBoomRate(-1);
			
			timeBetweenPutBooms_ = 30;
		}
	}
	
	protected void placeBoom(int x, int y) {
		Sound.sound_place_boom.playSound();

		Boom b = new Boom(x, y, board_);
		board_.addBoom(b);
	}
	
	private void clearBooms() {
		Iterator<Boom> bs = booms_.iterator();
		
		Boom b;
		while(bs.hasNext()) {
			b = bs.next();
			if(b.isRemoved())  {
				bs.remove();
				Game.addBoomRate(1);
			}
		}
		
	}
	
	// --------------------------------------------------------------------------
	// | Character Colide & Kill
	// --------------------------------------------------------------------------
	@Override
	public void kill() {
		if(!alive_)
			return;
		alive_ = false;

		Sound.sound_player_die.playSound();
		
		board_.addLives(-1);

		Message msg = new Message("-1 LIVE", getXMessage(), getYMessage(), 2, Color.white, 14);
		board_.addMessage(msg);
	}
	
	@Override
	protected void afterKill() {
		if(timeAfter_ > 0) --timeAfter_;
		else {
			if(booms_.size() == 0) {
				
				if(board_.getLives() == 0)
					board_.endGame();
				else
					board_.restartLevel();
			}
		}
	}
	
	// --------------------------------------------------------------------------
	// | Character Movement
	// --------------------------------------------------------------------------
	@Override
	protected void calculateMove() {
		int xa = 0, ya = 0;
		if (input_.up_) {
			ya--;
		}
		if (input_.down_) {
			ya++;
		}
		if (input_.left_) {
			xa--;
		}
		if (input_.right_) {
			xa++;
		}
		
		if(xa != 0 || ya != 0)  {
			move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
			moving_ = true;
		} else {
			moving_ = false;
		}
		
	}
	
	@Override
	public boolean canMove(double x, double y) {
		if (x_ <= 0 && x < 0 || x_ >= (board_.getWidth() - 1) * Game.TILES_SIZE && x > 0
				|| y_ <= Game.TILES_SIZE && y < 0 || y_ >= board_.getHeight() * Game.TILES_SIZE && y > 0) {
			return false;
		}

		for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
			double xt = ((x_ + x) + c % 2 * 11) / Game.TILES_SIZE; //divide with tiles size to pass to tile coordinate
			double yt = ((y_ + y) + c / 2 * 12 - 13) / Game.TILES_SIZE; //these values are the best from multiple tests
			
			Entity a = board_.getEntity(xt, yt, this);
			
			if(!a.collide(this))
				return false;
		}
		
		return true;
	}

	@Override
	public void move(double xa, double ya) {
		if(xa > 0) direction_ = 1;
		if(xa < 0) direction_ = 3;
		if(ya > 0) direction_ = 2;
		if(ya < 0) direction_ = 0;
		
		if(canMove(0, ya)) {
			y_ += ya;
		}
		
		if(canMove(xa, 0)) {
			x_ += xa;
		}
	}
	

	// --------------------------------------------------------------------------
	// | Items
	// --------------------------------------------------------------------------
	public void addItem(Item p) {
		if(p.isRemoved()) return;
		
		items_.add(p);
		
		p.setValues();
	}
	
	public void clearUsedItems() {
		Item p;
		for (int i = 0; i < items_.size(); i++) {
			p = items_.get(i);
			if(p.isActive() == false)
				items_.remove(i);
		}
	}
	
	public void removeItems() {
		for (int i = 0; i < items_.size(); i++) {
			items_.remove(i);
		}
	}
	
	// --------------------------------------------------------------------------
	// | Character Sprite
	// --------------------------------------------------------------------------
	private void chooseSprite() {
		switch(direction_) {
		case 0:
			if(moving_) {
				sprite_ = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate_, 20);
				image_ = SpriteImage.movingImage(SpriteImage.images_player_up, animate_, 20);
			} else {
				sprite_ = Sprite.player_up;
				image_ = SpriteImage.movingImage(SpriteImage.images_player_feel, animate_, 40);
			}
			break;
		case 1:
			if(moving_) {
				sprite_ = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate_, 20);
				image_ = SpriteImage.movingImage(SpriteImage.images_player_right, animate_, 20);
			} else {
				sprite_ = Sprite.player_right;
				image_ = SpriteImage.movingImage(SpriteImage.images_player_feel, animate_, 40);
			}
			break;
		case 2:
			if(moving_) {
				sprite_ = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate_, 20);
				image_ = SpriteImage.movingImage(SpriteImage.images_player_down, animate_, 20);
			} else {
				sprite_ = Sprite.player_down;
				image_ = SpriteImage.movingImage(SpriteImage.images_player_feel, animate_, 40);
			}
			break;
		case 3:
			if(moving_) {
				sprite_ = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate_, 20);
				image_ = SpriteImage.movingImage(SpriteImage.images_player_left, animate_, 20);
			} else {
				sprite_ = Sprite.player_left;
				image_ = SpriteImage.movingImage(SpriteImage.images_player_feel, animate_, 40);
			}
			break;
		default:
			if(moving_) {
				sprite_ = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate_, 20);
				image_ = SpriteImage.movingImage(SpriteImage.images_player_down, animate_, 20);
			} else {
				sprite_ = Sprite.player_right;
				image_ = SpriteImage.movingImage(SpriteImage.images_player_feel, animate_, 40);
			}
			break;
		}
	}

	// --------------------------------------------------------------------------
	// | Getter & Setter
	// --------------------------------------------------------------------------
	public List<Boom> getBooms() {
		return booms_;
	}
}
