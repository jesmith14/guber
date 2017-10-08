package code;

public class UberAppSimulator {
	public static void main(String [] args) {
		Driver testDriver = new Driver();
		Passenger testPassenger = new Passenger();
		Driver[] drivers = new Driver[1];
		Passenger[] passengers = new Passenger[1];
		drivers[0] = testDriver;
		passengers[0] = testPassenger;
		MapGrid testMap = new MapGrid(5, drivers, passengers);
		testMap.PrintMap();
	}
}
