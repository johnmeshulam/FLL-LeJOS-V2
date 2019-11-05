package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import robot.runs.RunHandler;

public class MainMenu {

	private MainMenu() {
	}

	private static int arrowCoord;
	private static boolean active;

	public static void init() {
		active = true;
		arrowCoord = 0;
		System.out.print("\n\n\n\n\n\n\n\n\n");
		
		while (active) {

			drawScreen();

			switch (Button.getButtons()) {
			case Button.ID_UP:
				while (Button.getButtons() == Button.ID_UP)
					;
				moveUp();
				break;
			case Button.ID_DOWN:
				while (Button.getButtons() == Button.ID_DOWN)
					;
				moveDown();
				break;
			case Button.ID_ENTER:
				while (Button.getButtons() == Button.ID_ENTER)
					;
				selectRun();
				Delay.msDelay(50);
				while(RunHandler.isRunning());
				break;
			}
			Delay.msDelay(100);
		}

	}

	private static void moveUp() {
		arrowCoord = Math.max(0, arrowCoord - 1);
	}

	private static void moveDown() {
		arrowCoord = Math.min(RunHandler.getTotalRuns() - 1, arrowCoord + 1);
	}

	private static void selectRun() {
		if (!RunHandler.isRunning()) {
			RunHandler.startRun(arrowCoord);
		}
	}

	private static void drawScreen() {
		LCD.clear();
		LCD.drawString("->", 0, arrowCoord);
		for (int i = 0; i < RunHandler.getTotalRuns(); i++) {
			LCD.drawString(RunHandler.getRunName(i), 2, i);
		}
	}

}
