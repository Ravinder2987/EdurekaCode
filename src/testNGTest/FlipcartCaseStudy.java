package testNGTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FlipcartCaseStudy {

	static WebDriver driver;
	String url = "https://www.flipkart.com/";


	@BeforeSuite
	public void setUp() {
		String currDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", currDir+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.flipkart.com");	
	}

	@AfterSuite (enabled=true)
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1, enabled=true)
	public void searchSeleniumBook() {

		//cancel, if any pop-up window is displayed

		if(driver.findElement(By.xpath("//div[@class='_32LSmx']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		}
		else {
			System.out.println("login pop up was not displayed");
		}

		//search for selenium book 

		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("selenium book");
		driver.findElement(By.xpath("//button[@type='submit']")).sendKeys(Keys.ENTER);

		//select a selenium book to purchase

		driver.findElement(By.xpath("//a[@title='Selenium Testing Tools Cookbook']")).click();

	}

	@Test(priority=2, enabled=true)
	public void deliveryInfo() {

		//check window handle first
		ArrayList<String> windows = new ArrayList<> (driver.getWindowHandles());
		String windowHandleName = windows.get(1);

		driver.switchTo().window(windowHandleName);

		//enter delivery pincode 
		driver.findElement(By.xpath("//input[@id='pincodeInputId']")).sendKeys("141004");
		driver.findElement(By.xpath("//span[@class='_2aK_gu']")).click();

		//print the duration needed
		driver.findElement(By.xpath("//span[@class='_3yGtFA']")).click();
		WebElement duration = driver.findElement(By.xpath("//span[@class='kUhIIX']"));
		String timeNeeded = duration.getText();

		//Print in the console, how much time is needed for delivery 
		System.out.println(timeNeeded);
	}

	@Test(dependsOnMethods= {"deliveryInfo"}, priority=3, enabled=true)
	public void checkout() {

		WebDriverWait wait = new WebDriverWait(driver,20);

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		//click on "ADD TO CART" button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']"))).click();

	}

	@Test (dependsOnMethods= {"checkout"},priority = 4, enabled = true)
	public void placeOrder() {

		WebDriverWait w = new WebDriverWait(driver,20);

		//click on "PLACE ORDER" button 
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2AkmmA iwYpF9 _7UHT_c']"))).click();

		//give a random email id to place order
		WebElement emailBox=driver.findElement(By.xpath("//input[@class='_2zrpKA _14H79F']"));
		emailBox.sendKeys("randomemail@address.com");
		emailBox.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//input[@class='_2zrpKA _14H79F']")).sendKeys("0000000000");

		//click on continue button
		driver.findElement(By.xpath("//button[@class='_2AkmmA _1poQZq _7UHT_c']")).click();

	}

	@Test (priority=5,enabled = true)
	public static void takeScreenShot() throws IOException {
		//capture the screenschot as png file
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\Users\\Vishal\\eclipse-workspace\\Edureka\\src\\testNGTest.png"));
		
		//capture the error message 
		String errortext=driver.findElement(By.xpath("//span[contains(text(),'Please enter a valid Mobile number')]")).getText();
		System.out.println(errortext);
	}
	
}	
