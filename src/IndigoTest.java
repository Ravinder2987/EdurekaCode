
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndigoTest {

	//CASE STUDY 3 MODULE 4 

	WebDriver driver ;


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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		((JavascriptExecutor)driver).executeScript("document.body.style.zoom='80%';");
		if(url!="") 
			driver.get(url);
		else 
			driver.get("https://www.goindigo.in/?linkNav=home_header");
		
	}
	
	public void closeBrowser(){
		if (driver != null){
			driver.close();
			driver.quit();
		}
	}
	

	public void destinationDetails()  {

		//using sibling traverse XPATH
		driver.findElement(By.xpath("//input[@id='oneWayTrip']/following-sibling::label")).isSelected();
		WebElement dep = driver.findElement(By.name("or-src"));
		dep.click();
		dep.sendKeys("Bengaluru");
		dep.sendKeys(Keys.ENTER);
		
		
		
		WebDriverWait w = new WebDriverWait(driver, 5);
		WebElement arr = driver.findElement(By.name("or-dest"));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("or-dest")));

		
		arr.click();
		arr.sendKeys("Lucknow");
		arr.sendKeys(Keys.ENTER);

	}
	
	
	

	public void currentDate() {		
		
		
		WebElement element = driver.findElement(By.xpath("//div[starts-with(@id,'dp')]/descendant::div/a[contains(text(), 'Done')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	
		Actions actions = new Actions(driver);
		WebElement element_1 = driver.findElement(By.xpath("//div[starts-with(@id,'dp')]/descendant::div/a[contains(text(), 'Done')]"));

		actions.moveToElement(element_1).click().build().perform();
		
	
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//WebElement date = driver.findElement(By.xpath("//div[starts-with(@id,'dp')]/descendant::div/a[contains(text(), 'Done')]"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[starts-with(@id,'dp')]/descendant::div/a[contains(text(), 'Done')]")));
		element_1.click();
		
		
		
	}
	
	public void calender1() {
		// driver.findElement(By.xpath("//div[@class='ig-input-group field-float cal-focus']//input[@placeholder='Departure Date']"));
		//driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']"));
		WebElement element = driver.findElement(By.xpath("//a[@class='btn btn-primary dateClose']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		//element.click();
		
		element.sendKeys(Keys.TAB);
		
	}
	
	public void numOfPassengers() {
		
		WebElement element = driver.findElement(By.name("passenger"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		
		driver.findElement(By.name("passenger")).click();
		
		driver.findElement(By.xpath("//div[@class='passenger-dropdown pax-selection-row']//li[@class='adult-pax-list']//span[@class='icon-plus']")).click();
		driver.findElement(By.xpath("//div[@class='passenger-dropdown pax-selection-row']//li[@class='adult-pax-list']//span[@class='icon-plus']")).click();
		
	
		//driver.findElement(By.cssSelector("body.hideHelp:nth-child(2) div.body-wrap:nth-child(7) div.bookingwidget.section div.container.ig-container.ig-search-container.bw-card-view:nth-child(2) div.row.clearfix div.ig-hp-search.booking-widget-container.card-view div.ig-hp-search-form div.hpFlightSearch.tab-content.clearfix:nth-child(3) div.tab-pane.active.book-flight-container:nth-child(1) form.flight-search-form.tab-content.ig-form-validation-style div.row.justify-content-around.ie-justify-pass-cur:nth-child(5) div.col-sm-5.col-5.padd-left.pax-selection-container:nth-child(1) div.passenger-dropdown.pax-selection-row ul.passengers-list.clearfix.list-unstyled li.child-pax-list div.no-of-counts:nth-child(2) > button.pax-plus.btn.btn-info")).click();
		
		driver.findElement(By.xpath("//div[@class='passenger-dropdown pax-selection-row']//li[@class='child-pax-list']//button[@class='pax-plus btn btn-info']")).click();
		
		
		
	}
	
	public void quitBrowser() {
		if(driver!=null)
			driver.quit();
	}
	
	

	public static void main(String[] args)  {
		IndigoTest fb = new IndigoTest();
		fb.openBrowser("chrome", "https://www.goindigo.in/?linkNav=home_header");
	
		fb.destinationDetails();
		fb. calender1();
		//fb.currentDate();
		//fb.numOfPassengers();
		
	//fb.quitBrowser();

	}
}

	

		

	

