package week5.day1.assignments;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

public class CreateLead extends BaseClass {
	@Test
	public void runTC001() {
			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TestLeaf");
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Hari");
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys("R");
			driver.findElement(By.name("submitButton")).click();

	}
	
	@Test(priority=-1)
	public void runTC002() {
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys("TestLeaf");
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys("Joe");
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys("L");
		driver.findElement(By.name("submitButton")).click();

}

}
