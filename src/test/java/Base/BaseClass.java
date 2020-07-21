package Base;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	public static WebDriver driver;
	
	public WebDriver initializeDriver() {
		
		System.setProperty("webdriver.chrome.driver","C:\\AUTOMATION\\Selenium\\chromedriver.exe");
	    driver = new ChromeDriver();
		return driver;
	}
	
	public String getScreenshotPath(String name, WebDriver driver) throws Exception {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destpath=System.getProperty("user.dir")+"\\Screenshot\\"+name+".png";
		File file=new File(destpath);
		FileUtils.copyFile(source, file);
		return destpath;
	}
}
