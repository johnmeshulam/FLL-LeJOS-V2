package robot.hardware.sensors;

public enum SensorType {
	ULTRASONIC (false),
	GYRO (true),
	COLOR (false),
	TOUCH (false);
	
	private boolean canCalibrate;
	
	private SensorType(boolean canCalibrate) {
		this.canCalibrate = canCalibrate;
	}
	
	public boolean canCalibrate() {
		return this.canCalibrate;
	}
}
