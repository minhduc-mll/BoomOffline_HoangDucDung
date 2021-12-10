package hoang.duc.dung.boomoffline.gui.menu;

import hoang.duc.dung.boomoffline.gui.Frame;
import hoang.duc.dung.boomoffline.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Options extends JMenu implements ChangeListener {

	Frame frame_;
	
	public Options(Frame frame) {
		super("Options");
		
		frame_ = frame;
		
		JMenuItem pause = new JMenuItem("Pause");
		pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		pause.addActionListener(new MenuActionListener(frame));
		add(pause);
		
		JMenuItem resume = new JMenuItem("Resume");
		resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		resume.addActionListener(new MenuActionListener(frame));
		add(resume);
		
		addSeparator();
		
		add(new JLabel("Size: "));
		
		JSlider sizeRange = new JSlider(JSlider.HORIZONTAL,
                1, 5, 3);
		
		//Turn on labels at major tick marks.
		sizeRange.setMajorTickSpacing(2);
		sizeRange.setMinorTickSpacing(1);
		sizeRange.setPaintTicks(true);
		sizeRange.setPaintLabels(true);
		sizeRange.addChangeListener(this);
		
		add(sizeRange);
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        int fps = (int)source.getValue();
	        
	        hoang.duc.dung.boomoffline.Game.SCALE = fps;
	        System.out.println( Game.SCALE );
	        
	        frame_.gamePanel_.changeSize();
			  frame_.revalidate();
			  frame_.pack();
	    }
	}
	
	class MenuActionListener implements ActionListener {
		public Frame frame_;
		public MenuActionListener(Frame frame) {
			frame_ = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("Pause")) {
				  frame_.pauseGame();
			  }
				  
			  if(e.getActionCommand().equals("Resume")) {
				  frame_.resumeGame();
			  }
		  }
	}
}
