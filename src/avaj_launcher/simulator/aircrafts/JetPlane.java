package avaj_launcher.simulator.aircrafts;

import avaj_launcher.simulator.MyLogger;
import avaj_launcher.simulator.WeatherTower;
import avaj_launcher.weather.Coordinates;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather;
		weather = this.weatherTower.getWeather(this.coordinates);
		MyLogger.log.print("JetPlane#" + this.name + "(" + this.id + "): ");
		switch (weather) {
			case "SUN": {
				MyLogger.log.println("Tango Oscar Whiskey Echo Romeo, I start my ascent");
				this.coordinates.setLatitude((this.coordinates.getLatitude() + 10) % 200);
				this.coordinates.setHeight(Integer.min(this.coordinates.getHeight() + 2, 100));
				break;
			}
			case "RAIN": {
				MyLogger.log.println("Tango Oscar Whiskey Echo Romeo, my speed is reduced because of the rain.");
				this.coordinates.setLatitude((this.coordinates.getLatitude() + 5) % 200);
				break;
			}
			case "FOG": {
				MyLogger.log.println("Tango Oscar Whiskey Echo Romeo, I cannot see you.");
				this.coordinates.setLatitude((this.coordinates.getLatitude() + 1) % 200);
				break;
			}
			case "SNOW": {
				MyLogger.log.println("Tango Oscar Whiskey Echo Romeo, Merry Christmas");
				this.coordinates.setHeight(this.coordinates.getHeight() - 7);
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
