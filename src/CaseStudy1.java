import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CaseStudy1 {

	WebDriver driver;
		
		
		public void openBrowser(String browserType, String url) {
			
			if(browserType.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			
			else if(browserType.equals("ie")) {
				System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			else if(browserType.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
				
				driver = new ChromeDriver();
				
				
			if(url!="") 
				driver.get(url);
				else 
				driver.get("https://edureka.co/");
			
				}
		}
		
			public void searchBar() {
				driver.findElement(By.id("search-inp3")).click();		//using ID locator
				driver.findElement(By.className("new-search-inp")).sendKeys("Search for a Course");	//using CLASSNAME locator
				driver.findElement(By.className("typeaheadbutton")).click();
				
				
				//Canceling the search and going back to home page
				driver.findElement(By.xpath("//i[@class='icon-Home-page-close']")).click();	 //using XPATH locator
				
			}
			
			public void browserElements() {				//examples of locating SEARCH BOX with different locators
				
				//By id
//				driver.findElement(By.id("search-inp3")).sendKeys("Search for a Course");
				
				//By name
//				driver.findElement(By.name("user_v1[query]")).sendKeys("Search for a Course");
				
				//By class name
//				driver.findElement(By.className("search_inp")).sendKeys("Search for a Course");
				
				//By xpath
//				driver.findElement(By.xpath("//input[@id='search-inp3']")).sendKeys("Search for a Course");
				
				//By css
//				driver.findElement(By.cssSelector("#search-inp3")).sendKeys("Search for a Course");
			}
			
			
			public void logIn() {
				String login = driver.findElement(By.xpath("//a[@href='javascript:void(0);']")).getText(); 
				System.out.println(login);					 //printing the text of LogIn button
			}
			
			public void quitBrowser() {
				
				driver.close();
			}
			
			
			
		public static void main(String[] args) {  						//main method
			
			CaseStudy1 cs1 = new CaseStudy1();							//creating object of class CaseStudy1 
			cs1.openBrowser("chrome", "https://edureka.co/");			//launching chrome browser 
			cs1.driver.manage().window().maximize();
			cs1.searchBar();											//calling searchBar method
			cs1.logIn();												//calling logIn method 
	
			
			//cs1.quitBrowser();
			
			}
	

}
