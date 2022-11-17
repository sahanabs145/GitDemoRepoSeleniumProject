package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//same for all
@CucumberOptions(
		features="src/test/java/features",
		glue="stepdefinitions")
public class Runner extends AbstractTestNGCucumberTests {
	
	

}