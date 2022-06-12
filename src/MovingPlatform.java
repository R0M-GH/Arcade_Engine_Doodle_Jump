import java.io.File;

import javafx.scene.image.Image;

public class MovingPlatform extends Platform {
	Image i1 = new Image("file:img" + File.separator + "movingplatform.png");
	int dx;

	public MovingPlatform(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.setImage(i1);
		dx = 1;
	}

	@Override
	public void act(long now) {
		if (this != null) {
			super.act(now);
			this.move(dx, 0);

			if (this.getX() > this.getWorld().getWidth() - this.getWidth() || this.getX() < 0) {
				dx = -dx;
			}
		}
	}
}
