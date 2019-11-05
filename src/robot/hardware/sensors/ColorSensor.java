package robot.hardware.sensors;

import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor extends RobotSensor{

	private EV3ColorSensor c;
	
	public ColorSensor(String name, int port) {
		super(name, SensorType.COLOR, port);
		this.c = new EV3ColorSensor(this.port);
		this.s = c.getRedMode();
	}
	
	/**
	 * Available modes: light, color
	 */
	@Override
	public void changeMode(String newMode) {
		switch (newMode) {
		case "light":
			s = c.getRedMode();
			break;
		case "color":
			s = c.getColorIDMode();
		}
	}
	
}
