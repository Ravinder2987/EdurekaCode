
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


//get all the footer links in an array
//click on each link, if it opens in the same browser then navigate back to old window
//if it opens in a new window, then close the new window
//then take the control back to first window and click the next link. 

public class facebookLinks {


	WebDriver driver;


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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	public void getLinks() {

		List<WebElement> footerLinks = driver.findElements(By.cssSelector("#pageFooterChildren a"));
		List <String> linkNames = new ArrayList<>();

		for (int i = 0; i<footerLinks.size(); i++) {

			linkNames.add(footerLinks.get(i).getText());
			WebElement ele = footerLinks.get(i);
			System.out.println(ele.getText());

			System.out.println(footerLinks.get(i).getText());

		}

	}
	
	public void clickLink() throws InterruptedException {
		
		List<WebElement> footerLinks = driver.findElements(By.cssSelector("#pageFooterChildren a"));
		
		//String currentWindowHandle = driver.getWindowHandle();
		
		ArrayList<String> tabHandles = new ArrayList<String> (driver.getWindowHandles());
		String newTab = "https://www.instagram.com/";
		boolean newFoundTab = false;
		
		for (String eachHandle : tabHandles) {
		driver.switchTo().window(eachHandle);
		
		if(driver.getTitle().equals(newTab)) {
		driver.close();
		
		
		//coming back to old tab
		driver.switchTo().window(currentWindowHandle);
		newFoundTab = true;
	 }
		}
		
		System.out.println("---------Total no. of Links found in Footer------ ::  "+footerLinks.size());
		
		for (int i = 0; i<footerLinks.size(); i++) {
			
			WebElement ele = footerLinks.get(i);
			System.out.println(ele.getText());
			
			ele.click();
			driver.navigate().back(); 
			
			//Thread.sleep(3000);
			footerLinks = driver.findElements(By.cssSelector("#pageFooterChildren a"));		//otherwise got stateElementException error
			
		}
	}

	public void closeBrowser(){
		if (driver != null){
			driver.close();
			driver.quit();
		}
	}



	public static void main(String[] args) throws InterruptedException {
		facebookLinks fb = new facebookLinks();
		fb.openBrowser("chrome", "https://www.facebook.com");
		
		fb.clickLink();
		
		fb.closeBrowser();

	}


}





