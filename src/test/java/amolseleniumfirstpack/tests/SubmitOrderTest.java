package amolseleniumfirstpack.tests;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import amolseleniumfirstpack.TestComponents.BaseTest;
import amolseleniumfirstpack.pageobjects.CartPage;
import amolseleniumfirstpack.pageobjects.CheckOutPage;
import amolseleniumfirstpack.pageobjects.ConfirmationPage;
import amolseleniumfirstpack.pageobjects.LandingPage;
import amolseleniumfirstpack.pageobjects.OrderHistoryPage;
import amolseleniumfirstpack.pageobjects.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider="getData", groups= {"purchase"})
	public void submitOrder(HashMap<String ,String> input) throws IOException , InterruptedException{
		// TODO Auto-generated method stub
		
		
		String countryName="india";
		ProductCataloguePage productCataloguePage=landingPage.loginApplication(input.get("email"), input.get("password"));
		CartPage cartPage=productCataloguePage.addProductToCart(input.get("productName"));
		cartPage.goToCart();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);	
		  
		CheckOutPage checkOutPage=cartPage.goToCheckout();
		
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage  = checkOutPage.placeOrder();
		
		String confirmMessage=confirmationPage.getOrderCofirmMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		String productName="ZARA COAT 3";
		ProductCataloguePage productCataloguePage=landingPage.loginApplication("amolrathod194@gmail.com", "Testing123");
		OrderHistoryPage orderHistoryPage=productCataloguePage.gotoOrdersPage();
		Assert.assertTrue(orderHistoryPage.verifyHistoryOrderDisplay(productName));
	}
	
	
	/*
	 * @DataProvider 
	 * public Object[][] getData() 
	 * { 
	 * //Object data[][]=new Object[2][2]; then defining the values with all the index 
	 * return new Object[][] {{"amolrathod194@gmail.com","Testing123"},{"amolrathod194@gmail.com","Testing123"}}; }
	 */
	
	/*
	 * @DataProvider public Object[][] getData() 
	 * { 
	 * HashMap<String, String> map=new HashMap<String, String>(); 
	 * map.put("email", "amolrathod194@gmail.com");
	 * map.put("password", "Testing123"); 
	 * map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1=new HashMap<String, String>(); 
	 * map1.put("email","amolrathod194@gmail.com"); 
	 * map1.put("password", "Testing123");
	 * map1.put("productName", "ZARA COAT 3"); 
	 * Object[][] data=new Object[][] {{map},{map1}};
	 * return data; 
	 * }
	 */
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\amolseleniumfirstpack\\data\\purchase.json");
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
	
	
}