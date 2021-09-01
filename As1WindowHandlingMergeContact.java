package week4.day1.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class As1WindowHandlingMergeContact {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
				//2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		
		//3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		String hometitle = driver.getTitle();
		System.out.println(hometitle+" Page loaded");
		
		//4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//check if page is loaded
		
		String title = "My Home | opentaps CRM";
		String hometitle1 = driver.getTitle();

		if (!title.equals(hometitle1)) {

			System.out.println("Test leaf home page is not  loaded");
		} else {

			System.out.println("Test leaf home page '"+hometitle1 +"' is loaded.Processing futher steps.");
		}
		
		
		//5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		
		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		
		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("//div[@class='subSectionBlock']//img")).click();
		
		Thread.sleep(2000);
		//Window Handling
		Set<String> windowHandlingSet=driver.getWindowHandles();
		List<String> windowHandlingList=new ArrayList<String>(windowHandlingSet);
		
		driver.switchTo().window(windowHandlingList.get(1));
		//System.out.println(driver.getTitle());
		
		//8. Click on First Resulting Contact
		
		driver.findElement(By.className("linktext")).click();
		driver.switchTo().window(windowHandlingList.get(0));
		
		
		//9.Click on Widget of To Contact
		
		driver.findElement(By.xpath("(//input[@id='partyIdTo'])/following::img[1]")).click();
		
		Set<String> windowHandlingSet1=driver.getWindowHandles();
		List<String> windowHandlingList1=new ArrayList<String>(windowHandlingSet1);
		
		driver.switchTo().window(windowHandlingList1.get(1));
		//System.out.println(driver.getTitle());
		
		//10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[text()='Contact ID'])/following::table[2]//a")).click();
		driver.switchTo().window(windowHandlingList1.get(0));
		Thread.sleep(2000);
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		//12. Accept the Alert
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		//13. Verify the title of the page
		System.out.println("The title of the current page is: "+driver.getTitle());
		String titleMerge = "View Contact | opentaps CRM";
		
		if (titleMerge.equals(driver.getTitle())) {

			System.out.println("Contact Merged successfully");
		} else {

			System.out.println("Merge contact is unsuccessful");
		}
		driver.quit();
		
		 
	}

}
