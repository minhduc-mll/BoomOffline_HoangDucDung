package hoang.duc.dung.boomoffline.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path_;
	public final int SIZE;
	public int[] pixels_;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/classic.png", 256);
	
	public SpriteSheet(String path, int size) {
		path_ = path;
		SIZE = size;
		pixels_ = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL a = SpriteSheet.class.getResource(path_);
			BufferedImage image = ImageIO.read(a);
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels_, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: what should this do? stop the program? yes bro, it'll stop.
			System.exit(0);
		}
	}
}
