package Listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BaseClass;

public class ExtentManager extends BaseClass {
	public static ExtentReports extent;

	public static ExtentReports ExtentReportGenerator() {

		String path = System.getProperty("user.dir") + "\\Reports\\ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("User Name", "Javed H Chowdhury");
		extent.setSystemInfo("Department", "QA Team");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Java Version", "1.8.0_181");
		extent.setSystemInfo("Maven Version", "3.6.3");

		return extent;

	}

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try {
			screenshotPath=System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName;
			FileUtils.copyFile(scrFile, new File(screenshotPath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}