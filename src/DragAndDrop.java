

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DragAndDrop {

	//Assignment DragnDrop

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


		if(url!="") 
			driver.get(url);
		else 
			driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

	}

	//Store names of all the capitals
	public void capitals() {
		List<WebElement> capList = driver.findElements(By.className("dragableBox"));

		List<String> capitalsText = new ArrayList<>();

		for(int i = 0; i<capList.size(); i++) {

			capitalsText.add(capList.get(i).getText());

			System.out.println(capList.get(i).getText());

		}


	}

	//	public void listOfCapitals()  {
	//
	//
	//		//WebElement ele = driver.findElement(By.className("dragableBox"));
	//		//Thread.sleep(3000);
	//
	//		List<WebElement> allCapitals = driver.findElements(By.xpath("//*[@id='capitals']/div/div[starts-with(@id, 'box')]"));
	//
	//		//taking all capitals in text in array
	//		List<String> allElementsText = new ArrayList<>();
	
	//		WebElement target = null;
	
	//		for(int i=1; i<=allCapitals.size();i++) {
	//			WebElement ele = driver.findElement(By.xpath("//*[@id='capitals']/div/div[@id='box"+i+"']"));
	//			allElementsText.add(ele.getText());
	//			System.out.println(ele.getText());
	
	//			if(ele.getText().equalsIgnoreCase("Oslo")) {
	
	//				ele.click();
	//				target = ele;
	//				//				target.click();
	//			}

	//	}

	//		//test code
	//		//		driver.findElement(By.xpath("//*[@id='capitals']/div/div[@id='DHTMLgoodies_dragableElement1']")).click();
	//
	//		WebElement lastElement = driver.findElements(By.xpath("//*[@id='capitals']/div/div[starts-with(@id, 'box')]")).get(allCapitals.size()-1);
	//
	//		if(lastElement.getText().equalsIgnoreCase(target.getText())) {
	//			System.out.println("Passed");
	//		}

	//}


	public void moveOslo() {
//		List<WebElement> cList = driver.findElements(By.xpath("//div[starts-with(@id,'box')]"));
//
//		List<String> cText = new ArrayList<>();
//		
////		for(int i = 0; i<cList.size(); i++) {
////	
////		cText.add(cList.get(i).getText());
////		System.out.println(cList.get(i).getText());
		
		WebElement oslo = driver.findElement(By.id("box1"));
		oslo.sendKeys(Keys.ENTER);
		
	
		
		}

	

	public void quitBrowser() {
		if(driver!=null)
			driver.quit();
	}



	public static void main(String[] args)  {
		DragAndDrop dd = new DragAndDrop();
		dd.openBrowser("chrome", "http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

		//dd.capitals();
		dd.moveOslo();

		//dd.listOfCapitals();
		//dd.quitBrowser();

	}
}

