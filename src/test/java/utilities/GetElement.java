package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appiumSupport.DriverSetup;
import io.appium.java_client.MobileElement;

public class GetElement extends DriverSetup{

	public WebDriver driver;
	

	public static MobileElement findElementbyXpath(String xpath, int timeout) {
		
		MobileElement element;

		try {
			element = getTLDriver().findElementByXPath(xpath);
		} catch (Exception e) {

			element = null;
		}
		return element;
	}

	public static boolean waitToVisible(String xpath, int timeLimitInSeconds) {

		MobileElement mobileElement;
		boolean isElementPresent = false;
		try {

			WebDriverWait wait = new WebDriverWait(getTLDriver(), timeLimitInSeconds);
			WebElement check = wait
					.until(ExpectedConditions.visibilityOf(getTLDriver().findElementByXPath(xpath)));
			isElementPresent = check.isDisplayed();

		} catch (Exception e) {
			isElementPresent = false;
			System.out.println(e.getMessage());

		}
		return isElementPresent;
	}
}
