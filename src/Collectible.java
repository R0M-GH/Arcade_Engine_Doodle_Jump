import engine.Actor;

public abstract class Collectible extends Actor {
	public Collectible() {
	}

	public abstract void onCollect(Player player);
}
