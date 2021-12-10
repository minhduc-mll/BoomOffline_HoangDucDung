package hoang.duc.dung.boomoffline.entities.character.enemy;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AIMedium;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

import java.util.Random;

public class Ghost extends Enemy {
	private Random random = new Random();
	private int random_ = random.nextInt(8);
	private long now_ = 0;
	private long last_ = 0;
	private int time_ = 60;

	public Ghost(int x, int y, Board board) {
		super(x, y, board, Sprite.ghost_dead, SpriteImage.images_enemy_ghost_dead, Game.NORMALSPEED / 4, 1000);
		
		sprite_ = Sprite.ghost_right1;
		image_ = SpriteImage.images_enemy_ghost_down[0][0];
		
		ai_ = new AIMedium(board_, board_.getPlayer(), this);
		direction_ = ai_.calculateDirection();
	}

	/*
	|--------------------------------------------------------------------------
	| Character Sprite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		now_ = System.currentTimeMillis();
		if (now_ - last_ > 2000) {
			random_ = random.nextInt(8);
			last_ = now_;
		}

		switch(direction_) {
			case 0:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_up[random_], animate_, time_);
				} else {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				}
				break;
			case 1:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, Sprite.ghost_right3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_right[random_], animate_, time_);
				} else {
					sprite_ = Sprite.ghost_left1;
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				}
				break;
			case 2:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				} else {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				}
				break;
			case 3:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, Sprite.ghost_left3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_left[random_], animate_, time_);
				} else {
					sprite_ = Sprite.ghost_left1;
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				}
				break;
			default:
				image_ = SpriteImage.movingImage(SpriteImage.images_enemy_ghost_down[random_], animate_, time_);
				break;
		}
	}
}
