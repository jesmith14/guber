package code;

import java.awt.Point;

public class Passenger {
	private String name;
	private float balance;
	public int rating;
	public Point location;
	
	public Passenger() {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
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
	
	public void requestDriver() {
		
	}
}

