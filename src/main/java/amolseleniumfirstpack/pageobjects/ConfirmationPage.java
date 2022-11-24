package amolseleniumfirstpack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import amolseleniumfirstpack.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public  String getOrderCofirmMessage() {
		String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		return confirmMessage;
	}
	
}
