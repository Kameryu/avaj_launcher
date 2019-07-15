package avaj_launcher.MyExceptions;

public class WrongIntegerValueException extends Exception {
	public WrongIntegerValueException() {
		super("Number of iteration cannot be a negative integer.");
	}

	public WrongIntegerValueException(String nb) {
		super("Number " + nb + " is to long for an integer.");
	}

	public WrongIntegerValueException(String nb, int atLine) {
		super("At line " + atLine + ": Number " + nb + " is to lone for an integer");
	}
}
