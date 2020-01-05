import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaCandidate {

	WebDriver driver ;

	/*Successfully acheieved below conditions of this case study:
		1. log In Edureka account
		2. Navigate to My Profile
		3. Update professional and ppersonal details
		4. Explore the blog and navigate to main page
		5. logOut of the portal
		6. quit the browser */

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);


		if(url!="") 
			driver.get(url);
		else 
			driver.get("https://www.edureka.co/");


	}

	public void closeBrowser(){
		if (driver != null){
			driver.close();
			driver.quit();
		}
	}

	public void logIn() {

		//click log in link
		driver.findElement(By.linkText("Log In")).click();


		WebDriverWait w = new WebDriverWait(driver, 30);

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#si_popup_email")));

		WebElement email = driver.findElement(By.cssSelector("#si_popup_email"));

		//enter email address 
		email.sendKeys("putyouremail@gmail.com");
		email.sendKeys(Keys.TAB);

		//enter password
		driver.findElement(By.cssSelector("#si_popup_passwd")).sendKeys("putyourpassword");
		email.clear();
		email.sendKeys("putyouremail@gmail.com");
		driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block']")).click();


	}

	public void userInfo() {

		//click on user account
		driver.findElement(By.xpath("//span[@class='user_name']")).click();

		//click My Profile 
		driver.findElement(By.xpath("//a[contains(text(),'My Profile')]")).click();

		//click on edit pencil button
		driver.findElement(By.xpath("//a[@id='personal_details']//i[@class='icon-pr-edit']")).click();

		//enter updated user name
		driver.findElement(By.name("fullname")).sendKeys("Sample Name");

		WebDriverWait w = new WebDriverWait(driver, 30);

		driver.findElement(By.xpath("//span[@class='changes-saved']")).isDisplayed();

		//update professional details
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Professional Details')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Professional Details')]")).click(); 

		//update company name
		driver.findElement(By.name("companyName")).sendKeys("abc123");
		driver.findElement(By.xpath("//li[@class='brand-logo']//i[@class='icon-Arrow-Left']")).click();

	}
	public void navigateToBlog() {
		//navigate to blogs
		driver.findElement(By.xpath("//button[contains(text(),'Community')]")).click();
		driver.findElement(By.xpath("//a[@class='dropdown-item'][contains(text(),'Blog')]")).click();

		//click on bigdata blog page
		driver.findElement(By.xpath("//section[@class='categories-section']//div[3]//a[1]//div[1]")).click();

		//click on blog hadoop administration
		driver.findElement(By.xpath("//h3[contains(text(),'Hadoop Administration')]")).click();

		//navigate to home page 
		driver.findElement(By.linkText("Home")).click();

	}

	public void logOut() {
		//click on log out
		driver.findElement(By.xpath("//a[@class='dropdown-toggle trackButton']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu user-menu profile-xs hidden-sm hidden-xs']//a[@class='trackButton'][contains(text(),'Log Out')]")).click();

	}


	public static void main(String[] args)  {

		EdurekaCandidate ec = new EdurekaCandidate();
		ec.openBrowser("chrome", "https://www.edureka.co/");
		ec.logIn();
		ec.userInfo();
		ec.navigateToBlog();
		ec.logOut();
		ec.closeBrowser();		

	}

}
