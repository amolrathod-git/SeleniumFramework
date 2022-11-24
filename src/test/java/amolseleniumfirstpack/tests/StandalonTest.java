package amolseleniumfirstpack.tests;

import java.awt.Window;
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

import amolseleniumfirstpack.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandalonTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName="ZARA COAT 3";
		/*
		 * WebDriverManager.chromedriver().setup(); WebDriver driver =new
		 * ChromeDriver();
		 */
		System.setProperty("webdriver.edge.driver" , "C:\\Users\\amolr\\Downloads\\Softwares\\edgedriver_win64\\msedgedriver.exe");
	    WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("amolrathod194@gmail.com", "Testing123");
		
		  WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		  
			/*
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
			 * "#toast-container")));
			 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.
			 * cssSelector(".ng-animating"))));
			 */
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 
		  
		  List<WebElement> products =
		  driver.findElements(By.cssSelector(".mb-3 .card"));
		  
		  WebElement prod = products.stream().filter(product->
		  product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		  
		  prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		  
		  //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
		  Thread.sleep(15000);
		  driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		  
		  
		  List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection h3"));
		  
		  Boolean match  = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		  Assert.assertTrue(match);
		  
		  driver.findElement(By.cssSelector(".totalRow button")).click();
		  
		  Actions a = new Actions(driver);
		  a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		  driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		  
			
			  JavascriptExecutor js=(JavascriptExecutor) driver;
			  js.executeScript("window.scrollBy(0,100)");
			 
		  //a.sendKeys(Keys.PAGE_DOWN).build().perform();
		  //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		  WebElement placeOrderBtn= driver.findElement(By.cssSelector(".action__submit"));
		  js.executeScript("arguments[0].click();", placeOrderBtn);
		  
		  
		  String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		  
	}

}