package code;

public class UberAppSimulator {
	public static void main(String [] args) {
		Driver testDriver1 = new Driver();
		Driver testDriver2 = new Driver();
		Driver testDriver3 = new Driver();
		Passenger testPassenger1 = new Passenger();
		Passenger testPassenger2 = new Passenger();
		Passenger testPassenger3 = new Passenger();
		Driver[] drivers = new Driver[3];
		Passenger[] passengers = new Passenger[3];
		drivers[0] = testDriver1;
		drivers[1] = testDriver2;
		drivers[2] = testDriver3;
		passengers[0] = testPassenger1;
		passengers[1] = testPassenger2;
		passengers[2] = testPassenger3;
		MapGrid testMap = new MapGrid(5, drivers, passengers);
		testMap.PrintMap();
	}
}
