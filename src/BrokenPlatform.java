import java.io.File;

import javafx.scene.image.Image;

public class BrokenPlatform extends Platform {
	int counter;
	private Image i1 = new Image("file:img" + File.separator + "brokenplatform.png");

	public BrokenPlatform(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.setImage(i1);
		counter = 1;
	}

	@Override
	public void collide() {
		super.collide();
		counter--;
	}

	@Override
	public void act(long now) {
		super.act(now);
		if (counter == 0) {
			this.remove();
		}
	}
}
