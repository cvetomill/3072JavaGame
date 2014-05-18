package game3072;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GameUI extends Application {
	static GridPane grid = new GridPane();
	static Stage globalStage;
	static Scene globalScene;
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		globalStage=primaryStage;
		final GridPane grid = new GridPane();
		globalStage.setTitle("Game 3072");
		
		 globalScene = new Scene(grid, 500, 600);
		globalScene.addEventHandler(KeyEvent.KEY_PRESSED, Engine.keyEventHandler);
		
		fillGrid(grid, Engine.map);
		styleGrid(grid);
		
		primaryStage.setScene(globalScene);
		primaryStage.show();
	}
	
public static void	updateStage(){
		grid=new GridPane();
		updateGrid(grid, Engine.map);
		styleGrid(grid);
		
		globalScene=new Scene(grid,500,600);
		globalScene.addEventHandler(KeyEvent.KEY_PRESSED, Engine.keyEventHandler);
		globalStage.setScene(globalScene);
		globalStage.show();
	}
	
	private static void updateGrid (GridPane pane, short[][] map){
		pane.getChildren().clear();
		fillGrid(pane,map);
	}
		
	private static void fillGrid(GridPane pane, short[][] map) {
		// place element different than zero
		short current = 0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
 				current = map[row][col];
				if (current != 0) {
					pane.add(new Label(String.valueOf(current)), row, col);

				} else {
					pane.add(new Label(""), row, col);
				}
			}
		}

	}
	
	public static void styleGrid(GridPane pane) {

		pane.setStyle("-fx-background-color: teal; -fx-hgap: 5; -fx-vgap: 5;-fx-alignment: center;");
		pane.setGridLinesVisible(true);
		pane.setSnapToPixel(true);
		pane.getChildren();

		ColumnConstraints oneThird = new ColumnConstraints();
		oneThird.setPercentWidth(100 / 5.0);
		oneThird.setHalignment(HPos.CENTER);

		pane.getColumnConstraints().addAll(oneThird, oneThird, oneThird, oneThird);
		RowConstraints oneHalf = new RowConstraints();
		oneHalf.setPercentHeight(100 / 5.0);
		oneHalf.setValignment(VPos.CENTER);
		pane.getRowConstraints().addAll(oneHalf, oneHalf, oneHalf, oneHalf);

		for (Node node : pane.getChildren()) {
			if (node instanceof Control) {
				Control control = (Control) node;
				control.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				control.setStyle("-fx-background-color: cornsilk; -fx-alignment: center;");
			}
		}
	}
	
	public static void main(String[] args) {
		Engine.GetRandomPosition();
		fillGrid(grid, Engine.map);
		
		launch(args);		
	}
}
