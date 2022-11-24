package amolseleniumfirstpack.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amolseleniumfirstpack.AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {
	
	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProducts;
	
	

	public Boolean verifyHistoryOrderDisplay(String productName)
	{
		 Boolean match  = orderedProducts.stream().anyMatch(oderHistory->oderHistory.getText().equalsIgnoreCase(productName));
		 return match;
	}
	
}
