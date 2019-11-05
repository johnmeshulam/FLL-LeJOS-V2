package robot.runs;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class StopListener implements KeyListener {

	@Override
	public void keyReleased(Key k) {
		return;
	}

	@Override
	public void keyPressed(Key k) {
		while (Button.ESCAPE.isDown())
			;
		if (RunHandler.isRunning()) {
			RunHandler.deactivateRun();
		}
	};

}
