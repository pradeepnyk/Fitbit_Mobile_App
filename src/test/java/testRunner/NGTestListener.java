package testRunner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class NGTestListener implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("*****************************ITestListner on start ****************************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*****************************ITestListner on success ****************************************");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("*****************************ITestListner on fail ****************************************");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*****************************ITestListner on skip ****************************************");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("***************************** Testcase failure percentage "+result.getTestName()+": "+result.SUCCESS_PERCENTAGE_FAILURE +"****************************************");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("***************************** Testcase started "+context.getName()+" ****************************************");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("***************************** Testcase finished "+context.getName()+" ****************************************");
		
	}

}
