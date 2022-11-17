 package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// step 3: create a pakage and Landing page to select login option in tutorialsninja page using page objet model technique
public class LandingPage {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		//initialize the objects in this class
		PageFactory.initElements(driver, this);

	}

	//setting the path of the webpage elements
	@FindBy(xpath="//a[text()='My Account']")
	private WebElement myAccountDropdown;					// this are called page objects
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	//creating methods to perform the actions to click the button
	public WebElement myAccountDropdown() {
		return myAccountDropdown;
	}
	
	public WebElement loginOption() {
		return loginOption;
	}
}
