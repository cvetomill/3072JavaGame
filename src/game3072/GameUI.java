package game3072;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 500, 600);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, GameMain.keyEventHandler);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}
}
