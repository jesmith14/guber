package code;

public class RideManager {
	private Ride currentRide;
	private MapGrid map;
	
	public RideManager(Ride ride, MapGrid map) {
		this.currentRide = ride;
		this.map = map;
	}
	
	public void updateBalances() {
		//todo: update driver and passenger balances when ride is complete
	}
	
	public float calculateFair() {
		return 0;
	}
	
	public void findNearestDriver() {
		//todo: use distance formula to find nearest driver
	}
	
	public void startDrive() {
		//todo: set conditionals for enroute, arrived, to destination, at destination
	}

}
