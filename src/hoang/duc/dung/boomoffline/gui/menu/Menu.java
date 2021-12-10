package hoang.duc.dung.boomoffline.gui.menu;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.gui.Container;
import hoang.duc.dung.boomoffline.gui.Frame;

import javax.swing.JMenuBar;

public class Menu extends JMenuBar {

	public Menu(Frame frame, Container container) {
		add(new Game(frame, container));
		add(new Options(frame));
		add(new Help(frame));
	}

}
