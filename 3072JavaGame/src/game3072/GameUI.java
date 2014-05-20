package game3072;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameUI extends Application {
	static GridPane grid = new GridPane();
	static Stage globalStage;
	static Scene globalScene;
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
        
        Label Score = new Label("Score");
        Score.setFont(new Font("Verdana", 20));
        Score.setLayoutX(220);
        Score.setLayoutY(80);
        
      //************************************************
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
		
		double scWidth=globalScene.getWidth();
		double scHeight=globalScene.getHeight();
		globalScene=new Scene(grid,scWidth,scHeight);
		globalScene.addEventHandler(KeyEvent.KEY_PRESSED, Engine.keyEventHandler);
		globalStage.setScene(globalScene);
		globalStage.show();
	}
	
	private static void updateGrid (GridPane pane, short[][] map){
		pane.getChildren().clear();
		fillGrid(pane,map);
	}
	
	public static void GameOverStage(){
		//************************************************
				// Define transparent scene
				
				 // label Game Over
		        Label GameOver = new Label("GAME OVER");
		        GameOver.setFont(new Font("Arial", 40));
		        GameOver.setLayoutX(220);
		        GameOver.setLayoutY(300);
		        
		        GridPane Glass = new GridPane();
		        Glass.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
		        final Scene scene3 = new Scene(Glass, 500, 600);
		       
		        Button NewGame = new Button();
		        NewGame.setLayoutX(200);
		        NewGame.setLayoutY(180);
		        NewGame.setText("NEW GAME");
		        NewGame.setFont(new Font("Arial", 15));
		        NewGame.setOnAction(new EventHandler<ActionEvent>() {

		            public void handle(ActionEvent event) {
		            	Engine.map =  new short[Engine.mapLimits][Engine.mapLimits];
		            	Engine.GetRandomPosition();
		            	fillGrid(grid, Engine.map);
		            	Engine.scoreCounter = 0;
		                globalStage.setScene(globalScene);
		            }
		        });
		        Glass.add(GameOver, 0, 1, 4, 1);
		        Glass.add(NewGame, 0, 2, 4, 1);
		      //************************************************
		        
		        Label Score = new Label("Your Score is " + Engine.scoreCounter);
		        Score.setFont(new Font("Arial", 15));
		        Score.setLayoutX(220);
		        Score.setLayoutY(80);
		        
		        Glass.add(Score, 0, 3, 4, 1);
				styleGrid(Glass);
				Glass.setGridLinesVisible(false);
				
				
				
				globalStage.setScene(scene3);
				globalStage.show();
				
		      //************************************************
	}
		
	private static void fillGrid(GridPane pane, short[][] map) {
		// place element different than zero
		short current = 0;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				current = map[row][col];
				if (current != 0) {
					//style Label
					String val=String.valueOf(current);
					Label lbl=new Label(val);
					lbl.setFont(new Font(35));
					
					// Using different colors for the different numbers
					switch (current) {
					case 3:
						lbl.setTextFill(Color.web( "#FFC826"));
						break;
					case 6:
						lbl.setTextFill(Color.web("#D0FA58"));
						break;
					case 12:
						lbl.setTextFill(Color.web("#FF6D44"));
						break;
					case 24:
						lbl.setTextFill(Color.web("#FF77B0"));
						break;
					case 48:
						lbl.setTextFill(Color.web("#F356FF"));
						break;
					case 96:
						lbl.setTextFill(Color.web("#C563FF"));
						break;
					case 192:
						lbl.setTextFill(Color.web("#9A75FF"));
						break;
					case 384:
						lbl.setTextFill(Color.web("#2327FF"));
						break;
					case 768:
						lbl.setTextFill(Color.web("#3FB2FF"));
						break;
					case 1536:
						lbl.setTextFill(Color.web("#3DFBFF"));
						break;
					case 3072:
						lbl.setTextFill(Color.web("#32FF62"));
						break;
					default:
						lbl.setTextFill(Color.web( "#000000"));
						break;
					}
					
					pane.add(lbl, row, col);

				} else {
					pane.add(new Label(""), row, col);
				}
			}
		}
		
		// Implementation of a score box 
		Label res=new Label("SCORE: "+Engine.scoreCounter);
		res.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		res.setTextFill(Color.RED);
		pane.addColumn(0, res);
		GridPane.setConstraints(res,0,4,4,1);
	}
	
	public static void styleGrid(GridPane pane) {

		pane.setStyle("-fx-background-color: white; -fx-hgap: 5; -fx-vgap: 5;-fx-alignment: center;");
		pane.setGridLinesVisible(false);
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
				control.setStyle("-fx-background-color: black; -fx-alignment: center;");
			}
		}
	}
	
	
	public static void main(String[] args) {
		Engine.GetRandomPosition();
		fillGrid(grid, Engine.map);
		
		launch(args);		
	}
}
