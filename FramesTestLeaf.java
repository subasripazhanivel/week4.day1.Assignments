package week4.day1.assignments;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesTestLeaf {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1.Take the the screenshot of the click me button of first frame
		WebElement frame1 = driver.findElement(By.xpath("//div[@id='wrapframe']/iframe"));
		driver.switchTo().frame(frame1);
		
		WebElement button = driver.findElement(By.xpath("//button[@id='Click']"));
		button.click();
		File source=button.getScreenshotAs(OutputType.FILE);
		File destination=new File("./snaps/Button.png");
		FileUtils.copyFile(source, destination);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		
		/*2.Find the number of frames
  - find the Elements by tagname - iframe
  - Store it in a list
  - Get the size of the list. (This gives the count of the frames visible to the main page)*/
		
		List<WebElement> framesList = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of Frames in the page : " + framesList.size());
		
		
	}

}
