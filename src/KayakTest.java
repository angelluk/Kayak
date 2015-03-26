import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class KayakTest {
	
	public static  WebDriver driver;
	
	private String airOrigin;
	private String  airDestination;
	private int numberOfFlight;
 
	//parameters pass via  constructor of Test class KayakTest
	public KayakTest(String airOrigin, String airDestination, int numberOfFlight) {
		this.airOrigin = airOrigin;
		this.airDestination = airDestination;
		this.numberOfFlight = numberOfFlight;
	}
	
	   //Declare parameters 
		@Parameters(name = "{index}: Flights from {0} to {1} and checking flight number {2}")
		public static Iterable<Object[]> data1() {
			return Arrays.asList(new Object[][] { 
				{ "SFO", "LAX", 2 }, 
				{ "MCO", "JFK", 3 },
				{ "LAS", "SAN", 5 }

			});
		}
	@BeforeClass
	public static void setUp() throws Exception {
		
		driver = new WebDriverManager(driver).getWebDriverFor(WebDriverManager.Browser.CHROME);
	 	// set time for wait driver
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
 
	 /*
	  *  test method for user search flight actions
	  */
	 @Test
	 public void testUserSearchFligths() throws Exception{
					 
		 // create Search Flights Object 
		 SearchFlights currFlt = new  SearchFlights(driver); 
		 
		 // search  and return results of search object
		 ResultSearchFligts currResultsFlights = currFlt.searchForFligts(airOrigin,airDestination);
		 
		 // checking for all results title
		 assertTrue("The title of serach results for flights is not contains valid airports",currResultsFlights.isCorrectAllResults(airOrigin,airDestination));
			  
		 // checking for particular flight
		 assertTrue("The  result for particular flight is not contains valid airports",currResultsFlights.isFlightCorrect(numberOfFlight,airOrigin,airDestination));	
 	 }

	@AfterClass
     public static void tearDown()
     { 
          driver.quit(); 
     } 
	
}
