package code;

import java.awt.Point;

public class Passenger {
	private String name;
	private double balance;
	private Point location;
	private double initialBalance;
	private Point previousLocation;
	
	public Passenger(String name, double balance, Point location) {
		this.name = name;
		this.balance = balance;
		this.location = location;
		this.initialBalance = balance;
		this.previousLocation = location;
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
		this.initialBalance = this.getBalance();
		this.balance = balance;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.previousLocation = this.getLocation();
		this.location = location;
		System.out.println("**Previous location in the passenger class: " + previousLocation);
		System.out.println("**New location in the passenger class: " + this.location);
	}
	
	public void setInitialBalance(double balance) {
		this.initialBalance = balance;
	}
	
	public double getInitialBalance() {
		return this.initialBalance;
	}
	
	public String printPassengerInfo() {
		return ("Passenger | Name: " + this.getName() + " | Balance: " + this.getBalance() + " | Location: (" + (int)this.getLocation().getX() + ", " + (int)this.getLocation().getY() + ")");
	}
	
	public Point getPrevLocation() {
		return this.previousLocation;
	}
}

