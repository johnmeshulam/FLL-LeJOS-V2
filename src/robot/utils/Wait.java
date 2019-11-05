package robot.utils;

import lejos.utility.Delay;
import robot.runs.RunHandler;

public class Wait {

	private Wait() {}
	
	public static void waitFor(WaitCondition condition) {
		while(!condition.evaluate() && RunHandler.isRunning()) {
			Delay.msDelay(1); //Does not work if not present
			//TODO: investigate
		}
	}
	
	public static void waitForSeconds(double seconds) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime < seconds * 1000
				&& RunHandler.isRunning()) {
			Delay.msDelay(1); //Does not work if not present
			//TODO: investigate
		};
	}
	
}
