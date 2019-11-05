package robot.hardware.sensors;

import lejos.hardware.sensor.EV3GyroSensor;

public class GyroSensor extends RobotSensor{

	private EV3GyroSensor g;

	public GyroSensor(String name, int port) {
		super(name, SensorType.GYRO, port);
		this.g = new EV3GyroSensor(this.port);
		this.s = g.getAngleMode();
	}
	
	@Override
	public void calibrate() {
		this.resetToAbsoluteValue();
		this.g.reset();
	}
	
}
