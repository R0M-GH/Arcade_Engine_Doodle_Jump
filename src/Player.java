import java.io.File;
import java.util.List;

import engine.Actor;
import javafx.scene.image.Image;

public class Player extends Actor {
	private double dy, dx;
	private int coins, lives;
	private int sum = 0;
	private boolean isGameOver = false;

	// public boolean touchingBottomOnce = this.getY() > this.getWorld().getHeight()
	// - this.getHeight();
	public Player() {
		try {
			Image i = new Image("file:img" + File.separator + "doodle.png");
			this.setFitWidth(90);
			this.setFitHeight(100);
			this.setImage(i);
		} catch (Exception e) {
		}

		dx = 0;
		dy = 0;
		coins = 0;
		lives = 3;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	@Override
	public void act(long now) {

		if (isGameOver() == false) {

			List<Actor> list = getWorld().getObjects(Actor.class);
			for (Actor p : list) {
				if (p != this) {
					p.move(0, -dy);
				}
			}
			sum += dy;

			setHeightScore(-sum);

			this.move(dx, 0);
			boolean contact;
			Platform blok = this.getOneIntersectingObject(Platform.class);
			if (blok != null) {
				contact = true;
			} else {
				contact = false;
			}

			dy += 0.5;

			if (this.getX() + this.getWidth() / 2 < 0) {
				this.setX(getWorld().getWidth() - this.getWidth() / 2);
			} else if (this.getX() + this.getWidth() / 2 > getWorld().getWidth()) {
				this.setX(-this.getWidth() / 2);
			}

			boolean touchingBottom = contact && this.getY() + this.getHeight() > blok.getY() && this.dy > 0;

			if (touchingBottom) {

				dy = -18;
				dx = 0;

				if (blok instanceof BrokenPlatform) {
					dy = 0;
					blok.remove();
				} else if (blok instanceof FirePlatform) {
					setGameOver(true);
				}
			}

			((Level) getWorld()).setCoins(coins);
		}
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean x) {
		isGameOver = x;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void addCoin() {
		coins++;
	}

	public void addCoins(int n) {
		coins += n;
	}

	public int getCoins() {
		return coins;
	}

	public void addLife() {
		if (lives < 3) {
			lives++;
		}
	}

	public int getLives() {
		return lives;
	}

	public int getHeightScore() {
		return ((Level) getWorld()).getHeightScore();
	}

	public void setHeightScore(int n) {
		((Level) getWorld()).setHeightScore(n);
	}

	public void pauseGame() {
		isGameOver = true;
	}

	public void unPause() {
		isGameOver = false;
	}
}
