package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base{

	public WebDriver driver;
	@Test
	public void testTwo() throws IOException, InterruptedException  {
		
		System.out.println("Gavi has updated this code by clicking on edit option");
		System.out.println("------------> Test Two testing method");
		 driver = initializeBrowser();
		driver.get("http://tutorialsninja.com/demo/");
		Thread.sleep(2000);
		driver.close();
	}
	
}
