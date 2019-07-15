package avaj_launcher.simulator.aircrafts;

import avaj_launcher.weather.Coordinates;

public class AircraftFactory {
	public static Flyable newAircraft (String type, String name, int longitude, int latitude, int height) {
		Flyable flyable = null;
		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		switch (type) {
			case "Baloon": {
				flyable = new Baloon(name, coordinates);
				break;
			}
			case "Helicopter": {
				flyable = new Helicopter(name, coordinates);
				break;
			}
			case "JetPlane": {
				flyable = new JetPlane(name, coordinates);
				break;
			}
		}
		return flyable;
	}
}
