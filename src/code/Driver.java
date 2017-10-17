package code;

import java.awt.Point;

public class Driver implements Comparable<Driver> {
	private String name;
	private Double balance;
	private String carTitle;
	private Status status;
	private Double rating;
	private int numRatings;
	private Point location;
	private int distanceFromPassenger;
	
	public Driver(String name, Double balance, String carTitle, Status status, Double rating, int numRatings, Point location) {
		this.name = name;
		this.balance = balance;
		this.carTitle = carTitle;
		this.status = status;
		this.rating = rating;
		this.numRatings = numRatings;
		this.location = location;
		this.distanceFromPassenger = Integer.MAX_VALUE;
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
		this.balance = balance;
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
	}
	
	public Double getRating() {
		return this.rating;
	}
	
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void acceptPassenger() {
		
	}
	
	public void printDriverInfo() {
		System.out.println("Driver | Name: " + this.getName() + " | Balance: " + this.getBalance() + " | Car Title: " + this.getCarTitle() + " | Status: " + this.getStatus() + " | Rating: " + this.getRating() + " | Location: " + this.getLocation());
	}
	
	public void setDistanceFromPassenger(int num) {
		this.distanceFromPassenger = num;
	}
	
	public int getDistanceFromPassenger() {
		return this.getDistanceFromPassenger();
	}

	@Override
	public int compareTo(Driver o) {
        if(this.getDistanceFromPassenger() < o.getDistanceFromPassenger()){
            return 1;
        }
        return 0;
	}

}
