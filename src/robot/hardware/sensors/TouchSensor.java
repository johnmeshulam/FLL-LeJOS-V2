package robot.hardware.sensors;

import lejos.hardware.sensor.EV3TouchSensor;

public class TouchSensor extends RobotSensor {

	private EV3TouchSensor t;

	public TouchSensor(String name, int port) {
		super(name, SensorType.TOUCH, port);
		this.t = new EV3TouchSensor(this.port);
		this.s = t.getTouchMode();
	}

}
