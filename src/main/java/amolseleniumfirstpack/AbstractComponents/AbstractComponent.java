package amolseleniumfirstpack.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amolseleniumfirstpack.pageobjects.CartPage;
import amolseleniumfirstpack.pageobjects.OrderHistoryPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By ByElement)
	{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
    wait.until(ExpectedConditions.visibilityOfElementLocated(ByElement));
	}
	
	public void waitForWebElementToAppear(WebElement wEle)
	{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
    wait.until(ExpectedConditions.visibilityOf(wEle));
	}
	
	public void waitForElementToDisppear(WebElement spinnerPF)
	{
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(12));
    wait.until(ExpectedConditions.invisibilityOf(spinnerPF));
	}
	
	public CartPage goToCart()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderHistoryPage gotoOrdersPage()
	{
		orderHeader.click();
		OrderHistoryPage orderHistoryPage=new OrderHistoryPage(driver);
		return orderHistoryPage;
	}
	
	

}
