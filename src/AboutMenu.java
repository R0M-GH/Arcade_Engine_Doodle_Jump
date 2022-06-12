import java.io.File;
import java.io.FileNotFoundException;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class AboutMenu extends World {
	World w = this;

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDimensionsInitialized() throws FileNotFoundException {
		BorderPane root = (BorderPane) this.getScene().getRoot();
		StackPane sp = new StackPane();
		root.setCenter(sp);
		ImageView about = new ImageView(new Image("file:img" + File.separator + "about.png",
				475, 2000, true, true));
		((StackPane) ((BorderPane) w.getScene().getRoot()).getCenter()).getChildren().add(about);

		this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					root.setCenter(null);
				}
			}
		});
	}
}
