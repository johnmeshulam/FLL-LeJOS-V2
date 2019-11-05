package robot.runs;

import java.util.ArrayList;

public class RunHandler {
	
	private RunHandler() {}
	
	private static ArrayList<RobotRun> runs = new ArrayList<RobotRun>();

	private static RunThread currentRun;
		
	public static void addRun(RobotRun run) {
		runs.add(run);
	}
	
	public static void startRun(int index) {
		new RunThread(runs.get(index)).start();
	}
	
	public static void setCurrentRun(RunThread newCurrentRun) {
		currentRun = newCurrentRun;
	}
	
	public static boolean isRunning() {
		if (currentRun == null) return false;
		return currentRun.isActive();
	}
	
	public static void deactivateRun() {
		if(currentRun != null && currentRun.isActive()) {
			currentRun.deactivate();
		}
	}
	
	public static int getTotalRuns() {
		return runs.size();
	}
	
	public static String getRunName(int index) {
		return runs.get(index).getRunName();
	}
}
