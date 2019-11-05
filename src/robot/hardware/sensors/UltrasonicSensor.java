package robot.hardware.sensors;

import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltrasonicSensor extends RobotSensor {
	
	private EV3UltrasonicSensor u;
	
	public UltrasonicSensor(String name, int port) {
		super(name, SensorType.ULTRASONIC, port);
		this.u = new EV3UltrasonicSensor(this.port);
		this.s = u.getDistanceMode();
	}

}
