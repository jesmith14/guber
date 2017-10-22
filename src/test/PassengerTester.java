package test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import code.Driver;
import code.Passenger;
import code.Status;

public class PassengerTester {
	@Test
	public void TestSetLocation() {
		Passenger p = new Passenger("Sierra", 2000, new Point(20, 52));
		Passenger p2 = new Passenger("Jessie", 203, new Point(5, 100));
		p.setLocation(new Point(10, 50));
		assertEquals(p.getPrevLocation(), new Point(20, 52));
	}
	
	@Test
	public void TestSetBalance() {
		Passenger p = new Passenger("Sierra", 2000, new Point(20, 52));
		p.setBalance(400);
		assertEquals(p.getInitialBalance(), 2000, 0);
	}
}
