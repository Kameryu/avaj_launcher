package avaj_launcher.simulator.aircrafts;

import avaj_launcher.simulator.MyLogger;
import avaj_launcher.simulator.WeatherTower;
import avaj_launcher.weather.Coordinates;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather;
		weather = this.weatherTower.getWeather(this.coordinates);
		MyLogger.log.print("Baloon#" + this.name + "(" + this.id + "): ");
		switch (weather) {
			case "SUN": {
				MyLogger.log.println("Sous les sunlights des tropiques!");
				this.coordinates.setLongitude((this.coordinates.getLongitude() + 2) % 200);
				this.coordinates.setHeight(Integer.min(this.coordinates.getHeight() + 4, 100));
				break;
			}
			case "RAIN": {
				MyLogger.log.println("I'm singin' in the rain!");
				this.coordinates.setHeight(this.coordinates.getHeight() - 5);
				break;
			}
			case "FOG": {
				MyLogger.log.println("*there is so much fog that we can't even ear the song*");
				this.coordinates.setHeight(this.coordinates.getHeight() - 3);
				break;
			}
			case "SNOW": {
				MyLogger.log.println("Libéré ! délivré !");
				this.coordinates.setHeight(this.coordinates.getHeight() - 15);
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
