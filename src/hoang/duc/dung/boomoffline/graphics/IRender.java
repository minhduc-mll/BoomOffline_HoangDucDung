package hoang.duc.dung.boomoffline.graphics;

import java.awt.*;

public interface IRender {

	public void update();
	
	public void render(Screen screen);

	public void draw(Screen screen, Graphics graphics);
}
