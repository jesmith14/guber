package test;
import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import code.Driver;
import code.Passenger;
import code.Status;

public class DriverTester {
	@Test
	public void TestSetBalance() {
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		test1.setBalance(400.5);
		assertEquals(test1.getInitialBalance(), 40.0, 0);
	}
	
	@Test
	public void TestSetRating() {
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		test1.setRating(3);
		assertEquals(test1.getRating(), 4.0, 0);
	}
	
	@Test
	public void TestSetLocation() {
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		test1.setLocation(new Point(30, 40));
		assertEquals(test1.getInitialLocation(), new Point(0, 10));
	}
	
	@Test
	public void TestCompareTo() {
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(30, 207));
		test1.setDistanceFromPassenger(52);
		test2.setDistanceFromPassenger(120);
		assertEquals(test1.compareTo(test2), -68);
	}
	
}
