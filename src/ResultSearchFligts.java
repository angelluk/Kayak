import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Represent  search result page for flights
 */
public class ResultSearchFligts {
  
	private  WebDriver driver;
   
	/*
     *  default constructor
     */
	public  ResultSearchFligts(WebDriver driver) {
        this.driver = driver;

        // Check that we're on the right page.
        if (!"KAYAK Search Results".equals(driver.getTitle())) {
           
            throw new IllegalStateException("This is not the search results for flights page");
        }
    }

	  /*
	   * SEARCH FLIGHTS RESULT OBJECT PAGE LOCATORS 
	   */
    
       By searchResultsTitle 		= By.cssSelector(".contentBlock.primaryText");
       By searchResults				= By.cssSelector(".inner"); 
       By flightAiports				= By.cssSelector(".airport");
         
       
       /*
        *  Check for correct of particular flight 
        */
       public boolean isFlightCorrect(Integer numFlight,String org, String den) throws Exception{
    	   
    	   boolean result = true; //critical mind  
    	   
    	   Thread.sleep(2000);
    	   
    	   // find all flights
    	   List<WebElement> allResults = driver.findElements(searchResults);				
    	   
    	   Integer resultListLentght = allResults.size();
    	   System.out.println("The flight results numbers is  - " + resultListLentght);		
    	   
    	   // checking for correct number of flights	
    	   if (numFlight>resultListLentght){																						
    		   System.out.println("The maximum flight  numbers is  - " + resultListLentght);
    		   numFlight = resultListLentght;
    	   }
    	   
    	   // get particular flight
    	   WebElement checkinFlight = allResults.get(numFlight);
    	   
    	   //System.out.println("The flight for checking is  - " + checkinFlight.getText());
    	   
    	   // check for all airports
    	   List<WebElement> allAirports  = checkinFlight.findElements(flightAiports);
    	   
    	   for (WebElement curAirport : allAirports) { 	// in this place can be realize complex logic for checking airports
    		   
    		  if (( curAirport.getText().contains(org)||(curAirport.getText().contains(den))))
    				  result = result & true;
   		   }
       	   return result;
         }
       
       /*
        *  Check for correct title of  results 
        */
       public boolean isCorrectAllResults(String org, String den) throws Exception{
    	   
    	   boolean result = false; //critical mind  
    	   
    	   String titleSearch = driver.findElement(searchResultsTitle).getText();
    	   
    	   if (titleSearch.contains(org) && (titleSearch.contains(den)))
    		   result = true;
    	   
    	   return result;
         }
}