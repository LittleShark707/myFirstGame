package contents;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class resource { //allows access to images
	public static BufferedImage getresourceImage(String path) {
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pic;
	}

}
