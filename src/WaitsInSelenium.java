import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsInSelenium {

	WebDriver driver;

	//CASE STUDY 2 Module 3 

	public void openBrowser(String browserType, String url) {
		String currDir = System.getProperty("user.dir");		//Dynamic way of accessing browser

		if(browserType.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", currDir+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", currDir+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserType.equals("ie")) {
			System.setProperty("webdriver.ie.driver", currDir+"\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		// Setting Selenium Page Timeout after launching browser
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


		if(url!="") 
			driver.get(url);
		else 
			driver.get("https://edureka.co/");

	}

	public void browserCapabilities() {


		//Maximize the browser
		driver.manage().window().maximize();

		//implicit wait on the browser instance
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);	
		
		
		//set selenium script timeout and execution of asynchronys script
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");	
		
	}


	public void selSearch() {			//accessing search bar

		driver.findElement(By.id("search-inp3")).click();
		WebElement searchBox1 = driver.findElement(By.id("search-inp-overlay-new"));

		//Enter Selenium in search bar and press Enter 
		searchBox1.sendKeys("Selenium");
		searchBox1.sendKeys(Keys.ENTER);
	}

	public void selCourse() {	

		//accessing selenium course
		WebDriverWait w = new WebDriverWait(driver, 5);

		//implementing explicit wait 
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add-master-courses']/a[1]/div[1]/div[2]/div[1]/h3")));

		driver.findElement(By.xpath("//*[@id='add-master-courses']/a[1]/div[1]/div[2]/div[1]/h3")).click();

		//implementing page Load Timeout Wait 
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		//Getting title of selenium course page
		String s = driver.getTitle();		
		System.out.println(s);

		//validating the title of selenium course page
		if(s.contains("Selenium 3.0 Web Driver Online Training"))	//But title is different in actual
			//Test Pass
			System.out.println("Title is same:: Test Passed");
		else 
			System.out.println("Title is different:: Test Failed");

		//navigating back to previous page 
		driver.navigate().back();

		//wait for whole page to load
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.findElement(By.id("search-inp3")).click();
		WebElement searchBox2 = driver.findElement(By.id("search-inp-overlay-new"));
		searchBox2.sendKeys("All courses");
		searchBox2.sendKeys(Keys.ENTER);

		//implementing Fluent Wait
		Wait wait = new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
	}
	
		public void quitBrowser() {
			if(driver!=null)
				driver.quit();
		}




	//driver.manage().timeouts().setScriptTimeout(50,TimeUnit.SECONDS);			//setScript Timeout 

	public static void main(String[] args) {				//Main Method

		WaitsInSelenium waitSel = new WaitsInSelenium ();

		waitSel.openBrowser("chrome", "https://www.edureka.co/");
		waitSel.browserCapabilities();
		waitSel.selSearch();
		waitSel.selCourse();	
		waitSel.quitBrowser();

	}
}
