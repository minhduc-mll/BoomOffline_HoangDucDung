package hoang.duc.dung.boomoffline.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.sound.Sound;

public class Screen {
	protected int width_, height_;
	public int[] pixels_;
	private int transparentColor_ = 0xffff00ff; // pink with alpha channel (ff in the begining)

	public static int xOffset_ = 0;
	public static int yOffset_ = 0;

	public Screen(int width, int height) {
		width_ = width;
		height_ = height;

		pixels_ = new int[width * height];

	}

	public void clear() {
		for (int i = 0; i < pixels_.length; i++) {
			pixels_[i] = 0;
		}
	}

	public void renderEntity(int xp, int yp, Entity entity) {
		xp -= xOffset_;
		yp -= yOffset_;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp; // add offset
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp; // add offset
				if (xa < -entity.getSprite().getSize() || xa >= width_ || ya < 0 || ya >= height_)
					break; // fix black margins
				if (xa < 0)
					xa = 0; // start at 0 from left
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if (color != transparentColor_)
					pixels_[xa + ya * width_] = color;
			}
		}
	}

	public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
		xp -= xOffset_;
		yp -= yOffset_;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp;
				if (xa < -entity.getSprite().getSize() || xa >= width_ || ya < 0 || ya >= height_)
					break; // fix black margins
				if (xa < 0)
					xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if (color != transparentColor_)
					pixels_[xa + ya * width_] = color;
				else
					pixels_[xa + ya * width_] = below.getPixel(x + y * below.getSize());
			}
		}
	}

	public void drawEntity(int xp, int yp, Entity entity, Graphics graphics) {
		xp -= xOffset_;
		yp -= yOffset_;
		graphics.drawImage(entity.getSpriteImage().getImage(), xp * Game.SCALE, yp * Game.SCALE,
				Game.TILES_SIZE * Game.SCALE, Game.TILES_SIZE * Game.SCALE, null);
	}

	public void drawEntityWithBelowSprite(int xp, int yp, Entity entity, SpriteImage below, Graphics graphics) {
		xp -= xOffset_;
		yp -= yOffset_;
		graphics.drawImage(below.getImage(), xp * Game.SCALE, yp * Game.SCALE,
				Game.TILES_SIZE * Game.SCALE, Game.TILES_SIZE * Game.SCALE, null);
	}

	public void drawAnimation(int xp, int yp, int width, int height, SpriteImage image, Graphics graphics) {
		xp -= xOffset_;
		yp -= yOffset_;
		graphics.drawImage(image.getImage(), xp * Game.SCALE, yp * Game.SCALE,
				width, height, null);
	}

	public static void setOffset(int xO, int yO) {
		xOffset_ = xO;
		yOffset_ = yO;
	}

	public static int calculateXOffset(Board board, Player player) {
		if (player == null)
			return 0;
		int offset = xOffset_;

		double playerX = player.getX();
		double complement = Game.TILES_SIZE * 0.5;
		double firstBreakpoint = Game.WIDTH * 0.5;
		double lastBreakpoint = board.getWidth() * Game.TILES_SIZE - firstBreakpoint;

		if (playerX >= firstBreakpoint - complement && playerX <= lastBreakpoint - complement) {
			offset = (int) (playerX - firstBreakpoint + complement);
		}

		return offset;
	}

	public static int calculateYOffset(Board board, Player player) {
		if (player == null)
			return 0;
		int offset = yOffset_;

		double playerY = player.getY();
		double complement = Game.TILES_SIZE * 0.5;
		double firstBreakpoint = Game.HEIGHT * 0.5;
		double lastBreakpoint = board.getHeight() * Game.TILES_SIZE - firstBreakpoint;

		if (playerY >= firstBreakpoint - complement && playerY <= lastBreakpoint - complement) {
			offset = (int) (playerY - firstBreakpoint + complement);
		}

		return offset;
	}

	// --------------------------------------------------------------------------
	// | Game Screens
	// --------------------------------------------------------------------------
	public void drawEndGame(Graphics g, int points, String code) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());

		Font font = new Font("Times new roman", Font.BOLD, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString(" U là trời, thua hoài vậy ? ", getRealWidth(), getRealHeight()-100, g);
		drawCenteredString(" Làm ơn chơi thắng dùm đi !!! ", getRealWidth(), getRealHeight(), g);

		font = new Font("algerian", Font.BOLD, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.PINK);
		drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE,
				g);

		font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.GRAY);
		drawCenteredString(code, getRealWidth(), getRealHeight() * 2 - (Game.TILES_SIZE * 2) * Game.SCALE, g);
	}

	public void drawChangeLevel(Graphics g, int level) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());

		Font font = new Font("algerian", Font.BOLD, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.black);
		drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);

	}

	public void drawPaused(Graphics g) {
		Font font = new Font("algerian", Font.BOLD, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);

	}

	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x, y);
	}

	public int getWidth() {
		return width_;
	}

	public int getHeight() {
		return height_;
	}

	public int getRealWidth() {
		return width_ * Game.SCALE;
	}

	public int getRealHeight() {
		return height_ * Game.SCALE;
	}
}
