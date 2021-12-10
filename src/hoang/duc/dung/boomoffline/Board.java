package hoang.duc.dung.boomoffline;

import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.Message;
import hoang.duc.dung.boomoffline.entities.boom.Boom;
import hoang.duc.dung.boomoffline.entities.boom.Explosion;
import hoang.duc.dung.boomoffline.entities.character.Character;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.tile.GrassTile;
import hoang.duc.dung.boomoffline.entities.tile.items.Item;
import hoang.duc.dung.boomoffline.exceptions.LoadLevelException;
import hoang.duc.dung.boomoffline.graphics.IRender;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.input.IOClass;
import hoang.duc.dung.boomoffline.input.Keyboard;
import hoang.duc.dung.boomoffline.level.FileLevel;
import hoang.duc.dung.boomoffline.level.Level;
import hoang.duc.dung.boomoffline.sound.Sound;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements IRender {

	public int width_, height_;
	protected Level level_;
	protected Game game_;
	protected Keyboard input_;
	protected Screen screen_;

	public Entity[] entities_;
	public List<Character> characters_ = new ArrayList<Character>();
	protected List<Boom> booms_ = new ArrayList<Boom>();
	private List<Message> messages_ = new ArrayList<Message>();

	private int screenToShow_ = -1; // 1: endgame, 2: changelevel, 3: paused

	private int time_ = 0;
	private int points_ = Game.POINTS;
	private int lives_ = Game.LIVES;
	private int highScore_ = Game.HIGHSCORE;

	public Board(Game game, Keyboard input, Screen screen) {
		game_ = game;
		input_ = input;
		screen_ = screen;

		changeLevel(4); // start in level 1
	}

	// --------------------------------------------------------------------------
	// | Render & Update
	// --------------------------------------------------------------------------
	@Override
	public void update() {
		if (game_.isPaused())
			return;

		updateEntities();
		updateCharacters();
		updateBooms();
		updateMessages();
		detectEndGame();

		for (int i = 0; i < characters_.size(); i++) {
			Character a = characters_.get(i);
			if (((Entity) a).isRemoved())
				characters_.remove(i);
		}
	}

	@Override
	public void render(Screen screen) {
		if (game_.isPaused())
			return;

		// only render the visible part of screen
		int x0 = Screen.xOffset_ / Game.TILES_SIZE; // tile precision, -> left X
		int x1 = (Screen.xOffset_ + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
		int y0 = Screen.yOffset_ / Game.TILES_SIZE;
		int y1 = (Screen.yOffset_ + screen.getHeight() + Game.TILES_SIZE) / Game.TILES_SIZE; // render one tile plus to fix black margins

		if (x1 > getWidth()) {
			x1 = getWidth();
		}
		if (y1 > getHeight()) {
			y1 = getHeight();
		}

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				entities_[x + y * level_.getWidth()].render(screen);
			}
		}

		renderBooms(screen);
		renderCharacters(screen);

	}

	@Override
	public void draw(Screen screen, Graphics graphics) {
		if (game_.isPaused())
			return;

		// only render the visible part of screen
		int x0 = Screen.xOffset_ / Game.TILES_SIZE; // tile precision, -> left X
		int x1 = (Screen.xOffset_ + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
		int y0 = Screen.yOffset_ / Game.TILES_SIZE;
		int y1 = (Screen.yOffset_ + screen.getHeight() + Game.TILES_SIZE) / Game.TILES_SIZE; // render one tile plus to fix black margins

		if (x1 > getWidth()) {
			x1 = getWidth();
		}
		if (y1 > getHeight()) {
			y1 = getHeight();
		}

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				entities_[x + y * level_.getWidth()].draw(screen, graphics);
			}
		}

		drawBooms(screen, graphics);
		drawCharacters(screen, graphics);
	}

	// --------------------------------------------------------------------------
	// | ChangeLevel
	// --------------------------------------------------------------------------
	public void newGame() {
		resetProperties();
		changeLevel(1);
	}

	@SuppressWarnings("static-access")
	private void resetProperties() {
		points_ = Game.POINTS;
		lives_ = Game.LIVES;
		Player.items_.clear();

		game_.playerSpeed_ = game_.PLAYERSPEED;
		game_.boomRadius_ = game_.BOMBRADIUS;
		game_.boomRate_ = game_.BOMBRATE;

	}

	public void restartLevel() {
		changeLevel(level_.getLevel());
	}

	public void nextLevel() {
		changeLevel(level_.getLevel() + 1);
	}

	public void changeLevel(int level) {
		time_ = 0;
		screenToShow_ = 2;
		game_.resetScreenDelay();
		game_.pause();
		characters_.clear();
		booms_.clear();
		messages_.clear();

		try {
			level_ = new FileLevel(level, this);
			entities_ = new Entity[level_.getHeight() * level_.getWidth()];

			level_.createEntities();
		} catch (LoadLevelException e) {
			endGame(); // failed to load.. so.. end game?
		}
	}

	public void changeLevelByCode(String str) {
		int i = level_.validCode(str);

		if (i != -1)
			changeLevel(i + 1);
	}

	public boolean isItemUsed(int x, int y, int level) {
		Item p;
		for (int i = 0; i < Player.items_.size(); i++) {
			p = Player.items_.get(i);
			if (p.getX() == x && p.getY() == y && level == p.getLevel())
				return true;
		}

		return false;
	}

	// --------------------------------------------------------------------------
	// | Detections
	// --------------------------------------------------------------------------
	protected void detectEndGame() {
		if (time_ >= Game.TIME)
			restartLevel();
	}

	public void endGame() {
		Integer highScore = new Integer(IOClass.Read());
		if (getPoints() > highScore) {
			IOClass.write(getPoints());
		}

		screenToShow_ = 1;
		game_.resetScreenDelay();
		game_.pause();
	}

	public boolean detectNoEnemies() {
		int total = 0;
		for (int i = 0; i < characters_.size(); i++) {
			if (characters_.get(i) instanceof Player == false)
				++total;
		}

		return total == 0;
	}

	// --------------------------------------------------------------------------
	// | Pause & Resume
	// --------------------------------------------------------------------------
	public void gamePause() {
		game_.resetScreenDelay();
		if (screenToShow_ <= 0)
			screenToShow_ = 3;
		game_.pause();
	}

	public void gameResume() {
		game_.resetScreenDelay();
		screenToShow_ = -1;
		game_.run();
	}

	// --------------------------------------------------------------------------
	// | Screens
	// --------------------------------------------------------------------------
	public void drawScreen(Graphics g) {
		switch (screenToShow_) {
			case 1:
				Sound.sound_lose.playSound();
				screen_.drawEndGame(g, points_, level_.getActualCode());
				break;
			case 2:
				screen_.drawChangeLevel(g, level_.getLevel());
				break;
			case 3:
				screen_.drawPaused(g);
				break;
		}
	}

	// --------------------------------------------------------------------------
	// | Getters And Setters
	// --------------------------------------------------------------------------
	public Entity getEntity(double x, double y, Character m) {

		Entity res = null;

		res = getExplosionAt((int) x, (int) y);
		if (res != null)
			return res;

		res = getBoomAt(x, y);
		if (res != null)
			return res;

		res = getCharacterAtExcluding((int) x, (int) y, m);
		if (res != null)
			return res;

		res = getEntityAt((int) x, (int) y);

		return res;
	}

	public List<Boom> getBooms() {
		return booms_;
	}

	public Boom getBoomAt(double x, double y) {
		Iterator<Boom> bs = booms_.iterator();
		Boom b;
		while (bs.hasNext()) {
			b = bs.next();
			if (b.getX() == (int) x && b.getY() == (int) y)
				return b;
		}

		return null;
	}

	public Character getCharacterAt(double x, double y) {
		Iterator<Character> itr = characters_.iterator();

		Character cur;
		while (itr.hasNext()) {
			cur = itr.next();

			if (cur.getXTile() == x && cur.getYTile() == y)
				return cur;
		}

		return null;
	}

	public Player getPlayer() {
		Iterator<Character> itr = characters_.iterator();

		Character cur;
		while (itr.hasNext()) {
			cur = itr.next();

			if (cur instanceof Player)
				return (Player) cur;
		}

		return null;
	}

	public Character getCharacterAtExcluding(int x, int y, Character a) {
		Iterator<Character> itr = characters_.iterator();

		Character cur;
		while (itr.hasNext()) {
			cur = itr.next();
			if (cur == a) {
				continue;
			}

			if (cur.getXTile() == x && cur.getYTile() == y) {
				return cur;
			}

		}

		return null;
	}

	public Explosion getExplosionAt(int x, int y) {
		Iterator<Boom> bs = booms_.iterator();
		Boom b;
		while (bs.hasNext()) {
			b = bs.next();

			Explosion e = b.explosionAt(x, y);
			if (e != null) {
				return e;
			}

		}

		return null;
	}

	public Entity getEntityAt(double x, double y) {
		if (x < 0 || x > getWidth() - 1 || y < 0 || y > getHeight() - 1) {
			return new GrassTile((int) x, (int) y, Sprite.grass, SpriteImage.images_map_grass);
		}
		return entities_[(int) x + (int) y * level_.getWidth()];
	}

	// --------------------------------------------------------------------------
	// | Adds and Removes
	// --------------------------------------------------------------------------
	public void addEntitie(int pos, Entity e) {
		entities_[pos] = e;
	}

	public void addCharacter(Character e) {
		characters_.add(e);
	}

	public void addBoom(Boom e) {
		booms_.add(e);
	}

	public void addMessage(Message e) {
		messages_.add(e);
	}

	// --------------------------------------------------------------------------
	// | Renders
	// --------------------------------------------------------------------------
	protected void renderEntities(Screen screen) {
		for (int i = 0; i < entities_.length; i++) {
			entities_[i].render(screen);
		}
	}

	protected void renderBooms(Screen screen) {
		Iterator<Boom> itr = booms_.iterator();

		while (itr.hasNext())
			itr.next().render(screen);
	}

	protected void renderCharacters(Screen screen) {
		Iterator<Character> itr = characters_.iterator();

		while (itr.hasNext())
			itr.next().render(screen);
	}

	public void renderMessages(Graphics g) {
		Message m;
		for (int i = 0; i < messages_.size(); i++) {
			m = messages_.get(i);

			g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
			g.setColor(m.getColor());
			g.drawString(m.getMessage(), (int) m.getX() - Screen.xOffset_ * Game.SCALE, (int) m.getY());
		}
	}

	// --------------------------------------------------------------------------
	// | Renders
	// --------------------------------------------------------------------------
	protected void drawEntities(Screen screen, Graphics graphics) {
		for (int i = 0; i < entities_.length; i++) {
			entities_[i].draw(screen, graphics);
		}
	}

	protected void drawBooms(Screen screen, Graphics graphics) {
		Iterator<Boom> itr = booms_.iterator();

		while (itr.hasNext())
			itr.next().draw(screen, graphics);
	}

	protected void drawCharacters(Screen screen, Graphics graphics) {
		Iterator<Character> itr = characters_.iterator();

		while (itr.hasNext())
			itr.next().draw(screen, graphics);
	}

	// --------------------------------------------------------------------------
	// | Updates
	// --------------------------------------------------------------------------
	protected void updateEntities() {
		if (game_.isPaused())
			return;
		for (int i = 0; i < entities_.length; i++) {
			entities_[i].update();
		}
	}

	protected void updateCharacters() {
		if (game_.isPaused())
			return;
		Iterator<Character> itr = characters_.iterator();

		while (itr.hasNext() && !game_.isPaused())
			itr.next().update();
	}

	protected void updateBooms() {
		if (game_.isPaused())
			return;
		Iterator<Boom> itr = booms_.iterator();

		while (itr.hasNext())
			itr.next().update();
	}

	protected void updateMessages() {
		if (game_.isPaused())
			return;
		Message m;
		int left = 0;
		for (int i = 0; i < messages_.size(); i++) {
			m = messages_.get(i);
			left = m.getDuration();

			if (left > 0)
				m.setDuration(--left);
			else
				messages_.remove(i);
		}
	}

	// --------------------------------------------------------------------------
	// | Getters & Setters
	// --------------------------------------------------------------------------
	public Keyboard getInput() {
		return input_;
	}

	public Level getLevel() {
		return level_;
	}

	public Game getGame() {
		return game_;
	}

	public int getShow() {
		return screenToShow_;
	}

	public void setShow(int i) {
		screenToShow_ = i;
	}

	public int getTime() {
		return time_;
	}

	public int increaseTime() {
		if (game_.isPaused())
			return this.time_;
		else
			return this.time_++;
	}

	public int getPoints() {
		return points_;
	}

	public void addPoints(int points) {
		this.points_ += points;
	}

	public int getLives() {
		return lives_;
	}

	public void addLives(int lives) {
		this.lives_ += lives;
	}

	public int getHighScores() {
		return highScore_;
	}

	public int getWidth() {
		return level_.getWidth();
	}

	public int getHeight() {
		return level_.getHeight();
	}

}
