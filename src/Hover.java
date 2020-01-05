

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class Hover {
	
	static WebDriver driver;
	
	
	public void openBrowser(String browserType, String url){
		
		String currentDir = System.getProperty("user.dir");
		
		if (browserType.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", currentDir+"\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserType.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", currentDir+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", currentDir+"\\drivers\\IEDriverserver.exe");
			driver = new InternetExplorerDriver();
		}
		
	
		if(url.isEmpty()){
			url = "about:blank";
		}
		
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void closeBrowser(){
		if (driver != null){
			driver.close();
			driver.quit();
		}
	}
	

	 public void hoverTest() {
		  Actions actions = new Actions(driver);
		  WebElement menuOpt = driver.findElement(By.id("nav-link-accountList"));
		  
		  //menuOpt.click();
		  actions.moveToElement(menuOpt).build().perform();
		
		  System.out.println("Mouse is on Account & Lists Menu Option");
		  
		  WebElement subMenuOpt = driver.findElement(By.linkText("Your Prime Membership"));
		  actions.moveToElement(subMenuOpt).build().perform();
  
		  
		  //To check sub menu option is enabled or not 
		  if(subMenuOpt.isEnabled()) {
			  System.out.println("Test Passed");
		  } else {  System.out.println("Test Failed");}
		
		  System.out.println("Mouse is now on Your Prime Membership Menu Option");
		  
	 }

	
	public static void main(String[] args) {
		Hover hv = new Hover();
		hv.openBrowser("chrome", "https://www.amazon.com");
		hv.hoverTest();
		
		hv.closeBrowser();
		
	}


}
