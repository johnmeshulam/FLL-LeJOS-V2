package robot.hardware.motors;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import robot.RobotMap;
import robot.exceptions.HardwareCreationError;

public abstract class RobotMotor {
	
	private String motorName;
	private MotorType motorType;
	protected boolean inverted;
	
	private char portName;
	protected Port port;
	
	public RobotMotor(String motorName, MotorType motorType, char portName) {
		this(motorName, motorType, portName, false);
	}
	
	public RobotMotor(String motorName, MotorType motorType, char portName, boolean isInverted) {
		this.motorName = motorName;
		this.motorType = motorType;
		
		this.portName = portName;
		this.setPort();
		
		this.inverted = isInverted;
		RobotMap.addMotor(this);
	}
	
	private void setPort() {
		switch(this.portName) {
		case 'A':
			this.port = MotorPort.A;
			break;
		case 'B':
			this.port = MotorPort.B;
			break;
		case 'C':
			this.port = MotorPort.C;
			break;
		case 'D':
			this.port = MotorPort.D;
			break;
		default:
			throw new HardwareCreationError("Port " + portName + " is invalid!");
		}
	}

	public String getName() {
		return this.motorName;
	}
	
	public char getPortName() {
		return this.portName;
	}
	
	public MotorType getType() {
		return this.motorType;
	}
	
	public void brake() {
		this.brake(false);
	}
	
	protected abstract int convertSpeed(double speed);
	
	public abstract void forward(double speed);
	
	public abstract void backward(double speed);
	
	public abstract void brake(boolean immediateReturn);
	
	public abstract void coast();
	
	public abstract int readEncoder();
	
	public abstract void resetEncoder();
	
	public abstract boolean isStalled();
	
	public abstract void rotateDegrees(double speed, int degrees, boolean brake);
	
	public abstract void rotateSeconds(double speed, double seconds, boolean brake);

}
