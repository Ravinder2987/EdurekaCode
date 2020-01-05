
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

public class FlightBooking {

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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

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

		WebElement html = driver.findElement(By.tagName("html"));
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

		//selecting one way 
		driver.findElement(By.xpath("//input[@id='oneWayTrip']/following-sibling::label")).isSelected();

		//from Bengalore
		WebElement dep = driver.findElement(By.name("or-src"));
		dep.click();
		dep.sendKeys("Bengaluru");
		dep.sendKeys(Keys.ENTER);

		//to Lucknow
		WebDriverWait w = new WebDriverWait(driver, 5);
		WebElement arr = driver.findElement(By.name("or-dest"));

		w.until(ExpectedConditions.visibilityOfElementLocated(By.name("or-dest")));

		arr.click();
		arr.sendKeys("Lucknow");
		arr.sendKeys(Keys.ENTER);

	}

	//selecting current date 
	public void currentDate() {	

		try {
			Thread.sleep(1000);
			Actions action = new Actions(driver);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)");

			WebElement doneButton = driver.findElement(By.xpath("//div[starts-with(@id, 'dp')]/div[1]/div[3]//div[following::*[contains(text(),'Done')]]"));

			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(doneButton));
			action.moveToElement(doneButton).build().perform();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		WebElement dateWidget = driver.findElement(By.xpath("//div[starts-with(@id, 'dp')]/descendant::table/tbody"));
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));

		int dateInInt = Integer.parseInt(dateFormat.format(date).toString()) +1;
		//int dateInInt = Integer.parseInt(dateFormat.format(date).toString());
		String d = String.valueOf(dateInInt);

		for (WebElement cell: columns){
			if (cell.getText().equals(d)){
				cell.findElement(By.linkText(d)).click();
				break;
			}
		}

	}
	//selecting 3 adults & 2 children 
	public void numOfPassengers() {
		WebElement element = driver.findElement(By.name("passenger"));

		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		WebElement ad = driver.findElement(By.xpath("//div[@class='passenger-dropdown pax-selection-row']//li[@class='adult-pax-list']//span[@class='icon-plus']"));
		ad.click();
		ad.click();


		WebElement child = driver.findElement(By.xpath("//div[@class='passenger-dropdown pax-selection-row']//li[@class='child-pax-list']//button[@class='pax-plus btn btn-info']"));
		child.click();
		child.click();

		driver.findElement(By.xpath("//button[@class='btn btn-primary pax-done btn-pad-y']")).click();
	}


	// searching one way flight from Bengalore to Lucknow for 5 passengers (3 adults and 2 children)
	public void searchFlights() {

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		//List of available flights 
		List<WebElement> flights = driver.findElements(By.xpath("//div[@id='flightSelectMount']/descendant::div[@class='trip-body-root']//div[contains(@id, 'BLR')]"));
		System.out.println("Available Flights are: ");

		for(WebElement ele: flights) {
			System.out.println(ele.getAttribute("id").split("~~~")[0]);
		}

	}

	public void quitBrowser() {
		if(driver!=null)
			driver.quit();
	}



	public static void main(String[] args)  {
		FlightBooking fb = new FlightBooking();
		fb.openBrowser("chrome", "https://www.goindigo.in/?linkNav=home_header");

		fb.destinationDetails();
		fb.currentDate();
		fb.numOfPassengers();
		fb.searchFlights();
		fb.quitBrowser();

	}
}
