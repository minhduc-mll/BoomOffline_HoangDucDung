package hoang.duc.dung.boomoffline.entities.character.enemy;


import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AIAdvance;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class Pirate extends Enemy {

	public Pirate(int x, int y, Board board) {
		super(x, y, board, Sprite.doll_dead, SpriteImage.images_enemy_pirate_dead, Game.NORMALSPEED, 400);
		
		sprite_ = Sprite.doll_right1;
		image_ = SpriteImage.images_enemy_pirate_down[0];
		
		ai_ = new AIAdvance(board_, board_.getPlayer(), this);
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
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_up, animate_, 60);
				} else {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_feel, animate_, 60);
				}
				break;
			case 1:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_right, animate_, 60);
				} else {
					sprite_ = Sprite.doll_left1;
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_feel, animate_, 60);
				}
				break;
			case 2:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_down, animate_, 60);
				} else {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_feel, animate_, 60);
				}
				break;
			case 3:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_left, animate_, 60);
				} else {
					sprite_ = Sprite.doll_left1;
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_feel, animate_, 60);
				}
				break;
			default:
				image_ = SpriteImage.movingImage(SpriteImage.images_enemy_pirate_feel, animate_, 60);
				break;
		}
	}
}
