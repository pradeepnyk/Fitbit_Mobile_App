package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.google.common.io.Files;

import appiumSupport.DriverSetup;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import utilities.GetElement;
import utilities.TouchActions;

public class LoginStepDef extends DriverSetup {

    public TouchActions action = new utilities.TouchActions(getTLDriver());
    private int timeout = 60;
    
    private static final Logger logger = LogManager.getLogger(LoginStepDef.class);
    
    @After
    public void endOfExecution(Scenario scenario) throws IOException {
        logger.info(scenario.getName()
                + " $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$======= TC is completed ============$$$$$$$$$$$$$$$$$$$$$$$");
        if (scenario.isFailed()) {
            try {
                logger.error(scenario.getName() + " is Failed");
                File imageFile = ((TakesScreenshot) getTLDriver()).getScreenshotAs(OutputType.FILE);
                String failureImageFileName = scenario.getName()
                        + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
                File failureImageFile = new File(
                        System.getProperty("user.dir") + "/target/FailedScenarioscreenshots/" + failureImageFileName);
                failureImageFile.getParentFile().mkdir();
                failureImageFile.createNewFile();
                Files.copy(imageFile, failureImageFile);
                final byte[] screenshot = ((TakesScreenshot) getTLDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in
            } catch (WebDriverException e) {
                e.printStackTrace();
            }
        } else {
            try {
                logger.info(scenario.getName() + " is passed");
                scenario.embed(((TakesScreenshot) getTLDriver()).getScreenshotAs(OutputType.BYTES), "image/png");// ss
                                                                                                                    // of
                                                                                                                    // each
                                                                                                                    // pass
                                                                                                                    // scenario
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        tearDown();
    }

    @Given("^User is on landing page$")
    public void user_is_on_landing_page() {
        boolean isVisible;
        isVisible = GetElement.waitToVisible(Controller.get_xpath("loginButton_xpath"), timeout);
        assert (isVisible != false);
        logger.info("**************** User can see login button on landing page ***************************");
    }

    @When("^user clicks on login button$")
    public void user_clicks_login_button() throws Exception {
        WebElement login_button;
        login_button = GetElement.findElementbyXpath(Controller.get_xpath("loginButton_xpath"), timeout);
        Assert.assertTrue(this.action.tap(login_button));
        logger.info("**************** User clicked on Login button successfully ***************************");
    }

    @And("^user enters credentials and hit submit button$")
    public void user_enters_credentials_and_hit_submit(DataTable programParameters) throws Throwable {
        List<Map<String, String>> data = programParameters.asMaps(String.class, String.class);
        List<String> keys = new ArrayList<String>(data.get(0).keySet());
        String usernameData = null;
        String passwordData = null;
        WebElement usernameField = GetElement.findElementbyXpath(Controller.get_xpath("username_xpath"), timeout);
        WebElement pswrdField;
        for (int i = 0; i < keys.size(); i++) {
            String field = keys.get(i);
            switch (keys.get(i)) {
            case "Username":
                usernameData = data.get(0).get(field);
                usernameField.sendKeys(usernameData);
                break;
            case "Password":
                pswrdField = GetElement.findElementbyXpath(Controller.get_xpath("password_xpath"), timeout);
                passwordData = data.get(0).get(field);
                pswrdField.sendKeys(passwordData);
                break;

            }

        }
        WebElement loginBtn = GetElement.findElementbyXpath(Controller.get_xpath("login_submit_xpath"), timeout);
        Assert.assertTrue(this.action.tap(loginBtn));
        logger.info("**************** User submiited login with credentials successfully ***************************");
    }


    @Then("^verify user logged in successfully$")
    public void login_successful() {
        getTLDriver().manage().timeouts().implicitlyWait(40l, TimeUnit.SECONDS);
        try {
            boolean permissionScreen = GetElement.waitToVisible(Controller.get_xpath("location_permission_screen"),
                    100);
            if (permissionScreen) {
                MobileElement closeBtn = GetElement.findElementbyXpath(Controller.get_xpath("close_button"), 100);
                this.action.tap(closeBtn);
            }
        } catch (Exception e) {

        }
        
        boolean homepageCheck = GetElement.waitToVisible(Controller.get_xpath("homepage_check"), 100);
        Assert.assertTrue(homepageCheck);
        logger.info("**************** User is on landing page ***************************");
    }


}