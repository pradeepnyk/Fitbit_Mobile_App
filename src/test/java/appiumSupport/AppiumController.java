package appiumSupport;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public static final String TESTOBJECT_API_KEY = System.getProperty("key");
	
	private static final Logger logger = LogManager.getLogger(AppiumController.class);

	public AppiumDriver<MobileElement> start(String deviceName, String url, OS env, String udid, boolean isLocal)
			throws MalformedURLException {
		this.executionOS = env;
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (isLocal) {
			switch (executionOS) {
			case ANDROID:
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
			default:
				System.out.println("No environment value provided");
				break;

			}
		} else {
			capabilities.setCapability("testobject_api_key", TESTOBJECT_API_KEY);
			capabilities.setCapability("app", "sauce-storage:Fitbit_v3.12.1_apkpure.com.apk");
			try {
				driver = new AndroidDriver(new URL(url), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
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
