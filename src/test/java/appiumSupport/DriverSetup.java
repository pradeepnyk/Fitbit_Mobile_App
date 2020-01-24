package appiumSupport;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;

import appiumSupport.AppiumController.OS;
import appiumSupport.LockManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObjects.AndroidContentSelection;
import pageObjects.ContentSelection;
import pageObjects.IOSContentSelection;
import utilities.AndroidAppElemnts;
import utilities.IOSAppElements;

public class DriverSetup {

	public static ContentSelection Controller;
	public static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<AppiumDriver<MobileElement>>();
	public static OS env = AppiumController.executionOS;

	@Parameters({ "deviceName", "url", "env" })
	public AppiumDriver<MobileElement> setUp(String deviceName, String url, OS envrm, String udid) throws Exception {
		AppiumDriver<MobileElement> driver;
		System.out.println("Android driver");
		try {
			System.out.println("Android driver2");
			LockManager.getLock("mobile").lock();
			System.out.println("Android driver1");
			setTLDriver(AppiumController.instance.start(deviceName, url, envrm, udid));
			driver = getTLDriver();
		} finally {
			LockManager.getLock("mobile").unlock();
		}
		System.out.println("Android driver after");
		System.out.println(driver);
		this.env = AppiumController.executionOS;

		switch (AppiumController.executionOS) {
		case ANDROID:
			System.out.println("Android instantiate here");
			new AndroidAppElemnts();

			Controller = new AndroidContentSelection();

			System.out.println("In android");
			break;
		case IOS:
			System.out.println("IOS instantiate here");
			new IOSAppElements();
			Controller = new IOSContentSelection();

			System.out.println("In ios");
			break;

		}
		return driver;
	}

	public static void setTLDriver(AppiumDriver<MobileElement> driver1) {

		driver.set(driver1);
		driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public static AppiumDriver<MobileElement> getTLDriver() {
		return driver.get();
	}

	public void tearDown() {

		System.out.println("cleanup**************************s");
//		if (driver != null) {
//			if (!driver.toString().contains("null"))
//				LibBrowser.driverStop(driver); // stop the driver if it exists
				AppiumController.instance.stop();
//		}
//		setTLDriver(null);

	}

}
