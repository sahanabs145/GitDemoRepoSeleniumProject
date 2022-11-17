package tests;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

//step 2: create a test class and try to login to tutorialsninja webpage
public class LoginTest extends Base {
	
	//part2:1:: create logger and in method openApplication, login andd logs 
	static Logger log;
	
	public WebDriver driver;
	
	//step 12: create before method
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		//instead of writing code to launch browser again we are connecting Base class
		driver = initializeBrowser();
		log.debug("Browser got launched");
		
		 //driver.get("http://tutorialsninja.com/demo/");	//this is hardcoding
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
		
	}
	
	//step 11: we need to connect the data provider to this test method to check login for all the credentials, so we need to pass the parameter to @Test annotation like @Test(dataprovider="methodname")
	//and pass the username, password and expected result as parameter to login method like login(String email,String password,String expectedResult)

	@Test(dataProvider="getLoginData")
	public void login(String email,String password,String expectedResult) throws IOException, InterruptedException {
		
		//step 4: Create an object for LandingPage from LoginTest and perform required operations
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login option");
		
		Thread.sleep(3000);
	
		//step 6: create objects for LoginPage to login to portal
		LoginPage loginPage = new LoginPage(driver);
		
		//loginPage.emailAddressTextField().sendKeys(prop.getProperty("email"));
		//now we have used dataprovider in step 10 so need to modify the code like

		loginPage.emailAddressTextField().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		
		//loginPage.passwordField().sendKeys("password");// hardcoding
		//loginPage.passwordField().sendKeys(prop.getProperty("password"));

		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");
		
		//step 8: cretae objects for AccountPage to confirm we have logged in or not
		AccountPage accountPage = new AccountPage(driver);
		
		//Assert.assertTrue(accountPage.editYourAccountInformation().isDisplayed());	after adding data provider for expected result we r modifying this code like
		String acutualResult = null;
		
		try {
			
			if(accountPage.editYourAccountInformation().isDisplayed()) {
			   log.debug("User got logged in");
			   acutualResult = "Success";
			}
			
		}catch(Exception e) {
			log.debug("User didn't log in");
			acutualResult = "Failure";
			
		}
		
		//Assert.assertEquals(actualResult, expectedResult);
		if(acutualResult.equals(expectedResult)) {
			
			log.info("Login Test got passed");
			
		}else {
			
			log.error("Login Test got failed");
		}

		 
	}
	
	//step 9: Write @AfterMethod to close the browser
	@AfterMethod
	public void closure() {
		
		driver.close();
		log.debug("Browser got closed");
		
	}
	
	//step 10: passing multiple sets of data from DataProvider annotated method to check login credentials
	@DataProvider
	public Object[][] getLoginData() {
		
		//Object[][] data = {{"sahana.selenium@gmail.com","Second@123","Success"},{"dummy@test.com","1234","Failure"}};
		Object[][] data = {{"sahana.selenium@gmail.com","Second@123","Success"}};
		
		return data;
		
	}
	

}

//after doing all these steps 
//create some dummy classes like Twotest, ThreeTest, FourTest simply for having multiple test classes to check
//then conver this project to testNG project by    right click on project ->TestNG -> covert to testng
	// if we want to run all the test classess at a time means we need testng.xml hence we convert this project to testng project
	
	//Integrate with Maven to run the Project from the command line
	//so for this google Search for Maven TestNG configuration and add the plugin for Maven Surefire plugin having configuration tags 
	/*
	   <build>
	  <pluginManagement>
	  <plugins>
	  <plugin>
	        <groupId>org.apache.maven.plugins</groupId>
	        <artifactId>maven-surefire-plugin</artifactId>
	        <version>3.0.0-M7</version>
	        <configuration>
	          <suiteXmlFiles>
	            <suiteXmlFile>testng.xml</suiteXmlFile>
	          </suiteXmlFiles>
	        </configuration>
	      </plugin>
	  </plugins>
	  </pluginManagement>
	  </build>
	  */
	//copy paste this code in pom.xml after dependencies
	/*
	part 2:
	log4j2 is used to get the logs for the tasks performing like opened page, logged in, navigated to accounts page, instead of sysout we use log4j to get detailed view
		
		step 1: Configuring log4j for logs, create log4j2.xml under resource package
		
		
		step 2:Add the tags to pom.xml file to specify where exactly log4j file is available in the project
	Search for 'Maven filtering' in google go to last
	
	
	Add the tags under <build> <resources><resource> tag
		step 3:Enable Parallel execution using TestNG
Adding sample automation code in all the Test Classes(TwoTest, ThreeTest) and Run them as a group to see whether they are running one after the other
Modify the testng.xml file as below:
Separate individual classes to separate tags
Add parallel=tests


	step 4: Taking screenshots for failing Tests
Create a listeners package under src/main/java
Create a Listeners class under it and make it implement ITestListener interface
Add TestNG library if unable to resolve the errors
Select 'Source' menu in Eclipse IDE < Override/Implement Methods and select all the check boxes of ITestListerner
Create reusable method for taking screenshots in Base class with two parameters  - View here
Extend the Listeners class with Base class 
Update the onTestFailure() method of Listeners class - View here
Add the Listeners tags in testng.xml file - View here
Make the driver of the Test Classes to global and public
Intentionally fail a test and run the testng.xml file

 
 
	step 5: Integrating ExtentReports to the framework
Create a package say 'utilities' under 'src/main/java'
Create a class under this package say 'ExtentReporter' 
Create a method say 'getExtentReport' with this code - View here
Make the getExtentReport method static 
Write extent report code into different Listeners methods ( onTestStart, onTestSuccess, onTestFailure and onFinish) - View here
Remove parallel execution from testng.xml file and Run
Make ExtentReports thread-safe, by adding this code to Listeners class
Add parallel execution to testng.xml file
Add this line to the Listeners class
ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
Add this line inside onTestStart 
extentTestThread.set(extentTest);
Replace existing line with this line inside onTestSuccess
extentTestThread.get().log(Status.PASS,"Test Passed");
Replace existing line with this link inside onTestFailure
extentTestThread.get().fail(result.getThrowable());
Adding the screenshot to the ExtentReports
Make the takeScreenshot() method return the destination file path - View here
Update the onTestFailure method
String screenshotFilePath = takeScreenshot(testMethodName,driver);
extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);

step 5: Applying Encapsulation in the framework
Make all the Page Objects as private 

step 6: Integrating Cucumber BDD into this Framework
Add the dependencies for Cucumber
Cucumber-Java 
Cucumber-testng
Verify whether the Cucumber Eclipse IDE plugin is installed, help - eclipse market place - cucumber (if cucumber is not working in ur system then download natural from marketplace)
Create a package say 'features' under 'src/test/java'
Create a feature file say 'Login.feature' under this package - View here
Create a package say 'stepdefinitions' under 'src/test/java'
Create a Class say 'Login.java' under this package
Auto-generate the Step definitions using TinyGerkhin Chrome Plugin
Move the automation code to respective step definitions methods - View here
Create a runner package 
Create a Runner class under it - View here
Extend AbstractTestNGCucumberTests
Generate a testng2.xml file and modify like this - View here
Run the testng2.xml file
Mention this testng2.xml file in maven surefire plugin of pom.xml and run using the Maven command (mvn test)


	*/


// git connection - 
//GitDemoRepoSeleniumProject - git repo name
// create local repository folder in ur system to copy code from folder to githud, in documents create new folder as
//C:\Users\sahan\Documents\SeleniumProjLocalRepo