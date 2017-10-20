package code;

import java.awt.Point;
import java.util.ArrayList;

public class Ride {
	private Point pickup;
	private Point dropoff;
	private float fair;
	private Status status;
	
	public Ride(Point pickup, Point dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
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
	
	public float getFair() {
		return this.fair;
	}
	
	public void setFair(float fair) {
		this.fair = fair;
	}
}
