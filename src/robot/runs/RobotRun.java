package robot.runs;

public abstract class RobotRun {

	public abstract void runInstructions();
	
	private String name;

	public RobotRun(String name) {
		this.name = name;
		RunHandler.addRun(this);
	}
	
	public String getRunName() {
		return this.name;
	}

}
