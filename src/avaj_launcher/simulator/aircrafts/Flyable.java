package avaj_launcher.simulator.aircrafts;

import avaj_launcher.simulator.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower weatherTower);
	String getName();
}
