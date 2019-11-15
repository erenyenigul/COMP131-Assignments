import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import stanford.karel.SuperKarel;

public class SuperKarelBros extends SuperKarel {

	public void run() {

		playThemeSong(THEME_SONG);

		// Your code starts here.
		nextTask();
		tree();
		nextTask();
		downstairs();
		coinBlocks();
		nextTask();
		climbPipe();
		pinkTower();
		nextTask();
		upstairs();
		flagpole();
		turnLeft();
		move();
		turnRight();
		move();
		turnLeft();
		while (!cornerColorIs(WHITE))
			move();
		// Your code ends here.
		if(cornerColorIs(WHITE)){
			playVictorySong(VICTORY_SONG);}
	}

	/** Helper Methods */
	// Your code starts here.
	private void nextTask() {
		while (frontIsClear()) {
			move();
		}
	}

	private void tree() {
		turnLeft();
		goAlongTheTree();
		turnRight();
		move();
		goAlongTheTree();
		turnRight();
		move();
		goAlongTheTree();
		turnLeft();
	}

	private void downstairs() {
		turnLeft();
		while (rightIsBlocked())
			move();
		turnRight();
		move();
		move();
		goDown();
	}

	private void coinBlocks() {
		while (rightIsBlocked()) {
			move();
		}
		move();
		turnLeft();
		while (!cornerColorIs(YELLOW)) {
			move();
		}
		turnAround();
		while (noBeepersPresent()) {
			paintCorner(CYAN);
			nextTask();
			turnLeft();
			move();
			turnLeft();
			while (!cornerColorIs(YELLOW)) {
				move();
			}
			turnAround();
		}
		pickBeeper();

		nextTask();
		turnLeft();
	}

	private void climbPipe() {
		turnLeft();
		while (rightIsBlocked()) {
			move();
		}
		turnRight();
		move();
		turnRight();
		move();
		goThroughPipe();
		turnRight();
		move();
		turnRight();
		nextTask();
		turnLeft();
	}

	private void goAlongTheTree() {
		while (rightIsBlocked() && frontIsClear()) {
			move();
			if (cornerColorIs(RED)) {
				paintCorner(CYAN);
			}
		}
	}

	private void pinkTower() {
		while (cornerColorIs(CYAN)) {
			move();
		}
		turnLeft();
		carryBeepers();
		nextTask();
		turnAround();
		carryBeepers();
	}

	private void upstairs() {
		turnLeft();
		nextTask();
		while (frontIsBlocked()) {
			turnLeft();
			move();
			turnRight();
			move();
		}
		move();
		turnRight();
		nextTask();
	}

	private void flagpole() {
		turnLeft();
		nextTask();
		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnLeft();
		while (cornerColorIs(CYAN)) {
			move();
		}
		turnAround();
		while (rightIsBlocked()) {
			pickBeeper();
			paintCorner(CYAN);
			move();
			if(frontIsClear()){
				paintCorner(BLUE);
				putBeeper(); }
		}


	}

	private void goDown() {
		while (rightIsClear()) {
			turnRight();
			move();
			turnLeft();
			move();
		}
	}

	private void goThroughPipe() {
		while (cornerColorIs(GREEN)) {
			while (leftIsBlocked() && frontIsClear()) {
				move();
				while (beepersPresent()) {
					pickBeeper();
				}
			}
			if (leftIsClear() && !cornerColorIs(CYAN)) {
				turnLeft();
				move();
				while (beepersPresent()) {
					pickBeeper();
				}
			} else if (frontIsBlocked()) {
				turnRight();
				while (beepersPresent()) {
					pickBeeper();
				}
			}
		}
	}

	private void carryBeepers() {
		while (!cornerColorIs(BLACK)) {
			if (beepersPresent()) {
				putBeeperToNext();
			} else {
				move();
			}
		}
	}

	private void putBeeperToNext() {
		pickBeeper();
		move();
		putBeeper();
		turnAround();
		move();
		turnAround();
	}

	// Your code ends here.

	/** ----- Do not change anything below here. ----- */

	private void playThemeSong(String fileLocation) {
		try {
			inputStream = AudioSystem.getAudioInputStream(new File(fileLocation));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void playVictorySong(String fileLocation) {
		try {
			clip.close();
			inputStream.close();
			inputStream = AudioSystem.getAudioInputStream(new File(fileLocation));
			clip.open(inputStream);
			clip.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static final String THEME_SONG = "theme.wav";
	private static final String VICTORY_SONG = "victory.wav";
	private Clip clip;
	private AudioInputStream inputStream;

}

