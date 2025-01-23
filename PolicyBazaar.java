package pack1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class PolicyBazaar {

	static WebDriver driver;
	List <WebElement> insurancelist;
	WebElement health;
	int i,j,s;
	
	public PolicyBazaar() {}
	
	@BeforeClass
	public WebDriver createCromeDriver()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Dell\\Downloads\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.policybazaar.com/");
		return driver;
	}
	
	@Test(priority=1)
	public void LocateInHomePage() throws InterruptedException
	{
		System.out.println("On Home Page:");
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/a")).click();
		System.out.println("The sub-menus on Home Page:");
		LocateSubmenueOnHomePage();
		System.out.println("\n Link is :"+driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/h3/a")).getText());
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/h3/a")).click();
		health=driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[1]/nav/div/div[2]/div/div[2]/ul/li[3]/div/a/span"));
		System.out.println("The sub-menus on Health Insurance Page:");
		LocateSubmenueOnHealthPage();
		driver.navigate().to("https://www.policybazaar.com/");
	}
	
	public void LocateSubmenueOnHomePage()
	{
		insurancelist=driver.findElements(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/ul/li/a/span"));
		s=insurancelist.size();
		for(i=0;i<s;i++)
		{
			j=i+1;
			System.out.println(j+" : "+insurancelist.get(i).getText());
		}
	}
	

	public void LocateSubmenueOnHealthPage()
	{
		try
		{
			insurancelist=driver.findElements(By.xpath("//*[@id='navigatorType']/body/div[1]/nav/div/div[2]/div/div[2]/ul/li[3]/ul/li/a"));
			Actions action=new Actions(driver);
			action.moveToElement(health).perform();
			s=insurancelist.size();
			for(i=0;i<s;i++)
			{
				j=i+1;
				System.out.println(j+" : "+insurancelist.get(i).getText());
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Test(priority=2)
	public void LocateInFooter()
	{
		System.out.println("\n On Footer:");
		System.out.println("Link is :"+driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[1]/div/ul[3]/li[2]/a")).getText());
	}
	@Test(priority=3)
	public void LocateInAlsoBuy()
	{
		System.out.println("\n On Also Buy:");
		System.out.println("Link is :"+driver.findElement(By.xpath("/html/body/main/div[3]/section/div/a[3]")).getText());
	}
	
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("close");
		driver.close();
	}
	
}
