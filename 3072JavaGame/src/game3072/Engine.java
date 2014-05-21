package game3072;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Engine {
	
	public static int scoreCounter=0;
	public static byte startValue = 3; // Spawning value
	public static byte mapLimits = 4; // Size of the field
	public static short[][] map = new short[mapLimits][mapLimits]; // A short two dimensional array responsible for assigning 
																													  // values to to the corresponding cells
	
	public static EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {

		public void handle(KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.LEFT) {
				game3072.Movement.moveLeft(); 
					// Left arrow code and movement here
				GameUI.updateStage(); // Update the graphic image with the new values
			} else if (keyEvent.getCode() == KeyCode.RIGHT) {
					game3072.Movement.moveRight();// Right arrow code and movement here
				GameUI.updateStage();
			} else if (keyEvent.getCode() == KeyCode.UP) {
				game3072.Movement.moveUp();// Up arrow code and movement here
				GameUI.updateStage();
			} else if (keyEvent.getCode() == KeyCode.DOWN) {
				game3072.Movement.moveDown();
				// Down arrow code and movement here
				GameUI.updateStage();
			}
			if (GameIsOver()) // If the game is already over
			{							
				GameUI.GameOverStage();
				System.out.println("Game is over event handler");
			}			
			keyEvent.consume();
		}
	};
	
	// isThereThisValue - used for determining there is still cell which value equals to 3. 
	private static boolean isThereThisValue(int value) {
		for (int y = 0; y < mapLimits; y++) {
			for (int x = 0; x < mapLimits; x++) {
				if (map[y][x] == value) { // If it finds it, it returns true.
					return true;
				}
			}
		}
		return false;
	}

	// isUpMoveable - 	a check for possible movement
	private static boolean isUpMoveable(int y, int x) {
		if (y > 0) {
			if (map[y - 1][x] == 0 || map[y - 1][x] == map[y][x]) { // If the cell which Up from the current position is empty (=0) or 
				return true; 															 // it has the same value as the current one, proceed
			}
		}
		return false;
	}

	// isDownMoveable - a check for possible movement
	private static boolean isDownMoveable(int y, int x) {
		if (y < mapLimits - 1) {
			if (map[y + 1][x] == 0 || map[y + 1][x] == map[y][x]) { // If the cell which Down from the current position is empty (=0) or 
				return true; 															   // it has the same value as the current one, proceed
			}
		}
		return false;
	}

	// isRightMoveable - a check for possible movement
	private static boolean isRightMoveable(int y, int x) {
		if (x < mapLimits - 1) {
			if (map[y][x + 1] == 0 || map[y][x + 1] == map[y][x]) { // If the cell which Right from the current position is empty (=0) or 
				return true; 															   // it has the same value as the current one, proceed
			}
		}
		return false;
	}

	// isLeftMoveable - a check for possible movement
	private static boolean isLeftMoveable(int y, int x) {
		if (x > 0) {
			if (map[y][x - 1] == 0 || map[y][x - 1] == map[y][x]) { 	// If the cell which Left from the current position is empty (=0) or 
				return true; 															    // it has the same value as the current one, proceed
			}
		}
		return false;
	}

	// Checking if the game should be still active
	public static boolean GameIsOver() {
		for (int y = 0; y < mapLimits; y++) {
			for (int x = 0; x < mapLimits; x++) {
				if (map[y][x] != 0) {
					if (isUpMoveable(y, x) || isDownMoveable(y, x)	// If the movement Up, Down
							|| isLeftMoveable(y, x) || isRightMoveable(y, x)) { // Left or Right is possible and allowed (true)
						return false;															// return false - the game continues
					}
				} else
					return false;
			}
		}	
		return true; // if it returns true, the game is over
	}

	// A method which generates values in the still unused cells
	public static void GetRandomPosition() {
		Random coord = new Random();
		int y = coord.nextInt(mapLimits);
		int x = coord.nextInt(mapLimits);
		while (map[y][x] != 0 && map[y][x] != startValue // While the cell isn't empty, the cell's value isn't equal to 3
				&& (!GameIsOver() || isThereThisValue(startValue))) { //, the game is still not over or there is still 3 on the whole table, then continue
			y = coord.nextInt(mapLimits);
			x = coord.nextInt(mapLimits);
		}
		if (!GameIsOver()) {
			if (map[y][x] == 0) { // If the game isn't over, and there cell is empty (equal to 0), 
				map[y][x] = startValue;  // then assign value 3 to it.
			} else {
				map[y][x] *= 2;
			}
		}
	}
}