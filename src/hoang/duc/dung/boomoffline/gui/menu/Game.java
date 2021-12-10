package hoang.duc.dung.boomoffline.gui.menu;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.gui.CodeDialog;
import hoang.duc.dung.boomoffline.gui.Container;
import hoang.duc.dung.boomoffline.gui.Frame;
import hoang.duc.dung.boomoffline.gui.InfoDialog;
import hoang.duc.dung.boomoffline.input.IOClass;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Game extends JMenu {

	private Frame frame_;
	private Container container_;
	private String highScores_ = "Ops";

	public Game(Frame frame, Container container) {
		super("Game");
		frame_ = frame;
		container_ = container;

		/*
		 * New Game
		 */
		JMenuItem newgame = new JMenuItem("New Game");
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newgame.addActionListener(new MenuActionListener(frame));
		add(newgame);

		/*
		 * Main menu
		 */
		JMenuItem mainmenu = new JMenuItem("Main menu");
		mainmenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		mainmenu.addActionListener(new MenuActionListener(frame));
		add(mainmenu);

		/*
		 * Scores
		 */
		JMenuItem scores = new JMenuItem("High Scores");
		scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		scores.addActionListener(new MenuActionListener(frame));
		add(scores);

		/*
		 * Codes
		 */
		JMenuItem codes = new JMenuItem("Codes");
		codes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		codes.addActionListener(new MenuActionListener(frame));
		add(codes);

		highScores_ = "High scores: " + IOClass.Read();
	}

	class MenuActionListener implements ActionListener {
		public Frame frame_;

		public MenuActionListener(Frame frame) {
			frame_ = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("New Game")) {
				frame_.newGame();
			}

			if (e.getActionCommand().equals("Main menu")) {
				container_.showCard(Container.MENU_PANEL);
				frame_.newGame();
			}

			if (e.getActionCommand().equals("High Scores")) {
				new InfoDialog(frame_, "High Scores", highScores_, JOptionPane.INFORMATION_MESSAGE);
			}

			if (e.getActionCommand().equals("Codes")) {
				new CodeDialog(frame_);
			}
		}
	}
}
