package user;

import user.runs.Run1;
import user.runs.Run2;

public class RunCreator {
	public static void init() {
		new Run1("my first run");
		new Run2("my second run");
	}
}
