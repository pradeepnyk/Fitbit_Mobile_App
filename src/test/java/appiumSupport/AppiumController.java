package appiumSupport;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AppiumController {

	public static OS executionOS = OS.ANDROID;
	public static AppiumController instance = new AppiumController();
	public AppiumDriver driver;
	public enum OS {
		ANDROID, IOS
	}

	public AppiumDriver<MobileElement> start(String deviceName, String url, OS env, String udid) throws MalformedURLException {
		this.executionOS = env;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		switch (executionOS) {
		case ANDROID:
			System.out.println("Initialized android driver" + deviceName);
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/app/Android");
			File app = new File(appDir, "Fitbit_v3.12.1_apkpure.com.apk");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("udid", udid);
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("appPackage", "com.fitbit.FitbitMobile");
			capabilities.setCapability("appActivity", "com.fitbit.FirstActivity");
			driver = new AndroidDriver<AndroidElement>(new URL(url), capabilities);
			break;

		}
		return driver;
	}

	public void stop() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
