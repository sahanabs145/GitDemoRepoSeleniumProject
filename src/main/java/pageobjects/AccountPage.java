package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//step 7: create a class to check if we logged in by using the text present on screen an=fter logging in
public class AccountPage {

	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(linkText="Edit your account information")
    private WebElement editYourAccountInformation;	
	//part 2: 5::encapsulation make all the class page objects as private so that if anyone needs to access the objects means they need to access using methods only  
	
	
	public WebElement editYourAccountInformation() {
		return editYourAccountInformation;
	}
}
