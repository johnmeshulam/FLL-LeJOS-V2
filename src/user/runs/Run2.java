package user.runs;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import robot.RobotMap;
import robot.runs.RobotRun;
import robot.utils.Wait;

public class Run2 extends RobotRun{

	public Run2(String name) {
		super(name);
	}

	@Override
	public void runInstructions() {
		RobotMap.getChassis().tankDrive(1.0, 0.5);
		
		Wait.waitFor(() -> {
			return Button.getButtons() == Button.ID_ENTER;
		});
		
		RobotMap.getChassis().brake();
		
		Wait.waitForSeconds(2.0);
		
		for (int i = 0; i < 10; i++) {
			LCD.drawInt((int) RobotMap.getSensor("gyro").read(), 0, 4);
			Wait.waitForSeconds(0.2);
		}
		
	}
	
}
