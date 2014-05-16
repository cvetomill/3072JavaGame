package game3072;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane grid = new GridPane();
		Scene scene = new Scene(grid, 500, 600);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, Engine.keyEventHandler);

		fillGrid(grid, Engine.map);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void fillGrid(GridPane pane, short[][] map){
		short current=0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				current=map[row][col];
				if (current!=0) {
					pane.add(new Label(String.valueOf(current)), row, col);
				}
			}
		}
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
