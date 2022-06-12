import java.io.File;

import engine.Actor;
import javafx.scene.image.Image;

public class Monster extends Actor {
	long prev;
	long prev2;
	double x;
	double y;
	boolean t = true;
	Projectile p;

	boolean up = true;
	Image i1 = new Image("file:img" + File.separator + "FlyingMonster1.png",
			75, 75, true, true);
	Image i2 = new Image("file:img" + File.separator + "FlyingMonster2.png",
			75, 75, true, true);
	Image i3 = new Image("file:img" + File.separator + "FlyingMonster3.png",
			75, 75, true, true);

	public Monster(double x, double y) {
		this.setFitWidth(90);
		this.setFitHeight(100);
		this.x = x;
		this.y = y;
		this.setImage(i1);
		this.setX(x);
		this.setY(y);
	}

	@Override
	public void act(long now) {

		if (this.getY() > getWorld().getHeight()) {
			this.remove();
		}

		if (now - prev > 100000000) {
			if (this.getImage() == i1) {
				this.setImage(i2);
			} else if (this.getImage() == i2) {
				this.setImage(i3);
			} else if (this.getImage() == i3) {
				this.setImage(i1);
			}
			prev = now;

			if (t) {
				t = false;
				p = new Projectile(x, y + getHeight() / 2);
				getWorld().add(p);

			}

		}

		// if(p == null){
		// p = new Projectile(x, y+15);
		// getWorld().add(new Projectile(x, y+15));
		// }
		//
		// boolean found = false;
		// for(Projectile p1 : getWorld().getObjects(Projectile.class)){
		// if(p == p1){
		// found = true;
		// break;
		// }
		// }

		// if(!found){
		// p = null;
		// }

		Player p = getOneIntersectingObject(Player.class);

		if (p != null) {
			p.setGameOver(true);
		}
	}
}
