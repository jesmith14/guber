package code;

import java.awt.Point;

public class Driver implements Comparable<Driver> {
	private String name;
	private Double balance;
	private String carTitle;
	private Status status;
	private double rating;
	private int numRatings;
	private Point location;
	private int distanceFromPassenger;
	private Point previousLocation;
	private Double initialBalance;
	
	public Driver(String name, Double balance, String carTitle, Status status, double rating, int numRatings, Point location) {
		this.name = name;
		this.balance = balance;
		this.carTitle = carTitle;
		this.status = status;
		this.rating = rating;
		this.numRatings = numRatings;
		this.location = location;
		this.distanceFromPassenger = Integer.MAX_VALUE;
		this.previousLocation = location;
		this.initialBalance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.initialBalance = this.getBalance();
		this.balance = balance;
		System.out.println("Old balance: " + this.initialBalance);
		System.out.println("New Balance: " + this.balance);
	}
	
	public String getCarTitle() {
		return this.carTitle;
	}
	
	public void setCarTitle(String carTitle) {
		this.carTitle = carTitle;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
		if(this.status == status.ARRIVED) {
//			System.out.println("Car has arrived at destination. It is a " + this.getCarTitle());
		}
		else if(this.status == status.AVAILABLE) {
//			System.out.println("Driver " + this.getName() + " is now available.");
		}
		else if(this.status == status.ENROUTE) {
//			System.out.println("Driver " + this.getName() + " is on their way!");
		}
		else if(this.status == status.INTRANSIT) {
//			System.out.println("Ride with " + this.getName() + " is in progress!");
		}
		else if(this.status == status.FINISHED) {
//			System.out.println("Ride complete. Please give " + this.getName() + " a rating.");
		}
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setRating(int rating) {
		numRatings++;
		double prevRating = this.rating;
		double newRating = rating;
		this.rating = (int)(((prevRating * numRatings)  + newRating)/ (numRatings + 1));
		this.numRatings++;
//		System.out.println("You gave " + this.getName() + " a rating of " + rating + ". " + this.getName() + "'s overall rating is now " + this.getRating());
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.previousLocation = this.getLocation();
		this.location = location;
	}
	
	public void acceptPassenger() {
		
	}
	
	public String printDriverInfo() {
		return ("Driver | Name: " + this.getName() + " | Balance: " + this.getBalance() + " | Car Title: " + this.getCarTitle() + " | Status: " + this.getStatus() + " | Rating: " + this.getRating() + " | Location: (" + (int)this.getLocation().getX() + ", " + (int)this.getLocation().getY() + ")");
	}
	
	public void setDistanceFromPassenger(int num) {
		this.distanceFromPassenger = num;
	}
	
	public int getDistanceFromPassenger() {
		return this.distanceFromPassenger;
	}
	
	public Point getInitialLocation() {
		return this.previousLocation;
	}
	
	public void setInitialBalance(Double balance) {
		this.initialBalance = balance;
	}
	
	public Double getInitialBalance() {
		return this.initialBalance;
	}

	@Override
	public int compareTo(Driver o) {
        return (int)(this.getDistanceFromPassenger() - o.getDistanceFromPassenger());
	}

}
