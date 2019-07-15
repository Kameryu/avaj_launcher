package avaj_launcher.simulator;

import avaj_launcher.weather.Coordinates;
import avaj_launcher.weather.WeatherProvider;

public class WeatherTower extends Tower {
	public String getWeather (Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	void changeWeather () {
		this.conditionsChanged();
	}
}
