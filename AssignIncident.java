package week5.day1.assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AssignIncident extends ServiceNowBaseClass {
	@Test(priority=-1)
	public void assignIncident() {
		// Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath(
				"(//span[text()='Incident']/following::div[@class='sn-widget-list-title' and text()='Open'])[1]"))
				.click();
		// Switch to frame
		WebElement mainFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(mainFrame);
		// Click on incident
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		// Click lookup icon in assignment group field
		driver.findElement(By.xpath("//button[@id='lookup.incident.assignment_group']")).click();
		// Get window handles
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		List<String> allwindowHandlesList = new ArrayList<String>();
		allwindowHandlesList.addAll(windowHandles);
		String oldWindow = allwindowHandlesList.get(0);
		String newWindow = allwindowHandlesList.get(1);
		// Switch to new window
		driver.switchTo().window(newWindow);
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys("Software", Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Software']")).click();
		// Switch to parent window
		driver.switchTo().window(oldWindow);
		driver.switchTo().frame(mainFrame);
		// Store incident number
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		// Click preview icon to open
		driver.findElement(By.xpath("//button[@id='viewr.incident.assignment_group']")).click();
		// Store assigned group
		String assignedGroupValue = driver.findElement(By.xpath("//*[@id=\"sys_readonly.sys_user_group.name\"]"))
				.getAttribute("value");
		// Click preview icon to close
		driver.findElement(By.xpath("//button[@id='viewr.incident.assignment_group']")).click();
		// Enter work notes
		driver.findElement(By.xpath("//textarea[@id='activity-stream-textarea']")).sendKeys("Enter text notes");
		// Click update
		driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();
		// Search for assigned incident
		driver.findElement(By.xpath("//span[@id='incident_hide_search']//input[@placeholder='Search']"))
				.sendKeys(incidentNumber, Keys.ENTER);

		String currentAssignedGroup = driver.findElement(By.xpath("//a[@aria-label='Open record: Software']"))
				.getText();

		if (assignedGroupValue.equals(currentAssignedGroup)) {
			System.out.println("Assigned group verified");
		} else {
			System.out.println("Incorrect assigned group");
		}
	}
}
