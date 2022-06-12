package engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class World extends javafx.scene.layout.Pane {
	private AnimationTimer timer;
	private final World w = this;
	private HashSet<KeyCode> keysPressed;
	private boolean widthSet, heightSet, timerRunning;
	private MyDimensionsChangeListener dimListener = new MyDimensionsChangeListener();
	private MySceneChangeListener sceneListener = new MySceneChangeListener();
	private long delay = 50000000, prev = 0;
	private boolean initialized = false;

	public World() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (now - prev >= delay) {
					w.act(now);

					ArrayList<Actor> list = (ArrayList<Actor>) getObjects(Actor.class);
					for (Actor a : list) {
						if (a.getWorld() != null) {
							a.act(now);
						}
					}
				}
			}
		};

		widthSet = false;
		heightSet = false;
		timerRunning = false;
		keysPressed = new HashSet<>();
		widthProperty().addListener(dimListener);
		heightProperty().addListener(dimListener);
		sceneProperty().addListener(sceneListener);

		this.setOnKeyPressed(new MyKeyHandlerAdder());
		this.setOnKeyReleased(new MyKeyHandlerRemover());

	}

	public abstract void act(long now);

	public void start() {
		timer.start();
		timerRunning = true;
	}

	public void stop() {
		timer.stop();
		timerRunning = false;
	}

	public void add(Actor actor) {
		this.getChildren().add(actor);
		actor.addedToWorld();
	}

	public void remove(Actor actor) {
		getChildren().remove(actor);
	}

	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (Node actor : getChildren()) {
			if (cls.isInstance(actor)) {
				list.add(cls.cast(actor));
			}
		}
		return list;
	}

	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (A a : getObjects(cls)) {
			if (a.getX() <= x && a.getX() + a.getWidth() >= x && a.getY() <= y && a.getY() + a.getHeight() >= y) {
				list.add(a);
			}

		}
		return list;
	}

	public void addKeyCode(KeyCode keyCode) {
		keysPressed.add(keyCode);
	}

	public void removeKeyCode(KeyCode keyCode) {
		keysPressed.remove(keyCode);
	}

	public boolean isKeyPressed(KeyCode keyCode) {
		return keysPressed.contains(keyCode);
	}

	public boolean isStopped() {
		return !timerRunning;
	}

	public abstract void onDimensionsInitialized() throws FileNotFoundException;

	private class MyDimensionsChangeListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if ((double) newValue > 0 && !widthSet) {
				w.setWidth((double) newValue);
				widthSet = true;
			} else if ((double) newValue > 0 && !heightSet) {
				w.setHeight((double) newValue);
				heightSet = true;
			}

			if (widthSet && heightSet && !initialized) {
				try {
					onDimensionsInitialized();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				initialized = true;
			}
		}
	}

	private class MySceneChangeListener implements ChangeListener<Scene> {
		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
			if (newValue != null) {
				requestFocus();
			}
		}
	}

	private class MyKeyHandlerAdder implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			keysPressed.add(event.getCode());

		}
	}

	private class MyKeyHandlerRemover implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent event) {
			keysPressed.remove(event.getCode());

		}
	}
}
