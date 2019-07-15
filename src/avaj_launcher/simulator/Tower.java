package avaj_launcher.simulator;

import avaj_launcher.simulator.aircrafts.Flyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<>();
	private List<Flyable> leavers = new ArrayList<>();

	public void register (Flyable flyable) {
		observers.add(flyable);
		MyLogger.log.print(flyable.getName() + ", here is the Tower, you've enter our aerial space. ");
		MyLogger.log.println("You are now registered in the DataBase, please, sent regular information on the weather.");
	}

	public void unregister (Flyable flyable) {
		leavers.add(flyable);
	}

	void conditionsChanged() {
		for (Flyable fly : observers) {
			fly.updateConditions();
		}
		if (leavers.size() != 0) {
			for (Flyable fly : leavers) {
				for (Flyable obs : observers) {
					if (fly == obs) {
						MyLogger.log.print(obs.getName() + ", here is the Tower.");
						MyLogger.log.println("You are now unregistered from our DataBase.");
						observers.remove(obs);
						break;
					}
				}
			}
			leavers.clear();
		}
	}
}
