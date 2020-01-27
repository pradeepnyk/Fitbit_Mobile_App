package testRunner;

import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sitture.ExtentReporter;

import appiumSupport.DriverSetup;
import appiumSupport.LockManager;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(monochrome = true, features = { "src/test/java/features" }, glue = { "stepDefinitions" },
		dryRun = false, plugin = { "pretty", "json:target/cucumber.json"
//        		"html:target/cucumber-report-html"
				, "pretty:target/cucumber-pretty.txt"
//        		, "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"
				, "testng:target/cucumber.xml", "usage:target/cucumber-usage.json", "rerun:target/rerun.txt"
				, "com.sitture.ExtentFormatter:target/extent-report/index.html", "html:target/html-report" })


@Listeners(testRunner.NGTestListener.class)
public class TestRunner extends DriverSetup {
	private TestNGCucumberRunner testNGCucumberRunner;

	private long testStartTime;
	private long suiteStartTime;

	@BeforeSuite()
	public void beforeSuite(ITestContext testContext) throws Exception {
		testStartTime = System.currentTimeMillis();
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}
	
	@BeforeTest
	@Parameters(value={ "deviceName", "url", "env", "udid", "isLocal" })
	public void setUpClass(String deviceName, String url, OS env, String udid, boolean isLocal) throws Exception {
		LockManager.createLock("mobile");
		setUp(deviceName, url, env, udid, isLocal);
		

	}

	@Test(groups = "cucumber", description = "Runs Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		// calculate suite execution time
		System.out.println("Suite Execution Time in Sec: "
				+ TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - suiteStartTime));

		// Preparing Extent report from the execution stats after suite execution is completed
		testNGCucumberRunner.finish();
		ExtentReporter.setConfig("src/test/resources/config.xml");
	}
}
