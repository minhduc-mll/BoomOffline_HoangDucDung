package hoang.duc.dung.boomoffline.entities.tile;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

import java.awt.*;

public class PortalTile extends Tile {

	protected Board board_;
	
	public PortalTile(int x, int y, Board board, Sprite sprite, SpriteImage image) {
		super(x, y, sprite, image);
		board_ = board;
	}

	@Override
	public void update() {
		animate();
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		image_ = SpriteImage.movingImage(SpriteImage.images_map_tree, animate_, 60);
		super.draw(screen, graphics);
	}

	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			
			if(board_.detectNoEnemies() == false)
				return false;
			
			if(e.getXTile() == getX() && e.getYTile() == getY()) {
				if(board_.detectNoEnemies())
					board_.nextLevel();
			}
			
			return true;
		}
		
		return false;
	}

}
