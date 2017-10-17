package code;

import java.awt.Point;

public class Passenger {
	private String name;
	private double balance;
	public Point location;
	
	public Passenger(String name, double balance, Point location) {
		this.name = name;
		this.balance = balance;
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
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void requestDriver() {
		
	}
	
	public void printPassengerInfo() {
		System.out.println("Passenger | Name: " + this.getName() + " | Balance: " + this.getBalance() + " | Location: " + this.getLocation());
	}
}

