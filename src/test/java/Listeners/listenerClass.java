package Listeners;

import java.io.IOException;
import java.util.Arrays;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class listenerClass implements ITestListener {
	ExtentReports extent = ExtentManager.ExtentReportGenerator();
	ExtentTest test;

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		System.out.println("<<<<<<<<<< " + result.getName() + " is Staring >>>>>>>>>>>>>");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		extentTest.get().log(Status.INFO, "Test is Start");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("<<<<<<<<<< " + result.getName() + " is Passed >>>>>>>>>>>>>>>");

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().pass(m);

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("<<<<<<<<<<" + result.getName() + " is Failed >>>>>>>>>>>>>>>");
		extentTest.get().log(Status.FAIL, "Test is Failed");

		// Screenshot Capture
		System.out.println("Capture Screenshot!!!!!!!!!!");
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		try {

			ExtentManager.captureScreenshot();
			extentTest.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotPath).build());
		} catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("<<<<<<<<<< " + result.getName() + " is Skipped >>>>>>>>>>>>>>>");
		extentTest.get().log(Status.SKIP, "Test is Skipped");
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
