package robot.hardware.motors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import robot.runs.RunHandler;

public class LargeMotor extends RobotMotor {

	private EV3LargeRegulatedMotor l;

	public LargeMotor(String motorName, char port) {
		this(motorName, port, false);
	}

	public LargeMotor(String motorName, char port, boolean isInverted) {
		super(motorName, MotorType.LARGE, port, isInverted);
		this.l = new EV3LargeRegulatedMotor(this.port);
	}

	@Override
	public void forward(double speed) {
		l.setSpeed(convertSpeed(speed));
		if(!this.inverted) l.forward();
		else l.backward();
	}

	@Override
	public void backward(double speed) {
		l.setSpeed(convertSpeed(speed));
		if(!this.inverted) l.backward();
		else l.forward();
	}

	@Override
	public void brake(boolean immediateReturn) {
		l.stop(immediateReturn);
	}

	@Override
	public void coast() {
		l.flt();
	}

	@Override
	public int readEncoder() {
		return l.getTachoCount();
	}

	@Override
	public void resetEncoder() {
		l.resetTachoCount();
	}

	@Override
	public boolean isStalled() {
		return l.isStalled();
	}

	@Override
	protected int convertSpeed(double speed) {
		return (int) Math.min(Math.max((Math.abs(speed) * l.getMaxSpeed()), 0), l.getMaxSpeed());
	}

	@Override
	/**
	 * Defaults to forward, insert negative degrees to go back
	 */
	public void rotateDegrees(double speed, int degrees, boolean brake) {
		l.resetTachoCount();

		if (degrees >= 0) {
			this.forward(speed);
			while (Math.abs(l.getTachoCount()) < degrees && RunHandler.isRunning())
				;
		} else {
			this.backward(speed);
			while (-Math.abs(l.getTachoCount()) > degrees && RunHandler.isRunning())
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
		l.resetTachoCount();
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

	public void startSync() {
		l.startSynchronization();
	}

	public void endSync() {
		l.endSynchronization();
	}

	public void syncWithMotor(LargeMotor motor) {
		l.synchronizeWith(new RegulatedMotor[] { motor.getInternalMotor() });
	}
	
	public boolean isInverted() {
		return this.inverted;
	}

	/**
	 * WARNING: Use this only if you know what you are doing!
	 * 
	 * @return
	 */
	public EV3LargeRegulatedMotor getInternalMotor() {
		return this.l;
	}

}
