package code;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UberAppSimulator {
	
	private static ArrayList<Driver> drivers;
	private static ArrayList<Passenger> passengers;
	
	public UberAppSimulator() {
		drivers = new ArrayList<Driver>();
		passengers = new ArrayList<Passenger>();
	}
	
	public static void readFileInfo() throws FileNotFoundException {
		System.out.println("Reading from file.");
	    String fFileName = System.getProperty("user.dir")+"/"+"Project1Input.txt";
	    Scanner scanner = new Scanner(new FileInputStream(fFileName));
	    String[] scanned = null;
	    String driverName;
	    Double driverBalance;
	    String carTitle;
	    Status driverStatus;
	    Double rating;
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
		    	  thisDriver.printDriverInfo();
		    	  drivers.add(thisDriver);
	    	  }
	    	  else {
	    		  passengerName = scanned[1];
	    		  passengerBalance = Double.parseDouble(scanned[2]);
	    		  x = Integer.parseInt(scanned[3]);
	    		  y = Integer.parseInt(scanned[4]);
	    		  passengerLoc = new Point(x,y);
	    		  Passenger thisPassenger = new Passenger(passengerName, passengerBalance, passengerLoc);
	    		  thisPassenger.printPassengerInfo();
	    		  passengers.add(thisPassenger);
	    	  }
	      }
	    }
	    finally{
	      scanner.close();
	    }
	}
	
	
	public static void main(String [] args) {
		UberAppSimulator simulate = new UberAppSimulator();
//		System.out.println("Working Directory = " +
//	              System.getProperty("user.dir"));
		try {
			readFileInfo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapGrid testMap = new MapGrid(UberAppSimulator.drivers, UberAppSimulator.passengers);
//		testMap.PrintMap();
	}
}
