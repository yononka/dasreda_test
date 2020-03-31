package ui_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class Utilities extends BaseClass {
	
    public boolean isElementPresent(By by) {
  	  try {
  	    driver.findElement(by);
  	    return true;
  	  }
  	  catch (org.openqa.selenium.NoSuchElementException e) {
  	    return false;
  	  }
	}
    
	public boolean isDisabled(By by) {
		WebElement button = driver.findElement(by);
		String classes = button.getAttribute("class");
		return classes.contains("disabled");
		
	}
	    
	public int generateRandomNumber() {
		Random r = new Random();
		int Low = 100;
		int High = 900;
		int result = r.nextInt(High-Low) + Low;
		return result;
		
	}
	
	public String stringGenerator(int targetStringLength){
        int leftLimit = 1072; // 'А'
        int rightLimit = 1097; // 'Щ'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();  
        return generatedString;

	}

}
