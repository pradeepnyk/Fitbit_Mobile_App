package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import appiumSupport.DriverSetup;
import io.appium.java_client.MobileElement;

public class GetElement {

	public static WebDriver driver;

	public static MobileElement findElementbyXpath(String xpath, int timeout) {
		System.out.println("driver in find");
		System.out.println(DriverSetup.driver);
		MobileElement element;

		try {
			element = DriverSetup.getTLDriver().findElementByXPath(xpath);
		} catch (Exception e) {

			element = null;
		}
		return element;
	}

	public static boolean waitToVisible(String xpath, int timeLimitInSeconds) {

		MobileElement mobileElement;
		boolean isElementPresent = false;
		try {

//			mobileElement =  (MobileElement) DriverSetup.getTLDriver().findElementByXPath(xpath);
			WebDriverWait wait = new WebDriverWait(DriverSetup.getTLDriver(), timeLimitInSeconds);
			WebElement check = wait
					.until(ExpectedConditions.visibilityOf(DriverSetup.getTLDriver().findElementByXPath(xpath)));
			isElementPresent = check.isDisplayed();

		} catch (Exception e) {
			isElementPresent = false;
			System.out.println(e.getMessage());

		}
		return isElementPresent;
	}
}
