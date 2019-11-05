package user.runs;

import robot.RobotMap;
import robot.runs.RobotRun;
import robot.utils.Wait;

public class Run1 extends RobotRun{

	public Run1(String name) {
		super(name);
	}

	@Override
	public void runInstructions() {
		RobotMap.getMotor("armMotor").rotateDegrees(0.5, 720, true);
		
		Wait.waitForSeconds(3.0);
		
		RobotMap.getMotor("armMotor").rotateDegrees(0.5, -720, true);
	}

}
