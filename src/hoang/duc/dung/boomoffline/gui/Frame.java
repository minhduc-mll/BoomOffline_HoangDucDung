package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.gui.menu.Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class Frame extends JFrame {

	public GamePanel gamePanel_;

	private InfoPanel infoPanel_;

	private Game game_;
	private Container container_;
	private JPanel playGame_;

	public Frame() {
		container_ = new Container(this);

		setJMenuBar(new Menu(this, container_));

		gamePanel_ = new GamePanel(this);
		infoPanel_ = new InfoPanel(gamePanel_.getGame());

		playGame_ = new JPanel(new BorderLayout());
		playGame_.add(infoPanel_, BorderLayout.PAGE_START);
		playGame_.add(gamePanel_, BorderLayout.PAGE_END);

		container_.add(playGame_, Container.GAME_PANEL);

		game_ = gamePanel_.getGame();

		// setSize(720, 720);

		add(container_);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();

		setLocationRelativeTo(null);
		setVisible(true);

		game_.start();
	}

	// --------------------------------------------------------------------------
	// | Game Related
	// --------------------------------------------------------------------------
	public void newGame() {
		game_.getBoard().newGame();
	}

	public void changeLevel(int i) {
		game_.getBoard().changeLevel(i);
	}

	public void pauseGame() {
		game_.getBoard().gamePause();
	}

	public void resumeGame() {
		game_.getBoard().gameResume();
	}

	public boolean isRunning() {
		return game_.isRunning();
	}

	public void setTime(int time) {
		infoPanel_.setTime(time);
	}

	public void setLives(int lives) {
		infoPanel_.setLives(lives);
	}

	public void setPoints(int points) {
		infoPanel_.setPoints(points);
	}

	public boolean validCode(String str) {
		return game_.getBoard().getLevel().validCode(str) != -1;
	}

	public void changeLevelByCode(String str) {
		game_.getBoard().changeLevelByCode(str);
	}

}
