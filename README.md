# guber
A simplified version of Uber

Included is:
- UML Diagram for project
- Input file for simulation
- 2 Log Files for output
- Java Docs
- explanation for uber eats
- text files for git links

## How the input text file is formatted:
1) Value(D=Driver P=Passenger R=Rate)
2) Driver: Name, balance, carTitle, Status, currentRating, number of Ratings, locationX, locationY
3) Passenger: Name, balance, locationX, locationY

## How the finalLog.txt output text file is formatted:
~~~~~
Passengers:
Name | Balance | Final Location
~~~~~
Drivers: 
Name | Balance | Car Title | Status | Rating | Final Location
~~~~
Number of successful rides : With names of passengers who's rides were successful
Number of cancelled rides : With names of passengers who's rides were cancelled (due to insufficient funds)

## How the trips.json file is formatted:
1) Put all json into a json online formatter for easier reading
2) Each trip is it's own upper level json object
3) Within each trip, you will find: (an example is laid out below)
      "Car Title":"Hyundai Accent",
      "Ending Passenger Balance":970.0,
      "Initial Passenger Balance":1500.0,
      "Rating for this drive":1.0,
      "Initial Passenger Location":"( 10.0, 10.0)",
      "Pickup Location":"( 10.0, 10.0)",
      "Driver Rating":4.0,
      "Passenger Name":"Megha",
      "Ride Success or Cancelled":"Successful",
      "Driver Name":"Jessie",
      "Total Fair":530.0,
      "Ending Driver and Passenger Location":"( 124.0, 177.0)",
      "Ending Driver Balance":584.0,
      "Initial Driver Location":"( 2.0, 3.0)",
      "Initial Driver Balance":200.0

## How to Modify Code for Specific Use Cases:
### - To get a dropoff out of bounds:
      You must modify the main function in the uberAppSimulator.java class. Look for the line of code where the point randomDropoff is    created. There is a line of code commented out below it, uncomment it and you'll see the output for a ride with an out of bound dropoff.

### - To get one driver to take multiple trips:
      Again you must keep the dropoff to be non-random, but now specify it's location inside of the grid. Then manually update one of the drivers in the input file to have a location of 1,1. Update another driver to have location 1,4 and a passenger to have location 1,4. Update the dropoff to be at 1,1. Now the driver will end up at a location that another passenger is at, and will be the closest driver for that passenger. (make sure the passenger at 1,1 is listed last).

### - To see a ride cancelled because a passenger had not enough money.
      This should already be implemented in the code, but in case the random dropoff locations all happen to be very close (which is unlikely), you can also manually check for this by giving a passenger 0 money in the inputText file. You'll see the ride is cancelled and the next passenger begins their ride.
