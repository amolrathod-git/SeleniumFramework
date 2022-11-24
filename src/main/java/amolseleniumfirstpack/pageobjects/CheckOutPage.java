package amolseleniumfirstpack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amolseleniumfirstpack.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement searchCountryinDropdown;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountry; 
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By foundCountry = By.cssSelector(".ta-results");
	By submitButton= By.cssSelector(".action__submit");
	
	public void selectCountry(String country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(searchCountryinDropdown, country).build().perform();
		waitForElementToAppear(foundCountry);
		selectCountry.click();
		  
		
	}
	
	public ConfirmationPage placeOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
			 
		//a.sendKeys(Keys.PAGE_DOWN).build().perform();
		//waitForElementToAppear(submitButton);
		WebElement placeOrderBtn= submit;
		js.executeScript("arguments[0].click();", placeOrderBtn);
		
		return new ConfirmationPage(driver);
	}
	
	

}
