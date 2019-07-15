package avaj_launcher.MyExceptions;

public class LineFormatException extends Exception {
	public LineFormatException() {
		super("Integer value need to be a positive integer for the simulation.");
	}

	public LineFormatException(int i) {
		super("Format error at line " + i);
	}
}
