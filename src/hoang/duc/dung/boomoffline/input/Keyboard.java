package hoang.duc.dung.boomoffline.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys_ = new boolean[120]; //120 is enough to this game
	public boolean up_, down_, left_, right_, space_;
	
	public void update() {
		up_ = keys_[KeyEvent.VK_UP] || keys_[KeyEvent.VK_W];
		down_ = keys_[KeyEvent.VK_DOWN] || keys_[KeyEvent.VK_S];
		left_ = keys_[KeyEvent.VK_LEFT] || keys_[KeyEvent.VK_A];
		right_ = keys_[KeyEvent.VK_RIGHT] || keys_[KeyEvent.VK_D];
		space_ = keys_[KeyEvent.VK_SPACE] || keys_[KeyEvent.VK_X];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys_[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys_[e.getKeyCode()] = false;
		
	}

}
