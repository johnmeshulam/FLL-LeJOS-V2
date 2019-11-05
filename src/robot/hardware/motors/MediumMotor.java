package robot.hardware.motors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import robot.runs.RunHandler;

public class MediumMotor extends RobotMotor {

	private EV3MediumRegulatedMotor m;

	public MediumMotor(String motorName, char port) {
		this(motorName, port, false);
	}

	public MediumMotor(String motorName, char port, boolean isInverted) {
		super(motorName, MotorType.MEDIUM, port, isInverted);
		this.m = new EV3MediumRegulatedMotor(this.port);
	}

	@Override
	public void forward(double speed) {
		m.setSpeed(convertSpeed(speed));
		if(!this.inverted) m.forward();
		else m.backward();
	}

	@Override
	public void backward(double speed) {
		m.setSpeed(convertSpeed(speed));
		if(!this.inverted) m.backward();
		else m.forward();
	}

	@Override
	public void brake(boolean immediateReturn) {
		m.stop(immediateReturn);
	}

	@Override
	public void coast() {
		m.flt();
	}

	@Override
	public int readEncoder() {
		return m.getTachoCount();
	}

	@Override
	public void resetEncoder() {
		m.resetTachoCount();
	}

	@Override
	public boolean isStalled() {
		return m.isStalled();
	}

	@Override
	protected int convertSpeed(double speed) {
		return (int) Math.min(Math.max((Math.abs(speed) * m.getMaxSpeed()), 0), m.getMaxSpeed());
	}

	@Override
	/**
	 * Defaults to forward, insert negative degrees to go back
	 */
	public void rotateDegrees(double speed, int degrees, boolean brake) {
		m.resetTachoCount();

		if (degrees >= 0) {
			this.forward(speed);
			while (Math.abs(m.getTachoCount()) < degrees && RunHandler.isRunning())
				;
		} else {
			this.backward(speed);
			while (-Math.abs(m.getTachoCount()) > degrees && RunHandler.isRunning())
				;
		}

		if (brake) this.brake();
		else this.coast();
	}

	@Override
	/**
	 * Defaults to forward, insert negative power to go back
	 */
	public void rotateSeconds(double speed, double seconds, boolean brake) {
		m.resetTachoCount();
		long startTime = System.currentTimeMillis();

		if (speed >= 0)
			this.forward(speed);
		else
			this.backward(speed);

		while (System.currentTimeMillis() - startTime < seconds * 1000 && RunHandler.isRunning())
			;

		if (brake) this.brake();
		else this.coast();
	}

}
