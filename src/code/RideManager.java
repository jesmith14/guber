package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class RideManager {
	private Ride currentRide;
	private MapGrid map;
	private ArrayList<Driver> drivers;
	private Passenger passenger;
	private Driver currentDriver;
	private double fair;
	private ArrayList<String> successfulRides;
	private ArrayList<String> cancelledRides;
	private double initialDriverBalance;
	private double initialPassengerBalance;
	
	
	public RideManager(Ride ride, MapGrid map, ArrayList<Driver> drivers, Passenger passenger, ArrayList<String> successfulRides, ArrayList<String> cancelledRides, double initialDriverBalance, double initialPassengerBalance) {
		this.currentRide = ride;
		this.map = map;
		this.drivers = drivers;
		this.passenger = passenger;
		this.successfulRides = successfulRides;
		this.cancelledRides = cancelledRides;
		this.initialPassengerBalance = initialPassengerBalance;
		this.initialDriverBalance = initialDriverBalance;
		this.startRide();

	}
	
	public void updateBalances() {
		//todo: update driver and passenger balances when ride is complete
	}
	
	public float calculateFair() {
		return 0;
	}
	
	public void findNearestDriver() {
		int currentDistance, driverX, driverY, pickupX, pickupY;
		pickupX = (int)this.currentRide.getPickup().getX();
		pickupY = (int)this.currentRide.getPickup().getY();
		for(int i = 0; i < drivers.size(); i++) {
			driverX = (int)this.drivers.get(i).getLocation().getX();
			driverY = (int)this.drivers.get(i).getLocation().getY();
			currentDistance = (int) Math.sqrt(Math.pow((driverX - pickupX), 2) + Math.pow((driverY - pickupY),2));
			this.drivers.get(i).setDistanceFromPassenger(currentDistance);
		}
		Collections.sort(this.drivers);
		if(this.drivers.get(0).getDistanceFromPassenger() == this.drivers.get(1).getDistanceFromPassenger()){
			if(drivers.get(0).getRating() < drivers.get(1).getRating()) {
				drivers.set(0, drivers.get(1));
			}
		}
//		System.out.println("Closest Driver: " + this.drivers.get(0).printDriverInfo());
		
	}
	
	public double getFair(double driverDistance) {
		double rideDistance = (int) Math.sqrt(Math.pow((currentRide.getDropOff().getX() - currentRide.getPickup().getX()), 2) + Math.pow((currentRide.getDropOff().getY() - currentRide.getPickup().getY()),2));
		double rate = currentRide.getRate();
		double totalDistance = rideDistance + driverDistance;
		double fair = (totalDistance * rate);
//		System.out.println("The current fair is: " + fair);
		this.fair = fair;
		return fair;
	}
	
	public boolean validateFair(double fair) {
		if(this.passenger.getBalance() < fair) {
			System.out.println("Passenger " + passenger.getName() + " does not have enough money to cover fair. Ride Cancelled");
			this.cancelledRides.add(passenger.getName());
			return false;
		}
		else {
			return true;
		}
	}
	
	
	
	public void startRide() {
		this.findNearestDriver();
		int i = 0;
		this.currentDriver = this.drivers.get(i);
		while(currentDriver.getStatus() != Status.AVAILABLE) {
			i++;
			this.currentDriver = this.drivers.get(i);
		}
		this.initialDriverBalance = currentDriver.getBalance();
		this.initialPassengerBalance = passenger.getBalance();
		double fair = this.getFair(this.currentDriver.getDistanceFromPassenger());
		if(this.validateFair(fair)) {
			this.currentDriver.setStatus(Status.ENROUTE);
//			this.currentDriver.setLocation(this.currentRide.getPickup());
			this.currentDriver.setStatus(Status.ARRIVED);
			this.startTransit();
		}
	}
	
	public void startTransit() {
		this.currentDriver.setStatus(Status.INTRANSIT);
//		System.out.println("Passenger " + this.passenger.getName() + " has reached their destination.");
		this.currentDriver.setStatus(Status.FINISHED);
		this.passenger.setLocation(currentRide.getDropOff());
		this.currentDriver.setLocation(currentRide.getDropOff());
		this.finishRide();
	}
	
	public void finishRide() {
		double passengerPrevBalance = this.passenger.getBalance();
		double driverPrevBalance = this.currentDriver.getBalance();
		this.currentDriver.setInitialBalance(driverPrevBalance);
		this.passenger.setInitialBalance(passengerPrevBalance);
		this.passenger.setBalance(passengerPrevBalance - this.fair);
		this.currentDriver.setBalance(driverPrevBalance + this.fair);
		int randomRating = (int)(Math.random() * 5);
		if(randomRating == 0) {
			randomRating = 1;
		}
		this.currentDriver.setRating(randomRating);
		this.currentDriver.setStatus(Status.AVAILABLE);
		this.successfulRides.add(passenger.getName());
	}
	
	public double getInitialDriverBalance() {
		return this.initialDriverBalance;
	}
	
	public double getInitialPassengerBalance() {
		return this.initialPassengerBalance;
	}
	
//	public int getSuccessfulRides() {
//		return this.successfulRide;
//	}
//	
//	public int getCancelledRides() {
//		System.out.println("HEREHERHE" + this.cancelledRide);
//		return this.cancelledRide;
//	}

}
