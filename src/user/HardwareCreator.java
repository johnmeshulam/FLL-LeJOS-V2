package user;

import robot.hardware.chassis.RobotChassis;
import robot.hardware.motors.MediumMotor;
import robot.hardware.sensors.GyroSensor;

public class HardwareCreator {
	public static void init() {
		new RobotChassis("leftMotor", 'B', "rightMotor", 'C');
		new MediumMotor("armMotor", 'A');
		
		new GyroSensor("gyro", 1);
	}
}
