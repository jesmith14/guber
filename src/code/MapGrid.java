package code;

import java.awt.Point;

public class MapGrid {
	private int[][] grid;
	private Passenger[] passengers;
	private Driver[] drivers;
	private int mapSize;
	
	/*
	 * MapGrid constructor 
	 * @params integer value n for size of the array
	 * Driver [] drivers that indicates current drivers
	 * Passenger [] passengers that indicates current passengers
	 */
	public MapGrid(int n, Driver[] drivers, Passenger[] passengers) {
		this.grid = new int[n][n];
		this.passengers = passengers;
		this.drivers = drivers;
		this.mapSize = n;
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
			this.grid[x][y] = 1;
		}
		for(int j = 0; j < passengers.length; j++) {
			x = (int) (Math.random() * this.mapSize);
			y = (int) (Math.random() * this.mapSize);
			while(grid[x][y] == 0){
				x = (int) (Math.random() * this.mapSize);
				y = (int) (Math.random() * this.mapSize);
				randomLocation = new Point(x, y);
//				this.passengers[i]
			}
			this.grid[x][y] = 2;
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
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if(grid[i][j] != 1) {
					grid[i][j] = 0;
					System.out.print(0 + "  ");
				}
				else {
					System.out.print(1 + "  ");
				}
			}
			System.out.println();
		}
	}

}
