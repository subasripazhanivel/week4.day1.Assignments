package week4.day1.assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramsePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 1.Enter text in topic

		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Frame1");

		

		// switch to inner frame
		WebElement frame3 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(frame3);

		// select check box
		driver.findElement(By.id("a")).click();
		driver.switchTo().defaultContent();// switch to main window

		// switch to frame2

		WebElement frame2 = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(frame2);
		WebElement animalDropDown = driver.findElement(By.id("animals"));
		Select animalDrpDwn = new Select(animalDropDown);
		animalDrpDwn.selectByIndex(2);
		driver.switchTo().defaultContent();

	}

}
