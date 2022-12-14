package amolseleniumfirstpack.pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amolseleniumfirstpack.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		
		PageFactory.initElements(driver, this);
	}



	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css=".ng-trigger-flyInOut")
	WebElement loginErrorMsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCataloguePage loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCataloguePage(driver);
	}
	
	public String getLoginErrorMessage()
	{
		waitForWebElementToAppear(loginErrorMsg);
		return loginErrorMsg.getText();
	}
	
	
	
	
	
	
	

}
