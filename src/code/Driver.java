package code;

import java.awt.Point;

public class Driver {
	private String name;
	private double balance;
	private String carTitle;
	private Status status;
	private int rating;
	private Point location;
	
	public Driver(String name, double balance, String carTitle, Status status, int rating, Point location) {
		this.name = name;
		this.balance = balance;
		this.carTitle = carTitle;
		this.status = status;
		this.rating = rating;
		this.location = location;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getBalance() {
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
	
	public int getRating() {
		return this.rating;
	}
	
	public void setRating(int rating) {
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

}
