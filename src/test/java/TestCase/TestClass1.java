package TestCase;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import Base.BaseClass;


public class TestClass1 extends BaseClass {
	
	WebDriver driver;
	
	
@Test
public void clickAcademyTest() {
	driver=initializeDriver();
	
	driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	System.out.println("Navigate to Rahulshettyacademy");
	WebElement signupLink = driver.findElement(By.id("name"));
	signupLink.click();
	signupLink.sendKeys("Hello");
	
	driver.close();
	driver.quit();

   }
}