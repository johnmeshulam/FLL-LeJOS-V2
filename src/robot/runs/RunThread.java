package robot.runs;

import lejos.hardware.lcd.LCD;
import robot.RobotUtils;

public class RunThread extends Thread {
	
	private RobotRun run;
	private boolean active;
	
	public RunThread(RobotRun fromRun) {
		this.run = fromRun;
	}
	
	@Override
	public void run() {
		active = true;
		LCD.clear();
		RunHandler.setCurrentRun(this);
		RobotUtils.resetAllMotorEncoders();
		run.runInstructions();
		RobotUtils.stopAllMotors();
		active = false;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void deactivate() {
		this.active = false;
	}

}
