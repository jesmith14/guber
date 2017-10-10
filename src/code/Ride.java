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
	
	public boolean getEnRoute() {
		return this.enRoute;
	}
	
	public void setEnRoute(boolean enRoute) {
		this.enRoute = enRoute;
	}
	
	public boolean getArrivedAtPickup() {
		return this.arrivedAtPickup;
	}
	
	public void setArrivedAtPickup(boolean arrivedAtPickup) {
		this.arrivedAtPickup = arrivedAtPickup;
	}
	
	public boolean getInProgress() {
		return this.inProgress;
	}
	
	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	
	public boolean getCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean getArrivedAtDropoff() {
		return this.arrivedAtDropoff;
	}
	
	public void setArrivedAtDropoff(boolean arrivedAtDropOff) {
		this.arrivedAtDropoff = arrivedAtDropOff;
	}
}
