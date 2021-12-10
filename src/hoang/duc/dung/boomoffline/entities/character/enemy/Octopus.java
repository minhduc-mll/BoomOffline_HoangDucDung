package hoang.duc.dung.boomoffline.entities.character.enemy;


import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.enemy.ai.AIMedium;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

public class Octopus extends Enemy {

	public Octopus(int x, int y, Board board) {
		super(x, y, board, Sprite.minvo_dead, SpriteImage.images_enemy_dead, Game.NORMALSPEED, 800);
		
		sprite_ = Sprite.minvo_right1;
		image_ = SpriteImage.images_enemy_octopus_down[0];
		
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
		switch(direction_) {
			case 0:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_octopus_up, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_octopus_down[0];
				}
				break;
			case 1:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_octopus_right, animate_, 60);
				} else {
					sprite_ = Sprite.minvo_left1;
					image_ = SpriteImage.images_enemy_octopus_down[0];
				}
				break;
			case 2:
				if(moving_) {
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_octopus_down, animate_, 60);
				} else {
					image_ = SpriteImage.images_enemy_octopus_down[0];
				}
				break;
			case 3:
				if(moving_) {
					sprite_ = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate_, 60);
					image_ = SpriteImage.movingImage(SpriteImage.images_enemy_octopus_left, animate_, 60);
				} else {
					sprite_ = Sprite.minvo_left1;
					image_ = SpriteImage.images_enemy_octopus_down[0];
				}
				break;
			default:
				image_ = SpriteImage.images_enemy_octopus_down[0];
				break;
		}
	}
}
