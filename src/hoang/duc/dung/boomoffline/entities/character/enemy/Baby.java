package hoang.duc.dung.boomoffline.entities.character.enemy;


import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AIRandom;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class Baby extends Enemy {

	public Baby(int x, int y, Board board) {
		super(x, y, board, Sprite.balloom_dead, SpriteImage.images_enemy_gold_dead, Game.NORMALSPEED / 2, 100);
		
		sprite_ = Sprite.balloom_left1;
		image_ = SpriteImage.images_enemy_gold_down[0];
		
		ai_ = new AIRandom();
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
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_gold_up, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_gold_down[0];
				}
				break;
			case 1:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_gold_right, animate_, 60);
				} else {
					sprite_ = Sprite.balloom_left1;
					image_ = SpriteImage.images_enemy_gold_down[0];
				}
				break;
			case 2:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_gold_down, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_gold_down[0];
				}
				break;
			case 3:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_gold_left, animate_, 60);
				} else {
					sprite_ = Sprite.balloom_left1;
					image_ = SpriteImage.images_enemy_gold_down[0];
				}
				break;
			default:
				image_ = SpriteImage.images_enemy_gold_down[0];
				break;
		}
	}
}
