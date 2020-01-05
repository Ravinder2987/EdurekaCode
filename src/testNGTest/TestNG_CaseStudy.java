package testNGTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;

/*Case Study Questions:
 * 1. Launch the Chrome Browser
 * 2. Log in to account and browser for all the courses
 * 3. Logout of the account
 * 4. Use all the concepts learnt in TestNG (dependency, prioroty etc)
 * 5. Create another case to register the user with same email and an exception has to be shown on portal
 * 6. Check for the HTML report generated
 */


public class TestNG_CaseStudy {
	WebDriver driver;

	//Step 1: Launch the Chrome Browser

	@Test (priority=0)
	public void setUp() {

		String currDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", currDir+"\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.edureka.co/");

	}


	//Step 2: Log in to account and browser for all the courses

	@Test (priority=1)
	public void login() throws InterruptedException {

		driver.findElement(By.linkText("Log In")).click();
		WebElement email = driver.findElement(By.cssSelector("#si_popup_email"));
		email.sendKeys("enteryouremailid@gmail.com");
		email.clear();
		driver.findElement(By.cssSelector("#si_popup_passwd")).sendKeys("xxxxxxxxxxxx");
		email.sendKeys("enteryouremailid@gmail.com");
		driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block']")).click();
	}

	//Browse for all courses 
	@Test (priority=2)
	public void allCourses() {
		//locate searchbar then browse for all courses
		driver.findElement(By.xpath("//input[@placeholder='Search for a skill you wish to master']")).sendKeys("all courses");	
		driver.findElement(By.xpath("//i[@class='icon-Home-page-search']")).click();

	}

	//Step 3. Logout of the account
	@Test (dependsOnMethods= {"allCourses"})
	public void logOut() {
		driver.findElement(By.xpath("//span[@class='user_name']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
	}

	//SignUp with the already registered account
	@Test (dependsOnMethods= {"logOut"})
	public void signUpAgain() throws InterruptedException {
		driver.findElement(By.linkText("Sign Up")).click();
		driver.findElement(By.xpath("//input[@id='sg_popup_email']")).sendKeys("enteryouremailid@gmail.com");
		driver.findElement(By.xpath("//input[@id='sg_popup_phone_no']")).sendKeys("xxxxxxxxxxxx");
		driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block signup-new-submit']")).click();

		//exception thrown on portal 
		WebElement textMsg = driver.findElement(By.xpath("//p[contains(text(),'You are registered with us. Login to continue.')]"));
		String text = textMsg.getText();
		System.out.println(text);

	}

	@AfterTest (enabled=true)
	public void tearDown() {
		if(driver!=null)
			driver.quit();
	}
}
