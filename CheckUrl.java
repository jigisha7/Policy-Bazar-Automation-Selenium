package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class CheckUrl {
	public static WebDriver driver;
	
	@BeforeClass
	public void setDriver()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Dell\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.policybazaar.com/");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test(priority=1)
	public void getUrlOneWay()
	{
		driver.findElement(By.xpath("/html/body/main/div[2]/section/div[4]/a/div[1]/i")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		driver.navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test(priority=2)
	public void getUrlTwoWay()
	{
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/div/div/div[3]/h3/a")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		driver.navigate().back();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test(priority=3)
	public void getUrlThreeWay()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5800)");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
		}
		driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[1]/div/ul[1]/li[2]/a")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		driver.navigate().back();
	}
	
	@AfterClass
	public void closeDriver()
	{
		driver.close();
	}
		
}
