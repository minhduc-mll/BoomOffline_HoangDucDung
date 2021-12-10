package hoang.duc.dung.boomoffline.entities.character.enemy;


import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AILow;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class Mummy extends Enemy {
	
	public Mummy(int x, int y, Board board) {
		super(x, y, board, Sprite.oneal_dead, SpriteImage.images_enemy_dead, Game.NORMALSPEED, 200);
		
		sprite_ = Sprite.oneal_left1;
		image_ = SpriteImage.images_enemy_mummy_down[2];
		
		ai_ = new AILow(board_.getPlayer(), this);
		direction_ = ai_.calculateDirection();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Character Sprite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		switch(direction_) {
			case 0:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_mummy_up, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_mummy_down[0];
				}
				break;
			case 1:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_mummy_right, animate_, 60);
				} else {
					sprite_ = Sprite.oneal_left1;
					image_ = SpriteImage.images_enemy_mummy_down[0];
				}
				break;
			case 2:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_mummy_down, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_mummy_down[0];
				}
				break;
			case 3:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_mummy_left, animate_, 60);
				} else {
					sprite_ = Sprite.oneal_left1;
					image_ = SpriteImage.images_enemy_mummy_down[0];
				}
				break;
			default:
				image_ = SpriteImage.images_enemy_mummy_down[0];
				break;
		}
	}
}
