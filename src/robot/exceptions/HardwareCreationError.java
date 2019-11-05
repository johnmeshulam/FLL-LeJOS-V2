package robot.exceptions;

public class HardwareCreationError extends RuntimeException {

	private static final long serialVersionUID = 188738051227428202L;
	
	public HardwareCreationError(String errorMessage) {
		super(errorMessage);
	}
	
}
