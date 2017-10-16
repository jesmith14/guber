package code;

import java.awt.Point;
import java.util.Arrays;

public class MapGrid {
	private int[][] grid;
	private Passenger[] passengers;
	private Driver[] drivers;
	private final int mapSize = 50;
	
	/*
	 * MapGrid constructor 
	 * @params integer value n for size of the array
	 * Driver [] drivers that indicates current drivers
	 * Passenger [] passengers that indicates current passengers
	 */
	public MapGrid(Driver[] drivers, Passenger[] passengers) {
		this.grid = new int[mapSize][mapSize];
		this.passengers = passengers;
		this.drivers = drivers;
		this.setUpMap();
	}

	/*
	 * Function to set up the 2D array, initializing 0 for empty values, 
	 * 1 for a driver spot, and 2 for a passenger spot
	 */
	public void setUpMap() {
		Point randomLocation;
		int x, y;
		for(int i = 0; i < drivers.length; i ++) {
			x = (int) (Math.random() * this.mapSize);
			y = (int) (Math.random() * this.mapSize);
			randomLocation = new Point(x, y);
			this.drivers[i].setLocation(randomLocation);
			this.grid[x][y]++;
		}
		for(int j = 0; j < passengers.length; j++) {
			x = (int) (Math.random() * this.mapSize);
			y = (int) (Math.random() * this.mapSize);
			randomLocation = new Point(x, y);
			this.passengers[j].setLocation(randomLocation);
			this.grid[x][y]++;
		}
	}
	
	public int[][] getGrid() {
		return this.grid;
	}
	
	public Driver[] getDrivers() {
		return this.drivers;
	}
	
	public Passenger[] getPassengers() {
		return this.passengers;
	}
	
	/*
	 * Helper function to print the current map for testing
	 */
	public void PrintMap() {
		for(int i = 0; i < this.grid.length; i++) {
			for(int j = 0; j < this.grid[0].length; j++) {
					System.out.print(this.grid[i][j]);
			}
			System.out.println();
		}
	}

}
