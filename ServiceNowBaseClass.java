package week5.day1.assignment2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowBaseClass {
public ChromeDriver driver;
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev41128.service-now.com/navpage.do");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement loginFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(loginFrame);
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("1wQsyajPQ0CQ");
		driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
