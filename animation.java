package contents;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class animation {
	private List<BufferedImage> frames;
	private int index = 0;
	private int time;
	private long prtime;
	
	public animation(int time) {
		this.time = time;
		frames = new ArrayList<BufferedImage>();
	}
	
	public void update() {
		if(System.currentTimeMillis() - prtime > time) {
			index++;
			if(index >= frames.size()) {
				index = 0;
			}
			prtime = System.currentTimeMillis();
		}
	}
	
	public void addFrame(BufferedImage frame) {
		frames.add(frame);
	}
	
	public BufferedImage getFrame() {
		if(frames.size() > 0) {
			return frames.get(index);
		}
		return null;
	}
}
