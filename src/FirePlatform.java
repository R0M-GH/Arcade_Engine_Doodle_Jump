import java.io.File;

import javafx.scene.image.Image;

public class FirePlatform extends Platform {
	Image i1 = new Image("file:img" + File.separator + "fireplatform1.png",
			40, 40, true, true);
	Image i2 = new Image("file:img" + File.separator + "fireplatform2.png",
			40, 40, true, true);
	Image i3 = new Image("file:img" + File.separator + "fireplatform3.png",
			40, 40, true, true);
	long prev;

	public FirePlatform(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.setImage(i1);
	}

	@Override
	public void collide() {
		
	}

	@Override
	public void act(long now) {
		super.act(now);
		if (now - prev > 100000000) {
			if (this.getImage() == i1) {
				this.setImage(i2);
			} else if (this.getImage() == i2) {
				this.setImage(i3);
			} else if (this.getImage() == i3) {
				this.setImage(i1);
			}
			prev = now;
		}
		
		Player p = getOneIntersectingObject(Player.class);


	}
}
