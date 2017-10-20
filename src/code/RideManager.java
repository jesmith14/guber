package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class RideManager {
	private Ride currentRide;
	private MapGrid map;
	private ArrayList<Driver> drivers;
	
	public RideManager(Ride ride, MapGrid map, ArrayList<Driver> drivers) {
		this.currentRide = ride;
		this.map = map;
		this.drivers = drivers;
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
		System.out.println("HERE");
		this.drivers.get(0).printDriverInfo();
	}
	
	public void startRide() {
		this.findNearestDriver();
		int i = 0;
		Driver currentDriver = this.drivers.get(i);
		while(currentDriver.getStatus() != Status.AVAILABLE) {
			i++;
			currentDriver = this.drivers.get(i);
		}
		currentDriver.setStatus(Status.ENROUTE);
		
		//todo: set conditionals for enroute, arrived, to destination, at destination
	}

}
