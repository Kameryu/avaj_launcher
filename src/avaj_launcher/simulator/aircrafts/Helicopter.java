package avaj_launcher.simulator.aircrafts;

import avaj_launcher.simulator.MyLogger;
import avaj_launcher.simulator.WeatherTower;
import avaj_launcher.weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather;
		weather = this.weatherTower.getWeather(this.coordinates);
		MyLogger.log.print("Helicopter#" + this.name + "(" + this.id + "): ");
		switch (weather) {
			case "SUN": {
				MyLogger.log.println("All clear, ascending to the heavens.");
				this.coordinates.setLongitude((this.coordinates.getLongitude() + 10) % 200);
				this.coordinates.setHeight(Integer.min(this.coordinates.getHeight() + 2, 100));
				break;
			}
			case "RAIN": {
				MyLogger.log.println("Watch out ! God is crying !");
				this.coordinates.setLongitude((this.coordinates.getLongitude() + 5) % 200);
				break;
			}
			case "FOG": {
				MyLogger.log.println("Could these blades not be used as a fan to evacuate this fog?");
				this.coordinates.setLongitude((this.coordinates.getLongitude() + 1) % 200);
				break;
			}
			case "SNOW": {
				MyLogger.log.println("Mayday, Mayday, Mayday ! It's snowing up there !");
				this.coordinates.setHeight(this.coordinates.getHeight() - 12);
				break;
			}
		}
		if (this.coordinates.getHeight() < 1) {
			this.coordinates.setHeight(0);
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
	}

	@Override
	public String getName() {
		return this.name;
	}
}
