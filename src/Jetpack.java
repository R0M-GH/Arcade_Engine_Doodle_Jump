import java.io.File;

import javafx.scene.image.Image;

public class Jetpack extends Collectible {

	// IDEA BEEN SCRAPPED

	public Jetpack(double x, double y, double width, double height) {
		this.setImage(new Image("file:img" + File.separator + "jetpack1.png"));
	}

	@Override
	public void onCollect(Player player) {
		// Make the player sprite have a jetpack, and remove acceleration downward
		// Set a timer for 5 seconds, then remove the jetpack
	}

	@Override
	public void act(long now) {
		// zoom
	}
}
