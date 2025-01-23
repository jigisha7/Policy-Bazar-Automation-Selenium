package pack1;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class travelInsurance {

public static WebDriver driver;
	
	@BeforeClass
	public void setDriver(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromedriver.exe");					
         driver = new ChromeDriver();	
        driver.manage().window().maximize();
        driver.get("http://www.policybazaar.com/");	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority= 1)
	public void travelLink(){
		//Locating travel insurance link
		WebElement headermenu =driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/ul/li[2]/a"));
        headermenu.click();
        WebElement travel=driver.findElement(By.cssSelector("body > div.policywarp > div.ruby-menu-demo-header > div > ul > li.ruby-menu-mega > div > div > div:nth-child(4) > ul > li:nth-child(1) > a > span"));
        travel.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Excel file to get input
		public String excelfile(int cellnum) throws IOException{
			File file= new File("C:\\Users\\Dell\\eclipse-workspace\\New_workspace\\Hackathon1\\ExcelFile\\formData.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wrkbk = new XSSFWorkbook(fis);
			XSSFSheet sheet = wrkbk.getSheetAt(0);
			XSSFRow row = sheet.getRow(1);
			
			String value = String.valueOf(row.getCell(cellnum));
						
			wrkbk.close();
			fis.close();
			return value;
		}
	
	@Test(priority= 2)
	public void tripForm() throws InterruptedException, IOException{
		//Student option
		 Thread.sleep(3000);
		WebElement student=driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/article/ul/li[3]/a"));
        student.click();
        
        //-----travel details-------
        //destination
        WebElement destination=driver.findElement(By.id("destination-autocomplete"));
        String country = excelfile(0);
        destination.sendKeys(country);
        driver.findElement(By.xpath("//*[@id='navigatorType']/body/ul/li/em")).click();
        
        //Age of Student 1 
        driver.findElement(By.id("memage1")).sendKeys(excelfile(1));
        
        //Age of Student 2
        driver.findElement(By.id("memage2")).sendKeys(excelfile(2));
        
        //Trip Start date
        driver.findElement(By.id("startdate")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[7]/div[2]/div[1]/table/tbody/tr[5]/td[6]")).click();
      //       //*[@id="navigatorType"]/body/div[7]/div[2]/div[1]/table/tbody/tr[1]/td[6]
        // driver.findElement(By.cssSelector("body > div.daterangepicker.ltr.auto-apply.single.opensright.show-calendar > div.drp-calendar.left.single > div.calendar-table > table > tbody > tr:nth-child(4) > td:nth-child(3)"));
        
        //Trip End date
        driver.findElement(By.id("enddate")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='navigatorType']/body/div[8]/div[2]/div[1]/table/tbody/tr[2]/td[4]")).click();
        
        //Submit
        driver.findElement(By.id("proceedStepOne")).click();
	}
	
	@Test(priority= 3)
	public void personalForm() throws InterruptedException, IOException{
		
		//----------Personal details-----------
		//Name
		driver.findElement(By.id("travelname")).sendKeys(excelfile(3));
		
		//Gender
		WebElement gender=driver.findElement(By.id("travelgender"));
		Select select =new Select(gender);
		select.selectByVisibleText(excelfile(4));
		
		//Mobile
		driver.findElement(By.id("travelmobile")).sendKeys(excelfile(5));
		
		//Email
		driver.findElement(By.id("travelemail")).sendKeys(excelfile(6));
		
		//Submit
		driver.findElement(By.xpath("//*[@id='topForm']/section/div[2]/div[2]/div[1]/div[2]/div/a[2]")).click();
		Thread.sleep(3000);
    }
	
	@Test(dependsOnMethods={"tripForm","personalForm"})
	public void searchResult() throws IOException{
		
		//--------Search result page------------
		//Sorting result from low to high price
        WebElement sort=driver.findElement(By.xpath("//select[@class='sort_select']"));
        Select sel =new Select(sort);
        sel.selectByVisibleText(excelfile(7));
	}
	
	@AfterClass
	public void close(){
		driver.close();
	}
}
