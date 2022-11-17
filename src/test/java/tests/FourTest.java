package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import resources.Base;

public class FourTest extends Base{

	// need to make webDriver as public and global to take screenshots or else screenshot will not be captured and we will ge nullpointer exception
	public WebDriver driver;
	@Test
	public void testFour() throws IOException, InterruptedException {
		
		System.out.println("Sana has updated this code by downloading from Github.......");
		
		System.out.println("------------> Test Four testing method");
		 driver = initializeBrowser();
		driver.get("http://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		
		Assert.assertTrue(false);
		
	
	}
	
	@AfterMethod
	public void closingMethod() {
		
		driver.close();
	}
	
}
