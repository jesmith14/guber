package code;

import java.awt.Point;
import java.util.ArrayList;

public class MapGrid {
	private int[][] grid;
	private ArrayList<Passenger> passengers;
	private ArrayList<Driver> drivers;
	private final int mapSize = 300;
	
	/*
	 * MapGrid constructor 
	 * @params integer value n for size of the array
	 * Driver [] drivers that indicates current drivers
	 * Passenger [] passengers that indicates current passengers
	 */
	public MapGrid(ArrayList<Driver> drivers, ArrayList<Passenger> passengers) {
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
		int x;
		int y;
		for(int i = 0; i < drivers.size(); i ++) {
			x = (int)drivers.get(i).getLocation().getX();
			y = (int)drivers.get(i).getLocation().getY();
			this.grid[x][y]++;
		}
		for(int j = 0; j < passengers.size(); j++) {
			x = (int)passengers.get(j).getLocation().getX();
			y = (int)passengers.get(j).getLocation().getY();
			this.grid[x][y]++;
		}
	}
	
	public int[][] getGrid() {
		return this.grid;
	}
	
	public ArrayList<Driver> getDrivers() {
		return this.drivers;
	}
	
	public ArrayList<Passenger> getPassengers() {
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
