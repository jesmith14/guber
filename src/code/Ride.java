package code;

import java.awt.Point;

public class Ride {
	private Point pickup;
	private Point dropoff;
	private float fair;
	private boolean enRoute;
	private boolean arrivedAtPickup;
	private boolean inProgress;
	private boolean cancelled;
	private boolean arrivedAtDropoff;
	
	public Ride(Point pickup, Point dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
	}
	
	public Point getPickup() {
		return this.pickup;
	}
	
	public Point getDropOff() {
		return this.dropoff;
	}
	
	public float calculateFair() {
		return 0;
	}
	
	public float getFair() {
		return this.fair;
	}
	
	public void updateBalances() {
		//todo: update driver and passenger balances when ride is complete
	}
	
	public boolean getEnRoute() {
		return this.enRoute;
	}
	
	public boolean getArrivedAtPickup() {
		return this.arrivedAtPickup;
	}
	
	public boolean getInProgress() {
		return this.inProgress;
	}
	
	public boolean getCancelled() {
		return this.cancelled;
	}
	
	public boolean getArrivedAtDropoff() {
		return this.arrivedAtDropoff;
	}
}
