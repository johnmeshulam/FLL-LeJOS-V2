package robot.exceptions;

public class HardwareNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7030825542276418417L;

	public HardwareNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
}
