package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.exceptions.BoomOfflineException;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class GamePanel extends JPanel {

	private Game game_;

	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		try {
			game_ = new Game(frame);

			add(game_);

			game_.setVisible(true);

		} catch (BoomOfflineException e) {
			e.printStackTrace();
			// TODO: so we got an error hum.. hmmmmmmmmmmm
			System.exit(0);
		}
		setVisible(true);
		setFocusable(true);

	}

	public void changeSize() {
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		revalidate();
		repaint();
	}

	public Game getGame() {
		return game_;
	}

}
