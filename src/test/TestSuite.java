package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DriverTester.class, MapGridTester.class, PassengerTester.class, RideManagerTester.class })
public class TestSuite {

}
