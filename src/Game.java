import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
	BorderPane root;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setTitle("Game");

			root = new BorderPane();

			root.setPrefSize(475, 705);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			Alert a = new Alert(Alert.AlertType.CONFIRMATION);
			a.setContentText("To start playing the game click P, as instructed in the title screen. To open the menu screen or pause the game click P, once at the menu screen you can resume the game by clicking R, quit by clicking Q, or click A to view the about page(to leave the about page click escape).  ");
			a.show();

			TitleMenu world = new TitleMenu();
			root.setCenter(world);
		} catch (Exception e) {
		}
	}
}
