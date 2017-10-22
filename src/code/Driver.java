package code;

import java.awt.Point;

public class Driver implements Comparable<Driver> {
	private String name;
	private Double balance;
	private String carTitle;
	private Status status;
	private double currentDriveRating;
	private double rating;
	private int numRatings;
	private Point location;
	private int distanceFromPassenger;
	private Point previousLocation;
	private Double initialBalance;
	
	/**
	 * Constructor for the Driver Class, sets all initial information for a driver.
	 * @param  name  String that specifies Driver name
	 * @param	balance	Double that specifies driver starting balance
	 * @param	carTitle	String that specifies the driver's car title
	 * @param	status	Status type that specifies the driver's current status
	 * @param	rating	Double that specifies the driver's current rating
	 * @param	numRatings	integer that specifies how many ratings the driver has recieved
	 * @param	location	Point that specifies driver's current location
	 * @return      void
	 */
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
		this.currentDriveRating = 5;
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
		this.balance = (balance * 0.8);
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
	
	/**
	 * Sets the rating for a driver by averaging the new rating with the old one
	 * based on how many ratings the driver has had.
	 * @param  rating  an integer between 1 and 5 that specifies the passengers rating for the driver
	 * @return      void
	 */
	public void setRating(int rating) {
		numRatings++;
		this.currentDriveRating = rating;
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
	
	/**
	 * Gets all info from a driver compiled into one string.
	 * @return      String
	 */
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
	
	public Double getCurrentRating() {
		return this.currentDriveRating;
	}

	/**
	 * Overridden compareTo method from the Comparable interface. Compares two drivers based on their
	 * distance from a specified passenger location and returns the closest one.
	 * @param  Driver  A driver, o, to compare to the current driver.
	 * @return      Integer
	 */
	@Override
	public int compareTo(Driver o) {
        return (int)(this.getDistanceFromPassenger() - o.getDistanceFromPassenger());
	}

}
