import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

public class GameOverMenu extends World {
	private World w = this;
	int score;
	int highscore;
	Scanner in;

	public GameOverMenu(int score) {
		this.score = score;
		try {
			in = new Scanner(new File("highscore.txt"));
		} catch (FileNotFoundException e) {
		}
		this.highscore = in.nextInt();

		if (score > highscore) {
			highscore = score;
			try {
				FileWriter fw = new FileWriter("highscore.txt");
				fw.write(Integer.toString(highscore));
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDimensionsInitialized() throws FileNotFoundException {
		BorderPane root = (BorderPane) this.getScene().getRoot();
		Image i = new Image("file:img" + File.separator + "gameover.png", 475,
				950, true, true);
		BackgroundImage bgImage = new BackgroundImage(i,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background back = new Background(bgImage);
		root.setBackground(back);
		// Text txt = 

		this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE) {
					TitleMenu world = new TitleMenu();
					world.start();
					root.setPrefSize(405, 705);
					world.setPrefHeight(4000);
					world.setPrefWidth(650);
					world.requestFocus();
				}
			}
		});
	}
}
