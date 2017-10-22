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
	
	/**
	 * Constructor for a RideManager that initializes the private values and starts the ride.
	 * @param  ride	Ride object that specifies current ride
	 * @param	map	MapGrid object to specify the current mapGrid
	 * @param	drivers	ArrayList of all Drivers on the mapGrid
	 * @param	passenger	Passenger object of the current passenger requesting a ride
	 * @param	successfulRides	ArrayList of Strings representing the Passengers with successful rides
	 * @param	cancelledRides	ArrayList of Strings representing the Passengers with cancelled rides
	 * @param	
	 * @return      void
	 */
	public RideManager(Ride ride, ArrayList<Driver> drivers, Passenger passenger, ArrayList<String> successfulRides, ArrayList<String> cancelledRides) {
		this.currentRide = ride;
		this.drivers = drivers;
		this.passenger = passenger;
		this.successfulRides = successfulRides;
		this.cancelledRides = cancelledRides;
		this.startRide();

	}

	/**
	 * Finds the nearest driver to the current passenger's pickup location and then 
	 * sorts the array of drivers from closest to furthest. If there is a tie for
	 * the closest drivers, it goes with the driver with the highest rating
	 * @return      void
	 */
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
	}
	
	/**
	 * Gets the fair for the current ride by calculating the distance between the passenger to driver
	 * and then the distance of the ride times the amount of the current rate
	 * @param  driverDistance	double that specifies the distance from the driver to the passenger
	 * @return      double that specifies the fair for the current ride
	 */
	public double getFair(double driverDistance) {
		double rideDistance = (int) Math.sqrt(Math.pow((currentRide.getDropOff().getX() - currentRide.getPickup().getX()), 2) + Math.pow((currentRide.getDropOff().getY() - currentRide.getPickup().getY()),2));
		double rate = currentRide.getRate();
		double totalDistance = rideDistance + driverDistance;
		double fair = (totalDistance * rate);
		this.fair = fair;
		return fair;
	}
	
	/**
	 * Validates that the current passenger has enough money in their account to
	 * pay for the total fair of the ride. If not, it cancels the ride and notifies the 
	 * passenger.
	 * @param  fair	double that indicates the total fair for the upcoming ride
	 * @return      boolean	true if the user has enough money to cover the fair, false if they do not
	 */
	public boolean validateFair(double fair) {
		if(this.passenger.getBalance() < fair) {
			this.cancelledRides.add(passenger.getName());
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * Starts the current ride by finding the nearest driver, finding the nearest fair,
	 * validating the passenger can pay the fair, setting the status of the driver as they
	 * make their way to the passenger, and calling the startTransit function
	 * @return      void
	 */
	public void startRide() {
		this.findNearestDriver();
		int i = 0;
		this.currentDriver = this.drivers.get(i);
		while(currentDriver.getStatus() != Status.AVAILABLE) {
			i++;
			this.currentDriver = this.drivers.get(i);
		}
		double fair = this.getFair(this.currentDriver.getDistanceFromPassenger());
		if(this.validateFair(fair)) {
			this.currentDriver.setStatus(Status.ENROUTE);
			this.currentDriver.setStatus(Status.ARRIVED);
			this.startTransit();
		}
	}
	
	/**
	 * Sets the status of the driver while the ride starts and makes it to the destination, then
	 * updates the driver and passenger locations to the destination upon arrival. Then calls the finishRide function.
	 * @return      void
	 */
	public void startTransit() {
		this.currentDriver.setStatus(Status.INTRANSIT);
		this.currentDriver.setStatus(Status.FINISHED);
		this.passenger.setLocation(currentRide.getDropOff());
		this.currentDriver.setLocation(currentRide.getDropOff());
		this.finishRide();
	}
	
	/**
	 * Updates the balances of the passenger and the driver to subtract the drive fair from the passenger's balance
	 * and adding that amount to the driver's balance. Then chooses a rating randomly for the passenger to give to
	 * the driver between 1 and 5, then applies the new rating to the driver's overall rating and sets the status
	 * of the driver to available, then adds the passenger to the list of successful rides.
	 * @return      void
	 */
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

}
