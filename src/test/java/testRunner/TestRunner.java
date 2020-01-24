package testRunner;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appiumSupport.AppiumController.OS;
import appiumSupport.LockManager;
import appiumSupport.DriverSetup;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
        monochrome = true,
        features = {"src/test/java/features"},
        glue = {"stepDefinitions"},
        tags = "@PositiveLogin",
        dryRun = false,
        plugin = { "pretty",
        		"json:target/cucumber.json"
//        		, "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"
        		, "testng:target/cucumber.xml"
        		, "usage:target/cucumber-usage.json"
        		, "rerun:target/rerun.txt"
//        		, "com.cucumber.listener.ExtentCucumberFormatter:target/WearablesReport.html"
        		, "com.sitture.ExtentFormatter:target/extent-report/index.html"
        		, "html:target/html-report"})

public class TestRunner extends  DriverSetup{
	private TestNGCucumberRunner testNGCucumberRunner;
	
	private long testStartTime;
	private long suiteStartTime;
	
	@BeforeSuite()
	public void beforeSuite(ITestContext testContext) throws Exception{
		testStartTime = System.currentTimeMillis();
	}
	 
    @BeforeClass(alwaysRun = true)
    @Parameters({"deviceName","url","env", "udid"})
    public void setUpClass(String deviceName,String url,OS env, String udid) throws Exception {
    	System.out.println("Cucumber Test Class Before class");
    	System.out.println("Environment variable: "+env);
    	LockManager.createLock("mobile");
    	setUp(deviceName,url,env, udid);
        
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        
    }
    
    @Test(groups = "cucumber", description = "Runs Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        System.out.println("Cucumber Test Class Inside Test");
        System.out.println(cucumberFeature.getCucumberFeature());
        
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }
 
    @DataProvider
    public Object[][] features() {
        System.out.println("Data Provider test Class");
        return testNGCucumberRunner.provideFeatures();
    }
    
    @AfterSuite
	public void afterSuite() throws Exception{
		// calculate suite execution time
		System.out.println("Suite Execution Time in Sec: "+TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - suiteStartTime));
	}
}
