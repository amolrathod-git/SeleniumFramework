package amolseleniumfirstpack.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import amolseleniumfirstpack.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialiazeDriver() throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\amolseleniumfirstpack\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		
		//FirefoxDriver
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup(); 
			driver =new FirefoxDriver();
				 
				/*
				 * System.setProperty("webdriver.edge.driver" ,
				 * "C:\\Users\\amolr\\Downloads\\Softwares\\edgedriver_win64\\msedgedriver.exe")
				 * ; WebDriver driver = new EdgeDriver();
				 */
			
			}
			//Chrome Driver
			else if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup(); 
				driver =new ChromeDriver();
			}
			
			//EdgeDriver
			else if (browserName.equals("edge")) {
				WebDriverManager.edgedriver().setup(); 
				driver =new EdgeDriver();
			}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		return driver;	
		}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		
		//String to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	//capture screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"\\report\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\report\\"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver= initialiazeDriver();
		landingPage= new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
		
}
