package hoang.duc.dung.boomoffline.entities.boom;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.entities.AnimatedEntity;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Character;
import hoang.duc.dung.boomoffline.graphics.Screen;

import java.awt.*;

public class DirectionalExplosion extends AnimatedEntity {

	protected Board board_;
	protected int direction_;
	private int radius_;
	protected int xOrigin_, yOrigin_;
	protected Explosion[] explosions_;
	
	public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
		xOrigin_ = x;
		yOrigin_ = y;
		x_ = x;
		y_ = y;
		direction_ = direction;
		radius_ = radius;
		board_ = board;
		
		explosions_ = new Explosion[ calculatePermitedDistance() ];
		createExplosions();
	}

	/**
	 * Tạo các Explosions, mỗi Explosion ứng một đơn vị độ dài.
	 */
	private void createExplosions() {
		boolean last = false; // Vị trí explosion cuối cùng
		
		int x = (int) x_;
		int y = (int) y_;
		for (int i = 0; i < explosions_.length; i++) {
			last = (i == explosions_.length - 1);
			
			switch (direction_) {
				case UP:
					y--;
					break;
				case RIGHT:
					x++;
					break;
				case DOWN:
					y++;
					break;
				case LEFT:
					x--;
					break;
			}
			explosions_[i] = new Explosion(x, y, direction_, last, board_);
		}
	}

	/**
	 * Tính toán độ dài của Flame, bị chặn bởi các vật cản là Brick/Wall.
	 */
	private int calculatePermitedDistance() {
		int radius = 0;
		int x = (int) x_;
		int y = (int) y_;
		while(radius < radius_) {
			if(direction_ == UP)
				y--;
			if(direction_ == RIGHT)
				x++;
			if(direction_ == DOWN)
				y++;
			if(direction_ == LEFT)
				x--;
			
			Entity a = board_.getEntity(x, y, null);

			if(a instanceof Character)
				++radius; // explosion has to be below the character

			if(a.collide(this) == false) // cannot pass thru
				break;
			
			++radius;
		}
		return radius;
	}
	
	public Explosion explosionAt(int x, int y) {
		for (int i = 0; i < explosions_.length; i++) {
			if (explosions_[i].getX() == x && explosions_[i].getY() == y)
				return explosions_[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i].render(screen);
		}
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		for (int i = 0; i < explosions_.length; i++) {
			explosions_[i].draw(screen, graphics);
		}
	}

	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
