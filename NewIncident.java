package week5.day1.assignment2;


import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class NewIncident extends ServiceNowBaseClass {
	@Test(invocationCount=3)
	public void newIncident() {
		//Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident",Keys.ENTER);
		driver.findElement(By.xpath("(//span[text()='Incident']/following::div[@class='sn-widget-list-title' and text()='All'])[1]")).click();
		//Switch to frame
		WebElement incidentFrame = driver.findElement(By.xpath("//iframe[@title='Incidents | ServiceNow']"));
		driver.switchTo().frame(incidentFrame);
		//Click New button
		driver.findElement(By.xpath("//button[text()='New']")).click();
		//Click icon in caller field
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		//Get window handles
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		List<String> allwindowHandlesList = new ArrayList<String>();
		allwindowHandlesList.addAll(windowHandles);
		String oldWindow = allwindowHandlesList.get(0);
		String newWindow = allwindowHandlesList.get(1);
		//Switch to new window
		driver.switchTo().window(newWindow);
		//Select a value for Caller
		driver.findElement(By.xpath("//tbody[@class='list2_body']//td//a[@role='button']")).click();
		//Switch to parent window
		driver.switchTo().window(oldWindow);
		//Switch to frame
		WebElement newincidentFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(newincidentFrame);
		//Enter value for short_description
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Entered description");
		//Read the incident number and save it a variable
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		//Click on Submit button
		driver.findElement(By.xpath("(//button[text()='Submit'])[1]")).click();
		//Search the same incident number in the next search screen
		driver.findElement(By.xpath("//span[@id='incident_hide_search']//input[@placeholder='Search']")).sendKeys(incidentNumber,Keys.ENTER);
		String createdIncident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		//Verify the incident is created successful and take snapshot 
		if(incidentNumber.equals(createdIncident)) {
			System.out.println("Incident created successfully");
		}
		else {
			System.out.println("Incident not created");
		}
	}
			
}


