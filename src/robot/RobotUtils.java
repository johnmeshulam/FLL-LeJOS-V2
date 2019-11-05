package robot;

import robot.hardware.motors.RobotMotor;
import robot.hardware.sensors.RobotSensor;

public class RobotUtils {
	private RobotUtils() {
	}

	public static void calibrateAllSensors() {
		RobotSensor[] sensors = RobotMap.getSensors();
		for (int i = 0; i < sensors.length; i++) {
			if (sensors[i] != null) {
				sensors[i].resetToAbsoluteValue();
				if (sensors[i].canCalibrate()) {
					sensors[i].calibrate();
				}
			}
		}
	}

	public static void resetAllMotorEncoders() {
		RobotMotor[] motors = RobotMap.getMotors();
		for (int i = 0; i < motors.length; i++) {
			if (motors[i] != null) {
				motors[i].resetEncoder();
			}
		}
	}

	public static void stopAllMotors() {
		RobotMotor[] motors = RobotMap.getMotors();
		for (int i = 0; i < motors.length; i++) {
			if (motors[i] != null) {
				motors[i].brake(true);
			}
		}
	}
}
