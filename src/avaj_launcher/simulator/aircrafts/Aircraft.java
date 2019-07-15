package avaj_launcher.simulator.aircrafts;

import avaj_launcher.weather.Coordinates;

abstract class Aircraft {
    long id;
    String name;
    Coordinates coordinates;
    private static long idCounter = 0;

    Aircraft (String name, Coordinates coordinates) {
        this.coordinates = coordinates;
        this.name = name;
        this.id = Aircraft.nextId();
    }

    private static long nextId () {
        Aircraft.idCounter++;
        return (Aircraft.idCounter);
    }
}
