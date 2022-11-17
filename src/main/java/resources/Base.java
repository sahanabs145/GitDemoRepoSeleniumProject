package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


//step 1: creating base class and launching web browser by adding data.properties file
public class Base {
	
	WebDriver driver;
	
	public Properties prop;
	
	public WebDriver initializeBrowser() throws IOException {
		
		 prop = new Properties();
		String propertiesPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(propertiesPath);
		prop.load(fis);
		
		
		// getting the browsername from data.properties file 
		//here we are avoiding the hardcoding in the same file hence creating new properties file for browser name
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("ie")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	//part 2: 4::Create reusable method for taking screenshots in Base class with two parameters
	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		File SourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+"testName.png";
		FileUtils.copyFile(SourceFile, new File(destinationFilePath));
		
		//part 2: 4.3::  to display screenshot in extent report
		return destinationFilePath;
		//above method and path are same for all and user.dir is our project location
	}

}