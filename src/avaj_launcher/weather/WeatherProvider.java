package avaj_launcher.weather;

import java.util.concurrent.ThreadLocalRandom;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String weather[] = {"SUN", "RAIN", "FOG", "SNOW"};
	private WeatherProvider () {}

	public static WeatherProvider getProvider () {
		return weatherProvider;
	}

	public String getCurrentWeather (Coordinates coordinates) {
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();
		int myNumber = longitude + latitude + height;
		return weather[(ThreadLocalRandom.current().nextInt(0, 4) + myNumber) % 4];
	}
}
