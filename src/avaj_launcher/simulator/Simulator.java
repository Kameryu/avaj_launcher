package avaj_launcher.simulator;

import avaj_launcher.MyExceptions.MyEmptyFileException;
import avaj_launcher.MyExceptions.LineFormatException;
import avaj_launcher.MyExceptions.WrongIntegerValueException;
import avaj_launcher.simulator.aircrafts.Flyable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	private static List <Flyable> flyables = new ArrayList<>();

	public static void main (String[] argv) throws MyEmptyFileException {
		WeatherTower weatherTower;
		if ((MyLogger.log = MyLogger.openForFile()) != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(argv[0]));
				String line = reader.readLine();
				if (line == null)
					throw new MyEmptyFileException();
				weatherTower = new WeatherTower();
				int simulation = Parser.parseFirstLine(line);
				int cnt = 1;
				while ((line = reader.readLine()) != null) {
					cnt++;
					Flyable flyable = Parser.parseFlyable(line, cnt);
					flyables.add(flyable);
				}
				for (Flyable fly : flyables) {
					fly.registerTower(weatherTower);
				}
				for (int i = 0; i < simulation; i++) {
					weatherTower.changeWeather();
				}
			} catch (MyEmptyFileException | WrongIntegerValueException | LineFormatException e) {
				System.out.println(e.getMessage());
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + argv[0]);
			} catch (IOException e) {
				System.out.println("Failed to read file: " + argv[0]);
			}
			MyLogger.log.close();
		}
	}
}
