package utilities;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TouchActions {

	boolean success = true;
	IOSDriver<IOSElement> iosdriver;
	AppiumDriver driver;
	TouchAction action;

	public TouchActions(AppiumDriver driver) {
		this.driver = driver;

		this.action = new TouchAction(driver);
	}

	public void swipe(WebElement webelement) {

		Point bannerPoint = webelement.getLocation();

		// Get size of device screen
		Dimension screenSize = driver.manage().window().getSize();
		// Get start and end coordinates for horizontal swipe
		int startX = Math.toIntExact(Math.round(screenSize.getWidth() * 0.8));

		int endX = 0;

		this.action.press(PointOption.point(startX, bannerPoint.getY() + 500))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
				.moveTo(PointOption.point(endX, bannerPoint.getY() + 500)).release().perform();

	}

	public boolean tap(WebElement webelement) {
		this.action.tap(tapOptions().withElement(element(webelement)));
		this.action.perform();
		return true;
	}

}
