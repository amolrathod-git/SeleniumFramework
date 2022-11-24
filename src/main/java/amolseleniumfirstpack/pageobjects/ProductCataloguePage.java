package amolseleniumfirstpack.pageobjects;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amolseleniumfirstpack.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

 @FindBy(css=".mb-3")
 List<WebElement> products;
 
 @FindBy(css=".ng-animating")
 WebElement spinnerPF ;
 
 By productsBy= By.cssSelector(".mb-3 .card");
 By addToCard = By.cssSelector(".card-body button:last-of-type");
 By toastMessage=By.cssSelector("#toast-container");
 By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addProductToCart(String productName) throws InterruptedException
	{  
		WebElement productToAdd = getProductByName(productName);
		productToAdd.findElement(addToCard).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisppear(spinnerPF);
		//Thread.sleep(5000);
		
		return new CartPage(driver);
		
	}
	
	

}
