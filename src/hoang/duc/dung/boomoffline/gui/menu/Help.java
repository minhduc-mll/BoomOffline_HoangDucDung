package hoang.duc.dung.boomoffline.gui.menu;

import hoang.duc.dung.boomoffline.gui.Frame;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.gui.InfoDialog;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Help extends JMenu {

	public Help(Frame frame)  {
		super("Help");
		
		/*
		 * How to play
		 */
		JMenuItem instructions = new JMenuItem("How to play");
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		instructions.addActionListener(new MenuActionListener(frame));
		add(instructions);
		
		/*
		 * Credits
		 */
		JMenuItem about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		about.addActionListener(new MenuActionListener(frame));
		add(about);
		
	}
	
	class MenuActionListener implements ActionListener {
		public Frame frame_;
		public MenuActionListener(Frame frame) {
			frame_ = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("How to play")) {
				  new InfoDialog(frame_, "How to Play", "Movement: W,A,S,D or UP,DOWN, RIGHT, LEFT\nPut Booms: SPACE, X", JOptionPane.QUESTION_MESSAGE);
			  }
				  
			  if(e.getActionCommand().equals("About")) {
				  String about = "\n\n" +
						  "Trường Đại học Công nghệ - Đại học Quốc gia Hà Nội.\n" +
						  "Sinh viên\n" +
						  "1. Quốc Dũng\n" +
						  "<https://github.com/Dung24-6>\n" +
						  "2. Huy Hoàng\n" +
						  "<https://github.com/ktshglsm>\n" +
						  "3. Minh Đức\n" +
						  "<https://github.com/minhduc-mll>\n";
				  new InfoDialog(frame_, "About", "Version: " + Game.VERSION + about, JOptionPane.INFORMATION_MESSAGE);
			  }
			  
		  }
	}
}
