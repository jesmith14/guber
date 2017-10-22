package test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import code.Driver;
import code.Passenger;
import code.Ride;
import code.RideManager;
import code.Status;

public class RideManagerTester {
	@Test
	public void TestFindNearestDriver() {
		Passenger p = new Passenger("Sierra", 2000, new Point(1, 1));
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(1, 1));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(test1);
		drivers.add(test2);
		Ride thisRide = new Ride(new Point(20, 52), new Point(100, 100), 3.0);
		ArrayList<String> successful = new ArrayList<String>();
		ArrayList<String> cancelled = new ArrayList<String>();
		RideManager manager = new RideManager(thisRide, drivers, p, successful, cancelled);
		manager.findNearestDriver();
		assertEquals(drivers.get(0).getName(), "Claudia");
	}
	
	@Test
	public void TestGetFair() {
		Passenger p = new Passenger("Sierra", 2000, new Point(1, 1));
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(1, 1));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(test1);
		drivers.add(test2);
		Ride thisRide = new Ride(new Point(20, 52), new Point(100, 100), 3.0);
		ArrayList<String> successful = new ArrayList<String>();
		ArrayList<String> cancelled = new ArrayList<String>();
		RideManager manager = new RideManager(thisRide, drivers, p, successful, cancelled);
		manager.findNearestDriver();
		double dist = drivers.get(0).getDistanceFromPassenger();
		assertEquals(manager.getFair(dist), 441.0, 0);
	}
	
	@Test
	public void TestValidateFairTrue() {
		Passenger p = new Passenger("Sierra", 2000, new Point(1, 1));
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(1, 1));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(test1);
		drivers.add(test2);
		Ride thisRide = new Ride(new Point(20, 52), new Point(100, 100), 3.0);
		ArrayList<String> successful = new ArrayList<String>();
		ArrayList<String> cancelled = new ArrayList<String>();
		RideManager manager = new RideManager(thisRide, drivers, p, successful, cancelled);
		manager.findNearestDriver();
		double dist = drivers.get(0).getDistanceFromPassenger();
		assertEquals(manager.validateFair(manager.getFair(dist)), true);
	}
	
	@Test
	public void TestValidateFairFalse() {
		Passenger p = new Passenger("Sierra", 20, new Point(1, 1));
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(1, 1));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(test1);
		drivers.add(test2);
		Ride thisRide = new Ride(new Point(20, 52), new Point(100, 100), 3.0);
		ArrayList<String> successful = new ArrayList<String>();
		ArrayList<String> cancelled = new ArrayList<String>();
		RideManager manager = new RideManager(thisRide, drivers, p, successful, cancelled);
		manager.findNearestDriver();
		double dist = drivers.get(0).getDistanceFromPassenger();
		assertEquals(manager.validateFair(manager.getFair(dist)), false);
	}
	
	@Test
	public void TestStartRideTransitandFinish() {
		Passenger p = new Passenger("Sierra", 20000, new Point(1, 1));
		Driver test1 = new Driver("Jessie", 40.0, "Hyundai Accent", Status.AVAILABLE, 4.5, 20, new Point(0, 10));
		Driver test2 = new Driver("Claudia", 50.9, "Honda Civic", Status.AVAILABLE, 2.5, 37, new Point(1, 1));
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(test1);
		drivers.add(test2);
		Ride thisRide = new Ride(new Point(20, 52), new Point(100, 100), 3.0);
		ArrayList<String> successful = new ArrayList<String>();
		ArrayList<String> cancelled = new ArrayList<String>();
		RideManager manager = new RideManager(thisRide, drivers, p, successful, cancelled);
		assertEquals(successful.size(), 1);
	}
	
	
	

}
