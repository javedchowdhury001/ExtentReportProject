package TestCase;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Base.BaseClass;


public class TestClass3 extends BaseClass{
	
	WebDriver driver;
	
	
@Test
public void facebookTest() {
	driver=initializeDriver();
	
	driver.get("https://www.facebook.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println("Navigate to Facebook");
	driver.findElement(By.xpath("//*[@id='email']")).sendKeys("jhon02@gmail.com");
	System.out.println(driver.getTitle());
	Assert.assertTrue(false);
	
	driver.close();
	driver.quit();



   }

}
