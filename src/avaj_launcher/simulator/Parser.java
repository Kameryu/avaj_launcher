package avaj_launcher.simulator;

import avaj_launcher.MyExceptions.LineFormatException;
import avaj_launcher.MyExceptions.WrongIntegerValueException;
import avaj_launcher.simulator.aircrafts.AircraftFactory;
import avaj_launcher.simulator.aircrafts.Flyable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
	static int parseFirstLine(String line) throws LineFormatException, WrongIntegerValueException {
		Pattern p = Pattern.compile("^ *-?[0-9]+ *$");
		Matcher m = p.matcher(line);
		if (!m.matches())
			throw new LineFormatException();
		p = Pattern.compile("^ *[0-9]+ *");
		m = p.matcher(line);
		if (!m.matches())
			throw new WrongIntegerValueException();
		String tmp = "2147483647";
		if (line.length() > tmp.length())
			throw new WrongIntegerValueException(line);
		if (line.length() == tmp.length() && line.compareTo(tmp) > 0)
			throw new WrongIntegerValueException(line);
		return Integer.parseInt(line);
	}

	static Flyable parseFlyable(String line, int cnt) throws LineFormatException, WrongIntegerValueException {
		String flyable[] = {"Baloon", "JetPlane", "Helicopter"};
		Pattern p = Pattern.compile("^ *(" + flyable[0] + "|" + flyable[1] + "|" + flyable[2]
				+ ") *[0-9A-Za-z]+ *-?[0-9]+ *-?[0-9]+ *-?[0-9]{1,3} *$");
		Matcher m = p.matcher(line);
		if (!m.matches())
			throw new LineFormatException(cnt);
		String tmp = "2147483647";
		String nb;
		for (int i = 2; i < 5; i++) {
			if (Integer.parseInt(line.split(" ")[i]) < 0)
				throw new LineFormatException();
			nb = line.split(" ")[i];
			if (nb.length() > tmp.length())
				throw new WrongIntegerValueException(nb, cnt);
			if (nb.length() == tmp.length() && nb.compareTo(tmp) > 0)
				throw new WrongIntegerValueException(nb, cnt);
		}
		return AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
				Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
				Integer.parseInt(line.split(" ")[4]));
	}
}
