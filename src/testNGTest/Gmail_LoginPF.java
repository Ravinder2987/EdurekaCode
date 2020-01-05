package testNGTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Gmail_LoginPF {
	WebDriver driver;
	
	public Gmail_LoginPF(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	//using FindBy to locate elements 
	@FindBy(how=How.XPATH, using="//input[@id='identifierId']") WebElement email;
	@FindBy(how=How.XPATH, using="//span[@class='CwaK9']") WebElement nextButton;
	@FindBy(how=How.XPATH, using="//input[@name='password']") WebElement password;
	@FindBy(how=How.XPATH, using="//div[@id='passwordNext']//span[@class='CwaK9']") WebElement nextClick;
	
	//Defining all user actions (methods) that can be performed to login to gmail account
	
	//enter email address to login
	public WebElement emailLogin() {
		return email;
	}
	
	public WebElement clickNext() {
		return nextButton;
	}
	
	public WebElement loginPassword() {
		return password;
	}
	
	public WebElement nextNEXTButton() {
		return nextClick;
	}
	
}
