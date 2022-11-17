package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

//part 2:  4:: taking screenshot
public class Listeners extends Base implements ITestListener {
	
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	
	//part 2: 4.2 :: to make extentreport as thread safe, same for all
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	//to create all this methods click on source -> implement/override methods -> slect checkbox for ITestListeners and click ok and remove rext inside methods
		public void onTestStart(ITestResult result) {
		
			String testName = result.getName();
		extentTest = extentReport.createTest(result.getName()+" ..");
		
		//part 2: 4.2 :: to make extentreport as thread safe, same for all
		extentTestThread.set(extentTest);
	}


	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		//extentTest.log(Status.PASS,testName+"Test Passed..........");   replace this line with threadsafe mode
		
		extentTestThread.get().log(Status.PASS,"Test Passed");
	}

	
	public void onTestFailure(ITestResult result) {
		
		//extentTest.fail(result.getThrowable());	replace this line with threadsafe mode
		
		extentTestThread.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		
		// we will get login() method of LoginTest class here
		String testMethodName = result.getName();
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			//takeScreenshot(testMethodName,driver);
		
			
			//part 2: 4.4:: for displaying screenshot in extent report in browser
			String screenshotFilePath = takeScreenshot(testMethodName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public void onTestSkipped(ITestResult result) {
		
		
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
		
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
		
		
		
	}

	
	public void onStart(ITestContext context) {
		
		
		
	}

	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
	}

}


//GitDemoRepoSeleniumProject - git repo name