package robot.hardware.chassis;

import robot.RobotMap;
import robot.hardware.motors.LargeMotor;
import robot.utils.Wait;

public class RobotChassis implements Chassis{

	private LargeMotor leftMotor;
	private LargeMotor rightMotor;
	
	public RobotChassis(char leftPort, char rightPort) {
		this(leftPort, rightPort, false);
	}
	
	public RobotChassis(char leftPort, char rightPort, boolean isInverted) {
		this("leftWheel", leftPort, "rightWheel", rightPort, isInverted);
	}
	
	public RobotChassis(String leftMotorName, char leftPort, String rightMotorName, char rightPort) {
		this(leftMotorName, leftPort, rightMotorName, rightPort, false);
	}
	
	public RobotChassis(String leftMotorName, char leftPort, String rightMotorName, char rightPort, boolean isInverted) {
		LargeMotor lm = new LargeMotor(leftMotorName, leftPort, isInverted);
		LargeMotor rm = new LargeMotor(rightMotorName, rightPort, isInverted);
		
		this.leftMotor = lm;
		this.rightMotor = rm;
		
		lm.syncWithMotor(rm);
		RobotMap.setChassis(this);
	}

	@Override
	public void forwardDrive(double speed) {
		syncDrive(speed, speed);
	}

	@Override
	public void backwardDrive(double speed) {
		syncDrive(-speed, -speed);
	}

	@Override
	public void forwardDrive(double speed, int degrees, boolean brake) {
		leftMotor.resetEncoder();
		
		syncDrive(speed, speed);
		
		Wait.waitFor(() -> {
			return leftMotor.readEncoder() > degrees;
		});
		
		if (brake) this.brake();
		else this.coast();
	}

	@Override
	public void backwardDrive(double speed, int degrees, boolean brake) {
		leftMotor.resetEncoder();
		
		syncDrive(-speed, -speed);
		
		Wait.waitFor(() -> {
			return leftMotor.readEncoder() < -degrees;
		});
		
		if (brake) this.brake();
		else this.coast();
	}

	@Override
	public void forwardDrive(double speed, double seconds, boolean brake) {
		syncDrive(speed, speed);
		
		Wait.waitForSeconds(seconds);
		
		if (brake) this.brake();
		else this.coast();
	}

	@Override
	public void backwardDrive(double speed, double seconds, boolean brake) {
		syncDrive(-speed, -speed);
		
		Wait.waitForSeconds(seconds);
		
		if (brake) this.brake();
		else this.coast();
	}

	@Override
	public void tankDrive(double leftSpeed, double rightSpeed) {
		syncDrive(leftSpeed, rightSpeed);
	}

	@Override
	public void tankDrive(double leftSpeed, double rightSpeed, int degrees, boolean brake) {
		leftMotor.resetEncoder();
		rightMotor.resetEncoder();
		
		syncDrive(leftSpeed, rightSpeed);
		
		Wait.waitFor(() -> {
			return (Math.abs(leftMotor.readEncoder()) > degrees 
					|| Math.abs(rightMotor.readEncoder()) > degrees);
		});
		
		if (brake) this.brake();
		else this.coast();
		
	}

	@Override
	public void tankDrive(double leftSpeed, double rightSpeed, double seconds, boolean brake) {
		syncDrive(leftSpeed, rightSpeed);
		
		Wait.waitForSeconds(seconds);
		
		if (brake) this.brake();
		else this.coast();
	}
	
	private void syncDrive(double leftSpeed, double rightSpeed) {
		leftMotor.startSync();
		if(leftMotor.isInverted()) leftSpeed = -leftSpeed;
		if(rightMotor.isInverted()) rightSpeed = -rightSpeed;
		
		if(leftSpeed >= 0) {
			leftMotor.forward(leftSpeed);
		} else {
			leftMotor.backward(leftSpeed);
		}
		
		if(rightSpeed >= 0) {
			rightMotor.forward(rightSpeed);
		} else {
			rightMotor.backward(rightSpeed);
		}
		leftMotor.endSync();
	}

	@Override
	public void brake() {
		leftMotor.startSync();
		leftMotor.brake(true);
		rightMotor.brake(true);
		leftMotor.endSync();
	}

	@Override
	public void coast() {
		leftMotor.startSync();
		leftMotor.coast();
		rightMotor.coast();
		leftMotor.endSync();
	}
		
}
