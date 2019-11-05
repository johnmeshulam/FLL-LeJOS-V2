package robot.hardware.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import robot.RobotMap;
import robot.exceptions.HardwareCreationError;

public abstract class RobotSensor {

	private String sensorName;
	private SensorType sensorType;

	private int portName;
	protected Port port;

	protected SampleProvider s;
	
	protected int zeroValue = 0;

	public RobotSensor(String name, SensorType type, int port) {
		this.sensorName = name;
		this.sensorType = type;

		this.portName = port;
		this.setPort(port);

		RobotMap.addSensor(this);
	}

	private void setPort(int port) {
		switch (port) {
		case 1:
			this.port = SensorPort.S1;
			break;
		case 2:
			this.port = SensorPort.S2;
			break;
		case 3:
			this.port = SensorPort.S3;
			break;
		case 4:
			this.port = SensorPort.S4;
			break;
		default:
			throw new HardwareCreationError("Port " + port + " is invalid!");
		}
	}
	
	public double read() {
		return this.read(5);
	}

	public double read(int sampleSize) {
		float[] samples = new float[sampleSize];
		double sum = 0;

		for (int i = 0; i < samples.length; i++) {
			this.s.fetchSample(samples, i);
			sum += samples[i];
		}

		return (sum / samples.length)-zeroValue;
	}

	public void resetToCurrentValue() {
		zeroValue = (int) this.read(5);
	}
	
	public void resetToAbsoluteValue() {
		zeroValue = 0;
	}
	
	public void calibrate() {
		System.out.println("This sensor can't calibrate");
	}

	public void changeMode(String mode) {
		System.out.println("This sensor does not have multiple modes!");
	}

	public boolean canCalibrate() {
		return this.sensorType.canCalibrate();
	}

	public String getName() {
		return this.sensorName;
	}

	public int getPortName() {
		return this.portName;
	}

	public SensorType getType() {
		return this.sensorType;
	}

}
