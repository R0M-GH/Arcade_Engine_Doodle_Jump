import java.io.File;
import java.io.FileNotFoundException;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class TitleMenu extends World {
	World w = this;

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDimensionsInitialized() throws FileNotFoundException {
		BorderPane root = (BorderPane) this.getScene().getRoot();
		Image i = new Image("file:img" + File.separator + "startScreen.png", 475,
				950, true, true);
		BackgroundImage bgImage = new BackgroundImage(i,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background back = new Background(bgImage);
		root.setBackground(back);

		root.setPrefSize(405, 705);
		this.setPrefHeight(4000);
		this.setPrefWidth(650);
		this.requestFocus();

		this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.P) {
					Level world = new Level();
					StackPane sp = new StackPane();
					root.setCenter(sp);
					sp.getChildren().add(world);

					root.setPrefSize(405, 705);
					world.setPrefHeight(4000);
					world.setPrefWidth(650);
					world.requestFocus();
					world.start();
				}

				if (event.getCode() == KeyCode.A) {
					AboutMenu world = new AboutMenu();
					StackPane sp = new StackPane();
					root.setCenter(sp);
					sp.getChildren().add(world);
					root.setPrefSize(405, 705);
					world.setPrefHeight(4000);
					world.setPrefWidth(650);
					world.requestFocus();
					world.start();
				}

				if (event.getCode() == KeyCode.Q) {
					System.exit(999);
				}
			}
		});
	}
}
