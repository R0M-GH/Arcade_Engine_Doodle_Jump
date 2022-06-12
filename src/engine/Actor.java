package engine;

import java.util.ArrayList;

public abstract class Actor extends javafx.scene.image.ImageView {
	public Actor() {}

	public abstract void act(long now);

	// Why is this here?
	public void addedToWorld() {}

	public void remove() {
		getWorld().remove(this);
	}

	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}

	public double getWidth() {
		return this.getBoundsInParent().getWidth();
	}

	public World getWorld() {
		return (World) this.getParent();
	}

	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}

	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (A actor : this.getWorld().getObjects(cls)) {
			if (actor != this && cls.isAssignableFrom(actor.getClass())
					&& this.intersects(actor.boundsInLocalProperty().get())) {
				list.add(actor);
			}
		}
		return list;
	}

	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		for (A actor : this.getWorld().getObjects(cls)) {
			if (actor != this && cls.isAssignableFrom(actor.getClass())
					&& this.intersects(actor.boundsInLocalProperty().get())) {
				return actor;
			}
		}
		return null;
	}

	public boolean xyInImage(double x, double y) {
		boolean xInBounds = x >= this.getX() && x <= this.getX() + this.getImage().getWidth();
		boolean yInBounds = y >= this.getY() && y <= this.getY() + this.getImage().getHeight();
		return xInBounds && yInBounds;
	}

	public boolean isInWorld() {
		if (this.getX() < 0 || this.getX() > this.getWorld().getWidth()
				|| this.getY() < 0 || this.getY() > this.getWorld().getHeight()) {
			return false;
		}
		return true;
	}
}
