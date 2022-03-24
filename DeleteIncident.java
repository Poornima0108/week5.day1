package week5.day1.assignment2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DeleteIncident extends ServiceNowBaseClass {
	@Test(invocationCount=2 , invocationTimeOut=10000)
	public void deleteIncident() {
		// Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By
				.xpath("(//span[text()='Incident']/following::div[@class='sn-widget-list-title' and text()='All'])[1]"))
				.click();
		// Switch to frame
		WebElement mainFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(mainFrame);
		// Click on incident
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		// Store incident number
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		// Click delete
		driver.findElement(By.xpath("//button[@id='sysverb_delete']")).click();
		driver.findElement(By.xpath("//button[@id='ok_button']")).click();
		// Search for deleted incident
		driver.findElement(By.xpath("//span[@id='incident_hide_search']//input[@placeholder='Search']"))
				.sendKeys(incidentNumber, Keys.ENTER);
		WebElement noRecordElement = driver
				.findElement(By.xpath("//*[@id=\"incident_table\"]//td[text()='No records to display']"));
		if (noRecordElement.isDisplayed() == true) {
			System.out.println("Incident deleted successfully");
		} else {
			System.out.println("Incident not deleted");
		}
	}
}
