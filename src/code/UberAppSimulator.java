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
	private static double initialDriverBalance;
	private static double initialPassengerBalance;
	
	public UberAppSimulator() {
		drivers = new ArrayList<Driver>();
		passengers = new ArrayList<Passenger>();
		successfulRides = new ArrayList<String>();
		cancelledRides = new ArrayList<String>();
		simulationObject = new JSONObject();
		initialDriverBalance = 0;
		initialPassengerBalance = 0;
	}
	
	public static void readFileInfo() throws FileNotFoundException {
//		System.out.println("Reading from file.");
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
//		    	  thisDriver.printDriverInfo();
		    	  drivers.add(thisDriver);
	    	  }
	    	  else if(scanned[0].charAt(0) == 'P') {
	    		  passengerName = scanned[1];
	    		  passengerBalance = Double.parseDouble(scanned[2]);
	    		  x = Integer.parseInt(scanned[3]);
	    		  y = Integer.parseInt(scanned[4]);
	    		  passengerLoc = new Point(x,y);
	    		  Passenger thisPassenger = new Passenger(passengerName, passengerBalance, passengerLoc);
//	    		  thisPassenger.printPassengerInfo();
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
	
	public static void writeJSONFile() throws IOException {
		writer = new BufferedWriter(new FileWriter("trips.json"));
		writer.write(simulationObject.toString());
		writer.close();
	}
	
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
				Point initialPassengerLocation = passengers.get(i).getLocation();
				Ride ride = new Ride(passengers.get(i).getLocation(), randomDropOff, rate);
				RideManager manager1 = new RideManager(ride, testMap, drivers, passengers.get(i), successfulRides, cancelledRides, initialDriverBalance, initialPassengerBalance);
				JSONObject tripObject = new JSONObject();
				tripObject.put("Driver Name", drivers.get(0).getName());
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
				System.out.println("Dropoff outside of grid area, please specify new dropoff");
			}
		}
		logFinalState();
		writeJSONFile();
	}
}
