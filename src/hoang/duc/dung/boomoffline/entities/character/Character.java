package hoang.duc.dung.boomoffline.entities.character;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.AnimatedEntity;
import hoang.duc.dung.boomoffline.graphics.Screen;

import java.awt.*;

public abstract class Character extends AnimatedEntity {
	
	protected Board board_;
	protected int direction_ = -1;
	protected boolean alive_ = true;
	protected boolean moving_ = false;
	public int timeAfter_ = 150;
	
	public Character(int x, int y, Board board) {
		x_ = x;
		y_ = y;
		board_ = board;
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);

	@Override
	public abstract void draw(Screen screen, Graphics graphics);
	
	protected abstract void calculateMove();
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterKill();
	
	protected abstract boolean canMove(double x, double y);
	
	public boolean isAlive() {
		return alive_;
	}
	
	public boolean isMoving() {
		return moving_;
	}
	
	public int getDirection() {
		return direction_;
	}
	
	protected double getXMessage() {
		return (x_ * Game.SCALE) + (sprite_.SIZE / 2 * Game.SCALE);
	}
	
	protected double getYMessage() {
		return (y_ * Game.SCALE) - (sprite_.SIZE / 2 * Game.SCALE);
	}
	
}
