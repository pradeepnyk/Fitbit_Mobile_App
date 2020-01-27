package appiumSupport;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObjects.AndroidContentSelection;
import pageObjects.ContentSelection;
import pageObjects.IOSContentSelection;
import utilities.AndroidAppElemnts;
import utilities.IOSAppElements;


public class DriverSetup extends AppiumController{

	public static ContentSelection Controller;
	private static ThreadLocal<AppiumDriver<MobileElement>> tldriver = new ThreadLocal<>();
	public static OS env = AppiumController.executionOS;
	public AppiumDriver<MobileElement> driver;
	
	@Parameters({"deviceName","url","env", "udid", "isLocal"})
    public AppiumDriver<MobileElement> setUp(String deviceName,String url,OS envrm, String udid, boolean isLocal) throws Exception {
	    
		try{
	    	LockManager.getLock("mobile").lock();
            setTLDriver(AppiumController.instance.start(deviceName,url,envrm, udid, isLocal));
            driver =  getTLDriver();
		}
		finally {
    		LockManager.getLock("mobile").unlock();
		}
        DriverSetup.env = AppiumController.executionOS;
        
        switch (AppiumController.executionOS) {
            case ANDROID:
            	new AndroidAppElemnts();
            	Controller = new AndroidContentSelection();
            break;
            case IOS:
            	new IOSAppElements();
            	Controller = new IOSContentSelection();
            break;
        }
        return driver;
    }

	
public static void setTLDriver(AppiumDriver<MobileElement> driver1) {
    	
    	tldriver.set(driver1);
    	tldriver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    public static AppiumDriver<MobileElement> getTLDriver() {
        return tldriver.get();
    }
    public void tearDown() {
        AppiumController.instance.stop();
    }
	
}
