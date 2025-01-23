package pack1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class CheckFillDetailsForm
{
	
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
	public void getUrl()
	{
		driver.findElement(By.xpath("/html/body/main/div[2]/section/div[4]/a/div[1]/i")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test(priority=2)
	public void validateCarDetailsWithIcon() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id=\"before-tp\"]/div[2]/a")).click();
		driver.findElement(By.id("spn6")).click();
		driver.findElement(By.xpath("//*[@id=\"section3\"]/ul/li[1]/span")).click();
			Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='dvMake']/div/ul/div/li[2]/span")).click();
			Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"modelScroll\"]/li[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("Petrol")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"variantScroll\"]/li[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dvRegYear\"]/ul/div/li[5]")).click();
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void closeDriver()
	{
		driver.close();
	}
}
