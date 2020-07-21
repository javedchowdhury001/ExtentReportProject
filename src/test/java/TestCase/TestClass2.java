package TestCase;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Base.BaseClass;


public class TestClass2 extends BaseClass {

	WebDriver driver;
	
	
@Test
public void googleTest() {
	driver=initializeDriver();

	driver.get("https://www.google.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println("Navigate to Google");
	WebElement TextBox =driver.findElement(By.xpath("//input[@name='q']"));		
	TextBox.sendKeys("BMW");
	//Assert.assertTrue(false);
	driver.close();
	driver.quit();

   }

}
