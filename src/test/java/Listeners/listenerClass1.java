package Listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseClass;

public class listenerClass1 extends BaseClass implements ITestListener {
	ExtentReports extent=ExtentManager.ExtentReportGenerator();
	ExtentTest test;

	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		System.out.println( "<<<<<<<<<< "+result.getName() +" is Staring >>>>>>>>>>>>>");
	    test=extent.createTest(result.getMethod().getMethodName());
	    extentTest.set(test);
	    extentTest.get().log(Status.INFO ,"Test is Start");
	}


	public void onTestSuccess(ITestResult result) {
		System.out.println("<<<<<<<<<< "+ result.getName() +" is Passed >>>>>>>>>>>>>>>");
		extentTest.get().log(Status.PASS ,"Test is Passed");
	}
		
	
	public void onTestFailure(ITestResult result) {
		System.out.println("<<<<<<<<<< "+ result.getName() +" is Failed >>>>>>>>>>>>>>>");
		extentTest.get().log(Status.FAIL ,"Test is Failed");
		extentTest.get().fail(result.getThrowable());
		
		//ScreenShot Script
		WebDriver driver = null;
	    Object testObject=result.getInstance();
		Class<?> clazz=result.getTestClass().getRealClass();
		try {
			driver=(WebDriver)clazz.getDeclaredField("driver").get(testObject);
		    } catch (Exception e) {
					
			} 
				
				
		try {
	     	extentTest.get().addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver),result.getMethod().getMethodName());
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} 
				
	}
		

	public void onTestSkipped(ITestResult result) {
		System.out.println("<<<<<<<<<< "+ result.getName() +" is Skipped >>>>>>>>>>>>>>>");
		extentTest.get().log(Status.SKIP ,"Test is Skipped");
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}


	public void onStart(ITestContext context) {
	}

	
	public void onFinish(ITestContext context) {
		 extent.flush();
	}

}
