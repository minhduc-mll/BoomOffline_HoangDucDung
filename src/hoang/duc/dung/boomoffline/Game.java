package hoang.duc.dung.boomoffline;

import hoang.duc.dung.boomoffline.exceptions.BoomOfflineException;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.gui.Frame;
import hoang.duc.dung.boomoffline.input.IOClass;
import hoang.duc.dung.boomoffline.input.Keyboard;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {

	// --------------------------------------------------------------------------
	// | Options & Configs
	// --------------------------------------------------------------------------
	public static final double VERSION = 1.0;

	public static final int TILES_SIZE = 16;
	
	public static final int TILES_SCREEN_WIDTH = 15; // Chiều ngang màn hình hiển thị 15 ô
	public static final int TILES_SCREEN_HEIGHT = 13; // Chiều dọc màn hình hiển thị 13 ô

	public static final int WIDTH = TILES_SCREEN_WIDTH * TILES_SIZE; // Chiều rộng màn hình
	public static final int HEIGHT = TILES_SCREEN_HEIGHT * TILES_SIZE; // Chiều cao màn hình

	public static int SCALE = 3;

	public static final String TITLE = "BoomOffline_HDD " + VERSION;

	// initial configs
	public static final int BOMBRATE = 2;
	public static final int BOMBRADIUS = 1;
	public static final double PLAYERSPEED = 1.0;
	public static final double NORMALSPEED = 1.0;

	public static final int TIME = 1800;
	public static final int POINTS = 0;
	public static final int LIVES = 3;
	public static final int HIGHSCORE = new Integer(IOClass.Read());

	protected static int SCREENDELAY = 3;

	// can be modified with bonus
	protected static int boomRate_ = BOMBRATE;
	protected static int boomRadius_ = BOMBRADIUS;
	protected static double playerSpeed_ = PLAYERSPEED;

	// Time in the level screen in seconds
	protected int screenDelay_ = SCREENDELAY;

	private Keyboard input_;
	private boolean running_ = false;
	private boolean paused_ = true;

	private static Board board_;
	private static Screen screen_;
	private static Frame frame_;

	// this will be used to render the game, each render is a calculated image saved
	// here
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game(Frame frame) throws BoomOfflineException {
		frame_ = frame;
		frame_.setTitle(TITLE);

		screen_ = new Screen(WIDTH, HEIGHT);
		input_ = new Keyboard();

		board_ = new Board(this, input_, screen_);
		addKeyListener(input_);
	}

	private void renderGame() { // render will run the maximum times it can per second
		BufferStrategy bs = getBufferStrategy(); // create a buffer to store images using canvas
		if (bs == null) { // if canvas dont have a bufferstrategy, create it
			createBufferStrategy(3); // triple buffer
			return;
		}

		screen_.clear();

		board_.render(screen_);

		for (int i = 0; i < pixels.length; i++) { // create the image to be rendered
			pixels[i] = screen_.pixels_[i];
		}

		Graphics g = bs.getDrawGraphics();

//		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//		g.drawImage(SpriteImage.images_background.getImage(), 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		board_.draw(screen_, g);
		board_.renderMessages(g);

		g.dispose(); // release resources
		bs.show(); // make next buffer visible
	}

	private void renderScreen() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen_.clear();

		Graphics g = bs.getDrawGraphics();

		board_.drawScreen(g);

		g.dispose();
		bs.show();
	}

	private void update() {
		input_.update();
		board_.update();
	}

	public void start() {
		running_ = true;

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; // nanosecond, 60 frames per second
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running_) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			if (paused_) {
				if (screenDelay_ <= 0) { // time passed? lets reset status to show the game
					board_.setShow(-1);
					paused_ = false;
				}

				renderScreen();
			} else {
				renderGame();
			}

			frames++;
			if (System.currentTimeMillis() - timer > 1000) { // once per second
				frame_.setTime(board_.increaseTime());
				frame_.setPoints(board_.getPoints());
				frame_.setLives(board_.getLives());
				timer += 1000;
				frame_.setTitle(TITLE + " | " + updates + " rate, " + frames + " fps");
				updates = 0;
				frames = 0;

				if (board_.getShow() == 2)
					--screenDelay_;
			}
		}
	}

	// --------------------------------------------------------------------------
	// | Getters & Setters
	// --------------------------------------------------------------------------
	public static double getPlayerSpeed() {
		return playerSpeed_;
	}

	public static int getBoomRate() {
		return boomRate_;
	}

	public static int getBoomRadius() {
		return boomRadius_;
	}

	public static void addPlayerSpeed(double i) {
		playerSpeed_ += i;
	}

	public static void addBoomRadius(int i) {
		boomRadius_ += i;
	}

	public static void addBoomRate(int i) {
		boomRate_ += i;
	}

	public static void addPoints(int i) {
		board_.addPoints(i);
	}

	// screen delay
	public int getScreenDelay() {
		return screenDelay_;
	}

	public void decreaseScreenDelay() {
		screenDelay_--;
	}

	public void resetScreenDelay() {
		screenDelay_ = SCREENDELAY;
	}

	public Keyboard getInput() {
		return input_;
	}

	public Board getBoard() {
		return board_;
	}

	public void run() {
		running_ = true;
		paused_ = false;
	}

	public void stop() {
		running_ = false;
	}

	public boolean isRunning() {
		return running_;
	}

	public boolean isPaused() {
		return paused_;
	}

	public void pause() {
		paused_ = true;
	}

}
