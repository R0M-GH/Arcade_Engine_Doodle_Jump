import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Coin extends Collectible {
	boolean r = true;

	// Media m = new Media(Paths.get("CoinSound.mp3").toUri().toString());
	Media media = new Media(new File("sound1.wav").toURI().toString());

	MediaPlayer Player = new MediaPlayer(media);
	Image i1 = new Image("file:img" + File.separator + "coin1.png",
			40, 40, true, true);
	Image i2 = new Image("file:img" + File.separator + "coin2.png",
			40, 40, true, true);
	Image i3 = new Image("file:img" + File.separator + "coin3.png",
			40, 40, true, true);
	long prev;

	public Coin(double x, double y) {
		this.setImage(i1);
		this.setX(x + 12.5);
		this.setY(y + 12.5);
	}

	@Override
	public void onCollect(Player player) {
		this.remove();
		
		
		
		player.addCoin();
		Player.play();
	}

	@Override
	public void act(long now) {
		if (this.getOneIntersectingObject(Player.class) != null) {
			System.out.println("h");
			this.onCollect(this.getOneIntersectingObject(Player.class));
			r = false;
		} else {
			if (this.getY() > getWorld().getHeight() && r == true) {
				this.remove();
			}
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
		}
	}
}
