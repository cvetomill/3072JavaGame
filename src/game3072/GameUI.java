package game3072;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GameUI extends Application {
	static GridPane grid = new GridPane();	
	
	@Override
	public void start(final Stage primaryStage) throws Exception {

		final GridPane grid = new GridPane();
		primaryStage.setTitle("Game 3072");
		
		final Scene scene = new Scene(grid, 500, 600);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
		
		fillGrid(grid, Engine.map);
		styleGrid(grid);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.LEFT) {
				game3072.Movement.moveLeft();
				//System.out.println("Left"); 	// Left arrow code and movement here
			} else if (keyEvent.getCode() == KeyCode.RIGHT) {
				game3072.Movement.moveRight();
				//System.out.println("right");// Right arrow code and movement here
			} else if (keyEvent.getCode() == KeyCode.UP) {
				game3072.Movement.moveUp();
				//System.out.println("up");// Up arrow code and movement here		
			} else if (keyEvent.getCode() == KeyCode.DOWN) {
				game3072.Movement.moveDown();
				//System.out.println("down");// Down arrow code and movement here
			}
			
			Task task = new Task<Void>() {
				  @Override
				  public Void call() throws Exception {
				      Platform.runLater(new Runnable() {
				        @Override
				        public void run() {
							updateGrid(grid, Engine.map);
							styleGrid(grid);
				        }
				      });
				      Thread.sleep(10);
				      return null;
				  }
				};
				Thread th = new Thread(task);
				th.setDaemon(true);
				th.start();
			
			keyEvent.consume();
		}
	};
	
	private static void updateGrid (GridPane pane, short[][] map){
		pane.getChildren().clear();
		short current = 0;
		for (int row = 0; row < map.length;row++){
			for (int col =0; col < map[row].length;col++){
				current = map[row][col];
				if (current != 0) {
					pane.add(new Label(String.valueOf(current)), row, col);

				} else {
					pane.add(new Label(""), row, col);
				}
			}
		}
		System.out.println("updateGrid");
	}
		
	private static void fillGrid(GridPane pane, short[][] map) {
		// create grid rows and columns

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

		pane.setStyle("-fx-background-color: teal;-fx-hgap: 5; -fx-vgap: 5; -fx-alignment: center;");
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
