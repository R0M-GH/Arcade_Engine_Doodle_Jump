import java.io.File;

import engine.Actor;
import javafx.scene.image.Image;

public class Platform extends Actor {
	public Platform(double x, double y, double width, double height) {
		this.setImage(new Image("file:img" + File.separator + "normalplatform.png"));
		this.setFitWidth(width);
		this.setFitHeight(height);
		this.setX(x);
		this.setY(y);
	}

	public void collide() {
	}

	@Override
	public void act(long now) {
		if (this.getY() > getWorld().getHeight()) {
			this.remove();
		}
	}
}
