package hoang.duc.dung.boomoffline.entities;

import java.awt.*;

import hoang.duc.dung.boomoffline.graphics.Screen;

public class Message extends Entity {

	protected String message_;
	protected int duration_;
	protected Color color_;
	protected int size_;
	
	public Message(String message, double x, double y, int duration, Color color, int size) {
		x_ =x;
		y_ = y;
		message_ = message;
		duration_ = duration * 60; //seconds
		color_ = color;
		size_ = size;
	}

	public int getDuration() {
		return duration_;
	}

	public void setDuration(int _duration) {
		this.duration_ = _duration;
	}

	public String getMessage() {
		return message_;
	}

	public Color getColor() {
		return color_;
	}

	public int getSize() {
		return size_;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Screen screen) {
	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
	}

	@Override
	public boolean collide(Entity e) {
		return true;
	}
	
	
}
