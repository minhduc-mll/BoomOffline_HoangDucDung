package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.Game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CodeDialog implements WindowListener, ActionListener {

	private Frame frame_;
	private JFrame dialog_;
	private JTextField code_;
	private boolean valid_ = false;

	public CodeDialog(Frame frame) {
		frame_ = frame;

		dialog_ = new JFrame("Enter a Valid Code");
		final JButton button = new JButton("Load!");
		button.addActionListener(this);

		JPanel pane = new JPanel(new BorderLayout());
		code_ = new JTextField("level");

		pane.add(new JLabel("Code: "), BorderLayout.WEST);
		pane.add(code_, BorderLayout.CENTER);
		pane.add(button, BorderLayout.PAGE_END);

		dialog_.getContentPane().add(pane);
		dialog_.setLocationRelativeTo(frame);
		dialog_.setSize(400, 100);
		dialog_.setVisible(true);
		dialog_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog_.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		frame_.pauseGame();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(valid_ == false)
			frame_.resumeGame();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = code_.getText();

		if(frame_.validCode(code)) {
			frame_.changeLevelByCode(code);
			valid_ = true;
			dialog_.dispose();
		} else {
			JOptionPane.showMessageDialog(frame_,
				    "That code isnt correct boy..",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
