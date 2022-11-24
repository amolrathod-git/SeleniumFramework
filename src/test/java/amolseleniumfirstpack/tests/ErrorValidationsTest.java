package amolseleniumfirstpack.tests;

import java.awt.Window;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import amolseleniumfirstpack.TestComponents.BaseTest;
import amolseleniumfirstpack.TestComponents.Retry;
import amolseleniumfirstpack.pageobjects.CartPage;
import amolseleniumfirstpack.pageobjects.CheckOutPage;
import amolseleniumfirstpack.pageobjects.ConfirmationPage;
import amolseleniumfirstpack.pageobjects.LandingPage;
import amolseleniumfirstpack.pageobjects.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{
	
	@Test (groups= {"ErrorHandling"},  retryAnalyzer=Retry.class)
	public void validateLogin() throws IOException , InterruptedException{
		// TODO Auto-generated method stub
		landingPage.loginApplication("amol@gmail.com", "Testing123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrorMessage());
		
	}
	
	@Test(groups= {"Amol"}, retryAnalyzer=Retry.class)
	public void validateCartProduct() throws InterruptedException
	{
	String productName="ZARA COAT 3";
	String countryName="india";
	ProductCataloguePage productCataloguePage=landingPage.loginApplication("amolrathod194@gmail.com", "Testing123");
	CartPage cartPage=productCataloguePage.addProductToCart(productName);
	cartPage.goToCart();
	
	Boolean match = cartPage.verifyProductDisplay(productName);
	Assert.assertTrue(match);	
	}
	
	

}