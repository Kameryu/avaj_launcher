package avaj_launcher.simulator;

import java.io.*;

public class MyLogger {
	public static PrintWriter log;

	static PrintWriter openForFile () {
		new File("simulation.txt");
		try {
			return new PrintWriter("simulation.txt");
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + "simulation.txt");
		}
		return null;
	}
}
