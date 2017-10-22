package code;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class UberAppSimulator {
	
	private static ArrayList<Driver> drivers;
	private static ArrayList<Passenger> passengers;
	private static double rate;
	private static String fFileName;
	private static BufferedWriter writer;
	private static ArrayList<String> successfulRides;
	private static ArrayList<String> cancelledRides;
	private static JSONObject simulationObject;
	
	/**
	 * UberAppSimulator constructor that sets all of the values for drivers, passengers, successful/cancelled rides, and the JSON object for all the trips
	 * @return      void
	 */
	public UberAppSimulator() {
		drivers = new ArrayList<Driver>();
		passengers = new ArrayList<Passenger>();
		successfulRides = new ArrayList<String>();
		cancelledRides = new ArrayList<String>();
		simulationObject = new JSONObject();
	}
	
	/**
	 * Helper function that reads information from a specified text file and parses it to create an array of
	 * Driver objects and an array of Passenger objects with the correct information, as well as setting the current rate for rides.
	 * @return      void
	 */
	public static void readFileInfo() throws FileNotFoundException {
	    fFileName = System.getProperty("user.dir")+"/"+"Project1Input.txt";
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));
	    String[] scanned = null;
	    String driverName;
	    Double driverBalance;
	    String carTitle;
	    Status driverStatus;
	    double rating;
	    int numRatings;
	    int x, y;
	    Point driverLoc;
	    String passengerName;
	    Double passengerBalance;
	    Point passengerLoc;
	    try {
	      while (scanner.hasNextLine()){
	    	  scanned = scanner.nextLine().split(",");
	    	  if(scanned[0].charAt(0) == 'D') {
		    	  driverName = scanned[1];
		    	  driverBalance = Double.parseDouble(scanned[2]);
		    	  carTitle = scanned[3];
		    	  driverStatus = Status.valueOf(scanned[4]);
		    	  rating = Double.parseDouble(scanned[5]);
		    	  numRatings = Integer.parseInt(scanned[6]);
		    	  x = Integer.parseInt(scanned[7]);
		    	  y = Integer.parseInt(scanned[8]);
		    	  driverLoc = new Point(x,y);
		    	  Driver thisDriver = new Driver(driverName, driverBalance, carTitle, driverStatus, rating, numRatings, driverLoc);
		    	  drivers.add(thisDriver);
	    	  }
	    	  else if(scanned[0].charAt(0) == 'P') {
	    		  passengerName = scanned[1];
	    		  passengerBalance = Double.parseDouble(scanned[2]);
	    		  x = Integer.parseInt(scanned[3]);
	    		  y = Integer.parseInt(scanned[4]);
	    		  passengerLoc = new Point(x,y);
	    		  Passenger thisPassenger = new Passenger(passengerName, passengerBalance, passengerLoc);
	    		  passengers.add(thisPassenger);
	    	  }
	    	  else if(scanned[0].charAt(0) == 'R') {
	    		  rate = Double.parseDouble(scanned[1]);
	    	  }
	      }
	    }
	    finally{
	      scanner.close();
	    }
	}
	
	/**
	 * Validates the current drop off point to make sure it is in the grid, if not it cancels that ride
	 * @param  dropOff	Point that represents the current dropoff location to be checked
	 * @return      boolean that returns true if the specified drop off is in the grid and false if it is outside of the grid
	 */
	public static boolean validateDropOff(Point dropOff) {
		int x = (int) dropOff.getX();
		int y = (int) dropOff.getY();
		if(x > 299 || x < 0 || y > 299 || y < 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Writes the JSON object with all of the trip information to a json file.
	 * @return      void
	 */
	public static void writeJSONFile() throws IOException {
		writer = new BufferedWriter(new FileWriter("trips.json"));
		writer.write(simulationObject.toString());
		writer.close();
	}
	
	/**
	 * Writes the log file for the final state of the simulation, logs the driver and passenger info at the time
	 * of the simulation finish and specifies the users with successful or cancelled rides
	 * @return      void
	 */
	public static void logFinalState() throws IOException {
		writer = new BufferedWriter(new FileWriter("finalLog.txt"));
		writer.write("Log Of Final State");
		writer.newLine();
		writer.write("~~~~~~~~~~~~~~~~~~");
		writer.newLine();
		for(int i = 0; i < passengers.size(); i++) {
			writer.write(passengers.get(i).printPassengerInfo());
			writer.newLine();
		}
		writer.write("~~~~~~~~~~~~~~~~~~");
		writer.newLine();
		for(int i = 0; i < drivers.size(); i++) {
			writer.write(drivers.get(i).printDriverInfo());
			writer.newLine();
		}
		writer.write("~~~~~~~~~~~~~~~~~~");
		writer.newLine();
		writer.write("Number of Successful Rides: " + successfulRides.size() + " | Passengers with success: ");
		for(int i = 0; i < successfulRides.size(); i++) {
			writer.write(" " + successfulRides.get(i) + ",");
		}
		writer.newLine();
		writer.write("Cancelled Rides: " + cancelledRides.size() + " | Passengers who cancelled: ");
		for(int i = 0; i < cancelledRides.size(); i++) {
			writer.write(" " + cancelledRides.get(i) + ",");
		}
		writer.close();
		
	}
	
	/**
	 * Main function that simulates a ride for every passenger in the map, then logs those rides in JSON objects
	 * @param  args String [] that specifies the arguments of the program
	 * @return      void
	 */
	public static void main(String [] args) throws IOException {
		UberAppSimulator simulate = new UberAppSimulator();
		try {
			readFileInfo();
		} catch (FileNotFoundException e) {
			System.out.println("No File Found");
		}
		MapGrid testMap = new MapGrid(UberAppSimulator.drivers, UberAppSimulator.passengers);
		for(int i = 0; i < passengers.size(); i++) {
			int x = (int) (Math.random() * 300);
			int y = (int) (Math.random() * 300);
			Point randomDropOff = new Point(x, y);
//			Point randomDropOff = new Point(10, 305);
			String successOrCancel;
			if(validateDropOff(randomDropOff)) {
				Ride ride = new Ride(passengers.get(i).getLocation(), randomDropOff, rate);
				RideManager manager1 = new RideManager(ride, drivers, passengers.get(i), successfulRides, cancelledRides);
				JSONObject tripObject = new JSONObject();
				tripObject.put("Driver Name", drivers.get(0).getName());
				tripObject.put("Rating for this drive", drivers.get(0).getCurrentRating());
				tripObject.put("Car Title", drivers.get(0).getCarTitle());
				tripObject.put("Driver Rating", drivers.get(0).getRating());
				tripObject.put("Passenger Name", passengers.get(i).getName());
				tripObject.put("Initial Driver Balance", drivers.get(0).getInitialBalance());
				tripObject.put("Ending Driver Balance", drivers.get(0).getBalance());
				tripObject.put("Initial Passenger Balance", passengers.get(i).getInitialBalance());
				tripObject.put("Total Fair", passengers.get(i).getInitialBalance() - passengers.get(i).getBalance());
				tripObject.put("Ending Passenger Balance", passengers.get(i).getBalance());
				tripObject.put("Initial Driver Location", "( " + drivers.get(0).getInitialLocation().getX() + ", " + drivers.get(0).getInitialLocation().getY() + ")");
				tripObject.put("Initial Passenger Location", "( " + passengers.get(i).getPrevLocation().getX() + ", " + passengers.get(i).getPrevLocation().getY() + ")");
				if(passengers.get(i).getInitialBalance() == passengers.get(i).getBalance()) {
					successOrCancel = "Cancelled";
				}
				else {
					successOrCancel = "Successful";
				}
				tripObject.put("Ride Success or Cancelled", successOrCancel);
				tripObject.put("Pickup Location", "( " + passengers.get(i).getPrevLocation().getX() + ", " + passengers.get(i).getPrevLocation().getY() + ")");
				tripObject.put("Ending Driver and Passenger Location", "( " + randomDropOff.getX() + ", " + randomDropOff.getY() + ")");
				simulationObject.put("Trip: " + (i + 1), tripObject);
			} else {
				cancelledRides.add(passengers.get(i).getName());
				JSONObject tripObject = new JSONObject();
				tripObject.put("Passenger Name", passengers.get(i).getName());
				tripObject.put("Passenger Location", passengers.get(i).getLocation());
				tripObject.put("Ride Success or Cancelled", "Cancelled");
				tripObject.put("Dropoff location", "( " + randomDropOff.getX() + ", " + randomDropOff.getY() + ")");
				simulationObject.put("Trip: " + (i + 1), tripObject);
			}
		}
		logFinalState();
		writeJSONFile();
	}
}
