import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/*
 * Represent  main search page
 */
public class SearchFlights {
  
	private  WebDriver driver;
	private  final String pageURL = "http://www.kayak.com/flights";

   
	/*
     *  default constructor
     */
	public  SearchFlights(WebDriver driver) {
        
		this.driver = driver;
        
		// go to Page Object - Kayak Flights
        this.driver.get(this.pageURL);
        
        // Check that we're on the right page.
        if (!"KAYAK - Cheap Flights - Deals on Airline Tickets - Airfare - Compare Hundreds of Travel Sites".equals(driver.getTitle())) {
           
            throw new IllegalStateException("This is not KAYAK -  Flights page");
        }
    }

	  /*
	   * SEARCH OBJECT PAGE LOCATORS 
	   */

    	By airOrigin 		= By.id("origin");						// origin airport
        By airDestination 	= By.id("destination");					// destination airport
        By startDateWr 		= By.id("travel_dates-start-wrapper");	// start date wrapper
        By endDateWr		= By.id("travel_dates-end-wrapper");	// end date wrapper
        By startDate 		= By.id("travel_dates-start-display");	// start date
        By endDate 			= By.id("travel_dates-end-display");	// end date
        By btnSearch 		= By.id("fdimgbutton");					// search 
        By optNearO			= By.id("nearbyO");						// option for origin air if  exist
        By optNearD			= By.id("nearbyD");						// option for destination air if  exist
      
    
    /*
     *  Check for visible element
     */
    public boolean isElemVisible(By Locator) throws Exception {
       
    	boolean result;
    	
    	try {
            result = driver.findElement(Locator).isEnabled();
                     
        }
    	catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return false;
        }
    	
    	return result;
    }
    /*
     *  Search for flight 
     */
    public  ResultSearchFligts  searchForFligts(String origin, String destination) throws Exception {
       
    	// origin airport
    	driver.findElement(airOrigin).click();
    	driver.findElement(airOrigin).clear();
    	driver.findElement(airOrigin).sendKeys(origin);
    	
    	driver.findElement(By.cssSelector(".fdMainRight.fieldBlockCol")).click(); // for shut popup air 
    	// optional near origin
    	// for implements this feature need more detailed algorithm
    	//if (isElemVisible(optNearO) == true)
    	//	driver.findElement(optNearO).click();
    	    	
    	// destination airport
    	driver.findElement(airDestination).click();
    	driver.findElement(airDestination).clear();
    	driver.findElement(airDestination).sendKeys(destination);
    	
    	driver.findElement(By.cssSelector(".fdMainRight.fieldBlockCol")).click(); // for shut popup air 
    	// optional near destination
    	// for implements this feature need more detailed algorithm
    	//if (isElemVisible(optNearD) == true)
    	//	driver.findElement(optNearD).click();
    	
    	//start date
    	driver.findElement(startDateWr).click();
    	driver.findElement(startDate).sendKeys("04/23/2015");
    	
    	//end date
    	driver.findElement(endDateWr).click();
    	driver.findElement(endDate).sendKeys("04/25/2015");
    	
    	// start search
    	driver.findElement(btnSearch).click();
    	
    	// return ResultSearchFligts objects that represent result of search flights
    	return  ( new ResultSearchFligts(this.driver));
    }
}