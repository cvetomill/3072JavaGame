package game3072;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameUI extends Application {
	static GridPane grid = new GridPane();

	@Override
	public void start(Stage primaryStage) throws Exception {

		Scene scene = new Scene(grid, 500, 600);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, Engine.keyEventHandler);

		fillGrid(grid, Engine.map);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static void fillGrid(GridPane pane, short[][] map) {
		short current = 0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				current = map[row][col];
				if (current != 0) {
					pane.add(new Label(String.valueOf(current)), row, col);
				}
			}
		}

	}

	public static void main(String[] args) {
		
		Engine.GetRandomPosition();
		fillGrid(grid, Engine.map);
		//style grid
		launch(args);
		//...
		
	}
}
