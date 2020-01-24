package stepDefinitions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import appiumSupport.AppiumController.OS;
import appiumSupport.DriverSetup;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import utilities.GetElement;
import utilities.TouchActions;

public class loginStepDef extends DriverSetup {

	public TouchActions action;
	private int timeout = 60;

	@Before
	public void setUp() throws Exception {
		System.out.println("Cucumber Test  Before");

		this.action = new utilities.TouchActions(DriverSetup.getTLDriver());

	}
	
	@After
	public void endOfExecution() {
		System.out.println("===END OF EXECUTION OF TEST SCENARIO");
		tearDown();
	}

	@Given("^User is on landing page$")
	public void user_is_on_landing_page() {
		boolean isVisible;
		System.out.println("in given" + Controller.get_xpath("loginButton_xpath"));
		isVisible = GetElement.waitToVisible(Controller.get_xpath("loginButton_xpath"), timeout);

		assert (isVisible != false);

	}

	@When("^user clicks on login button$")
	public void user_clicks_login_button() {
		System.out.println("in user_clicks_login_button");
		WebElement login_button;
		login_button = GetElement.findElementbyXpath(Controller.get_xpath("loginButton_xpath"), timeout);
		this.action.tap(login_button);
	}

	@And("^user enters credentials and hit submit button as \"(.*)\" and \"(.*)\"$")
	public void user_enters_correct_credentials(String userName, String password) throws Throwable {
		
		
		System.out.println("Username and password are: " + userName + ", " + password);
		WebElement usernameField = GetElement.findElementbyXpath(Controller.get_xpath("username_xpath"), timeout);
		WebElement pswrdField = GetElement.findElementbyXpath(Controller.get_xpath("password_xpath"), timeout);
		usernameField.sendKeys(userName);
		Thread.sleep(1000);
		pswrdField.sendKeys(password);
		WebElement loginBtn = GetElement.findElementbyXpath(Controller.get_xpath("login_submit_xpath"), timeout);
		this.action.tap(loginBtn);
		Thread.sleep(4000);
	}

	@Then("^verify user is on homepage$")
	public void user_on_homepage() {
		
		try {
			boolean permissionScreen = GetElement.waitToVisible(Controller.get_xpath("location_permission_screen"), 100);
			if(permissionScreen) {
				MobileElement closeBtn = GetElement.findElementbyXpath(Controller.get_xpath("close_button"), 100);
				this.action.tap(closeBtn);
			}
		}catch(Exception e) {
			
		}
		
		GetElement.waitToVisible(Controller.get_xpath("homepage_check"), 100);
				
//		WebElement home = GetElement.findElementbyXpath(Controller.get_xpath("homepage_check"), 100);
//		Assert.assertTrue(home.isDisplayed());
		System.out.println("User is on homepage");
	}
	

	@Then("^verify user gets error message$")
	public void user_gets_error_message() {
		System.out.println("Error message");
		boolean error_check = GetElement.waitToVisible(Controller.get_xpath("error_msg"), 100);
		Assert.assertTrue(error_check);
		MobileElement ok_btn = GetElement.findElementbyXpath(Controller.get_xpath("error_message_ok_btn"), 100);
		this.action.tap(ok_btn);
	}

}
