package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	private static ChromeDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// Step1: Load ServiceNow application URL given above
//		Instance URL: https://dev113545.service-now.com/
//			Username: admin
//			Password: w6hnF2FRhwLC
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://dev113545.service-now.com/");

		// Maximizing browser window
		driver.manage().window().maximize();

		// Wait Management

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// switch into the frame

		WebElement loginFrame = driver
				.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(loginFrame);
		// Step2: Enter username as admin
		driver.findElement(By.id("user_name")).sendKeys("admin");

		// Step3: Enter password as w6hnF2FRhwLC
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");

		// Step4: Click Login
		driver.findElement(By.id("sysverb_login")).click();

		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		// Step5: Search “incident “ Filter Navigator
		WebElement searchElement = driver.findElement(By.xpath("//input[@id='filter']"));
		searchElement.sendKeys("Incident");
		Thread.sleep(2000);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		// switch to incident frame
		WebElement incidentFrame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(incidentFrame);

		// Step6: Click “All”
		driver.findElement(By.xpath("//span[@id='incident_breadcrumb']//b")).click();
		Thread.sleep(2000);
		// Step7: Click New button
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();

		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		// Step8: Select a value for Caller and Enter value for short_description
		WebElement newIncidentFrame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(newIncidentFrame);
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Thread.sleep(2000);
		// window handling
		Set<String> windowHandlingSet = driver.getWindowHandles();
		List<String> windowHandlingList = new ArrayList<String>(windowHandlingSet);

		driver.switchTo().window(windowHandlingList.get(1));
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(windowHandlingList.get(0));
		Thread.sleep(2000);
		driver.switchTo().frame(newIncidentFrame);
		driver.findElement(By.id("incident.short_description")).sendKeys("short discription of incident creation");

		// Step9: Read the incident number and save it a variable
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident Number:" + incidentNumber);

		// Step10: Click on Submit button
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();

		driver.switchTo().parentFrame();

		WebElement searchIncidentFrame = driver.findElement(By.id("gsft_main"));
		driver.switchTo().frame(searchIncidentFrame);

		// Step 11: Search the same incident number in the next search screen as below
		 driver.findElement(By.xpath("(//input[@placeholder='Search'])[@class='form-control']")).sendKeys(incidentNumber+Keys.ENTER);;
		Thread.sleep(2000);
		

		// Step12: Verify the incident is created successful and take snapshot of the
		// created incident.

		String incidentSuccess = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if (incidentSuccess.equals(incidentNumber)) {
			System.out.println("Incicent creation of " + incidentNumber + " is successful");
		} else {
			System.out.println("Incident creation failed");

		}

		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./snaps/Incident.png");
		FileUtils.copyFile(source, destination);
     
		driver.switchTo().parentFrame();
	}

}
