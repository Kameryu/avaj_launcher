package avaj_launcher.MyExceptions;

public class MyEmptyFileException extends Exception {
	public MyEmptyFileException() {
		super("Cannot have a simulation with an empty file.");
	}
}
