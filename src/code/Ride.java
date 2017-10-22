package code;

import java.awt.Point;
import java.util.ArrayList;

public class Ride {
	private Point pickup;
	private Point dropoff;
	private double rate;
	
	public Ride(Point pickup, Point dropoff, double rate) {
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.rate = rate;
	}
	
	public Point getPickup() {
		return this.pickup;
	}
	
	public void setPickup(Point location) {
		this.pickup = location;
	}
	
	public Point getDropOff() {
		return this.dropoff;
	}
	
	public void setDropOff(Point location) {
		this.dropoff = location;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	public double getRate() {
		return this.rate;
	}
}
