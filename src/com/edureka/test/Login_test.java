package com.edureka.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.edureka.utilities.ReadExcel;
import com.edureka.utilities.WriteExcel;

public class Login_test {

	WebDriver driver;

	@BeforeTest
	public void openBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vishal\\eclipse-workspace\\Edureka\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("https://www.edureka.co/");
	}

	@Test
	public void Login() throws IOException {
		try {
			String[][] data= ReadExcel.getData("loginCredentials.xlsx", "credentials");

			for(int i=1; i<data.length;i++) {

				String username = data[i][0];
				String password = data[i][1];

				//locating elements on edureka login portal
				driver.findElement(By.linkText("Log In")).click();
				
				//enter email address from wxcwl sheet 
				driver.findElement(By.xpath("//input[@id='si_popup_email']")).sendKeys(username);
				
				//enter password from excel sheet 
				driver.findElement(By.xpath("//input[@id='si_popup_passwd']")).sendKeys(password);
				
				//click on login button
				driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block']")).click();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}


		//open blogs in new tab
		driver.findElement(By.xpath("//div[@class='col-lg-4 col-md-4 col-sm-4 col-xs-6 no-padding index_link']//a[contains(text(),'Blog')]")).click();

		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		driver.get("https://www.edureka.co/blog/");
		driver.findElement(By.xpath("//div[18]//a[1]//div[1]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'READ MORE')]")).click();
		driver.findElement(By.xpath("//div[@id='heading86401']//a[@class='btn btn-link text-left side-menu-nav-button']")).click();
		driver.get("https://www.edureka.co/blog/interview-questions/selenium-interview-questions-answers/");

	}

	@Test 
	public void writeExcel( ) throws IOException {
		WriteExcel we = new WriteExcel();
		we.writeData();
	}

	@AfterTest
	public void shutDown() {
		driver.close();
		driver.quit();
	}
}
