package code;

import java.awt.Point;
import java.util.ArrayList;

public class MapGrid {
	private int[][] grid;
	private ArrayList<Passenger> passengers;
	private ArrayList<Driver> drivers;
	private final int mapSize = 300;
	

	/**
	 * MapGrid constructor 
	 * @param  drivers an ArrayList of drivers that specifies the current drivers to add to the grid
	 * @param	passengers	an ArrayList of passengers to be added to the grid
	 * @return      void
	 */
	public MapGrid(ArrayList<Driver> drivers, ArrayList<Passenger> passengers) {
		this.grid = new int[mapSize][mapSize];
		this.passengers = passengers;
		this.drivers = drivers;
		this.setUpMap();
	}


	/**
	 * sets up the map based on the driver and passenger locations by adding 1 to every spot that has a person
	 * and indicating a 0 for empty fields
	 * @return      void
	 */
	public void setUpMap() {
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
	
	/**
	 * Helper funciton to print the current map for testing
	 * @return      void
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
