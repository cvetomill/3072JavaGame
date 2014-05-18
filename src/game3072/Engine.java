package game3072;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class Engine {

	public static byte startValue = 3; // Spawning value
	public static byte mapLimits = 4; // size of the field
	public static short[][] map = new short[mapLimits][mapLimits]; // field
																	// value
																	// container
	public static EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {

		public void handle(KeyEvent keyEvent) {
			if (keyEvent.getCode() == KeyCode.LEFT) {
				game3072.Movement.moveUp();
					// Left arrow code and movement here
				GameUI.updateStage();
			} else if (keyEvent.getCode() == KeyCode.RIGHT) {
				game3072.Movement.moveDown();	// Right arrow code and movement here
				GameUI.updateStage();
			} else if (keyEvent.getCode() == KeyCode.UP) {
				game3072.Movement.moveLeft(); 	// Up arrow code and movement here
				GameUI.updateStage();
			} else if (keyEvent.getCode() == KeyCode.DOWN) {
				game3072.Movement.moveRight();	
				// Down arrow code and movement here
				GameUI.updateStage();
			}

			keyEvent.consume();
		}
	};
	
	private static boolean isThereThisValue(int value) {

		for (int y = 0; y < mapLimits; y++) {
			for (int x = 0; x < mapLimits; x++) {
				if (map[y][x] == value) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isUpMoveable(int y, int x) {
		if (y > 0) {
			if (map[y - 1][x] == 0 || map[y - 1][x] == map[y][x]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isDownMoveable(int y, int x) {
		if (y < mapLimits - 1) {
			if (map[y + 1][x] == 0 || map[y + 1][x] == map[y][x]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isRightMoveable(int y, int x) {
		if (x < mapLimits - 1) {
			if (map[y][x + 1] == 0 || map[y][x + 1] == map[y][x]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isLeftMoveable(int y, int x) {
		if (x > 0) {
			if (map[y][x - 1] == 0 || map[y][x - 1] == map[y][x]) {
				return true;
			}
		}
		return false;
	}

	public static boolean GameIsOver() {
		for (int y = 0; y < mapLimits; y++) {
			for (int x = 0; x < mapLimits; x++) {
				if (map[y][x] != 0) {
					if (isUpMoveable(y, x) || isDownMoveable(y, x)
							|| isLeftMoveable(y, x) || isRightMoveable(y, x)) {
						return false;
					}
				} else
					return false;
			}
		}
		return true;
	}

	public static void Run() {
		while (!GameIsOver()) {
		}
	}

	public static void GetRandomPosition() {
		Random coord = new Random();
		int y = coord.nextInt(mapLimits);
		int x = coord.nextInt(mapLimits);
		while (map[y][x] != 0 && map[y][x] != startValue
				&& (!GameIsOver() || isThereThisValue(startValue))) {
			y = coord.nextInt(mapLimits);
			x = coord.nextInt(mapLimits);
		}
		if (!GameIsOver()) {
			if (map[y][x] == 0) {
				map[y][x] = startValue;
			} else {
				map[y][x] *= 2;
			}
		}
	}
}