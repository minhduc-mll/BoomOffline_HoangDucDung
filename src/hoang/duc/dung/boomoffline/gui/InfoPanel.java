package hoang.duc.dung.boomoffline.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import hoang.duc.dung.boomoffline.Game;

public class InfoPanel extends JPanel {

	private JLabel levelLabel_;
	private JLabel timeLabel_;
	private JLabel pointsLabel_;
	private JLabel livesLabel_;
	private JLabel highScoreLabel_;

	public InfoPanel(Game game) {
		setLayout(new GridLayout());

		levelLabel_ = new JLabel("Level: " + game.getBoard().getLevel().getLevel());
		levelLabel_.setForeground(Color.WHITE);
		levelLabel_.setHorizontalAlignment(JLabel.CENTER);

		timeLabel_ = new JLabel("Time: " + game.getBoard().getTime());
		timeLabel_.setForeground(Color.WHITE);
		timeLabel_.setHorizontalAlignment(JLabel.CENTER);

		livesLabel_ = new JLabel("Lives: " + game.getBoard().getLives());
		livesLabel_.setForeground(Color.WHITE);
		livesLabel_.setHorizontalAlignment(JLabel.CENTER);

		pointsLabel_ = new JLabel("Points: " + game.getBoard().getPoints());
		pointsLabel_.setForeground(Color.WHITE);
		pointsLabel_.setHorizontalAlignment(JLabel.CENTER);

		highScoreLabel_ = new JLabel("High score: " + game.getBoard().getHighScores());
		highScoreLabel_.setForeground(Color.GREEN);
		highScoreLabel_.setHorizontalAlignment(JLabel.CENTER);

		add(levelLabel_);
		add(timeLabel_);
		add(livesLabel_);
		add(pointsLabel_);
		add(highScoreLabel_);

		setBackground(Color.darkGray);
		setPreferredSize(new Dimension(0, 40));
	}

	public void setTime(int t) {
		timeLabel_.setText("Time: " + t);
	}

	public void setLives(int t) {
		livesLabel_.setText("Lives: " + t);

	}

	public void setPoints(int t) {
		pointsLabel_.setText("Points: " + t);
	}

	public JLabel getLivesLabel() {
		return livesLabel_;
	}

}
