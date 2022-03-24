package week5.day1.assignment2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class UpdateIncident extends ServiceNowBaseClass {

	@Test(dependsOnMethods = "week5.day1.assignment2.AssignIncident.assignIncident", alwaysRun = true)
	public void updateIncident() {
		// Search “incident “ Filter Navigator
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By
				.xpath("(//span[text()='Incident']/following::div[@class='sn-widget-list-title' and text()='Open'])[1]"))
				.click();
		// Switch to frame
		WebElement mainFrame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(mainFrame);
		// Click on incident
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		// Store incident number
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		// Update state
		WebElement stateDropdownElement = driver.findElement(By.xpath("//select[@id='incident.state']"));
		Select stateDD = new Select(stateDropdownElement);
		stateDD.selectByVisibleText("In Progress");
		WebElement stateOption = stateDD.getFirstSelectedOption();
		String updatedState = stateOption.getText();
		// Update urgency
		WebElement urgencyDropdownElement = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select urgencyDD = new Select(urgencyDropdownElement);
		stateDD.selectByIndex(1);
		WebElement urgencyOption = urgencyDD.getFirstSelectedOption();
		String updatedUrgency = urgencyOption.getText();
		// Click update
		driver.findElement(By.xpath("//button[@id='sysverb_update']")).click();
		// Search for updated incident
		driver.findElement(By.xpath("//span[@id='incident_hide_search']//input[@placeholder='Search']"))
				.sendKeys(incidentNumber, Keys.ENTER);
		// Open incident
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		// Retrieve state and urgency of opened incident
		WebElement stateDropdownElement1 = driver.findElement(By.xpath("//select[@id='incident.state']"));
		Select stateDD1 = new Select(stateDropdownElement1);
		String currentState = stateDD1.getFirstSelectedOption().getText();
		WebElement urgencyDropdownElement1 = driver.findElement(By.xpath("//select[@id='incident.urgency']"));
		Select urgencyDD1 = new Select(urgencyDropdownElement1);
		String currentUrgency = urgencyDD1.getFirstSelectedOption().getText();
		if (updatedState.equals(currentState)) {
			System.out.println("State verified");
		} else {
			System.out.println("State not updated");
		}
		if (updatedUrgency.equals(currentUrgency)) {
			System.out.println("Urgency verified");
		} else {
			System.out.println("Urgency not updated");
		}
	}

}
