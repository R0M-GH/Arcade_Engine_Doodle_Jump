import java.io.File;

import engine.Actor;
import javafx.scene.image.Image;

public class Projectile extends Actor {
	Image i1 = new Image("file:img" + File.separator + "FireBall.png", 35, 35, true, true);
	Image i2 = new Image("file:img" + File.separator + "FireBall1.png", 35, 35, true, true);
	int dx;
	double x;
	double y;

	public Projectile(double x, double y) {
		this.x = x;
		this.y = y;
		double chance = Math.random();
		if (chance < 0.5) {
			dx = -3;
			this.setImage(i1);
		} else {
			dx = 3;
			this.setImage(i2);
		}
		this.setX(x);
		this.setY(y);

		// this.setFitWidth(50);
		// this.setFitHeight(20);
	}

	@Override
	public void act(long now) {
		move(dx, 0);

		if (getX() > getWorld().getWidth() || getX() + getWidth() < 0) {
			dx = -dx;
		}
		
		Player p = getOneIntersectingObject(Player.class);

		if (p != null) {
			p.setGameOver(true);
		}
	}
}
