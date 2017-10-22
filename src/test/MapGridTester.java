package test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import code.Driver;
import code.MapGrid;
import code.Passenger;
import code.Status;

public class MapGridTester {

	@Test
	public void TestSetUpMap() {
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(30, 207));
		Passenger p = new Passenger("Sierra", 2000, new Point(20, 52));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		MapGrid grid = new MapGrid(drivers, passengers);
		grid.setUpMap();
		assertEquals(grid.getGrid()[30][207], 0);
	}
}
