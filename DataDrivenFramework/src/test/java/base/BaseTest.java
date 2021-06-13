package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import extentListeners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".//src//test//resources//excel//TestData.xlsx");
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	
	public void quit()
	{
		driver.quit();
		log.info("Test execution completed!!");
	}
	
	public boolean isElementPresent(String locator) {
	
	try {
	
		if (locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if (locator.endsWith("_ID")) {
		driver.findElement(By.id(OR.getProperty(locator)));
		}else if (locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
		
		log.info("Checking the element presence for : "+locator);
		ExtentListeners.test.info("Checking the element presence for : "+locator);
	
		
		return true;
		
	  }catch (Throwable t) {
		  
		  log.info("Error while finding element : "+locator+" error message is :"+t.getMessage());
			ExtentListeners.test.info("Checking the element presence for : "+locator+" error message is :"+t.getMessage());
			
			return false;
		}
	
		
	
	}
	public void click(String locator)
	{
		if (locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if (locator.endsWith("_ID")) {
		driver.findElement(By.id(OR.getProperty(locator))).click();
		}else if (locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		log.info("Clicking on an element: "+locator);
		ExtentListeners.test.info("Clicking on an element: "+locator);
	}
	
	public void type(String locator, String value)
	{
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			}
		log.info("Typing of an element: "+locator+" entered type value as: "+value);
		ExtentListeners.test.info("Typing of an element: "+locator+" entered type value as: "+value);
	}
	
	
	public void clear(String locator)
	{
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
			}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).clear();
			}else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
			}
		log.info("Clear all of an element: "+locator);
		ExtentListeners.test.info("Clear all of an element: "+locator);
		
	}
	
	
    static WebElement dropdown;
	
	public void select(String locator, String value)
	{
		if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			
			
			}else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			
			
			}else if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
			}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		
		log.info("Selecting from dropdown : "+locator+" value as "+value);
		ExtentListeners.test.info("Selecting from dropdown : "+locator+" value as "+value);
	}
	
	
	
	@BeforeSuite
	public void setup() {
		
		if (driver == null) {
			
			PropertyConfigurator.configure(".//src//test//resources//properties//log4j.properties");
			log.info("Test Execution Started !!!");
			
			try {
				fis = new FileInputStream(".//src//test//resources//properties//OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
				log.info("OR properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(".//src//test//resources//properties//Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Config.load(fis);
				log.info("Config properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if (Config.getProperty("browser").equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome Browser launched !!!");
			}
			
			else if (Config.getProperty("browser").equals("edge")) {
				
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.info("Edge Browser launched !!!");
			}
			
			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigated to : "+Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			
			try {
				DbManager.setMysqlDbConnection();
				log.info("DataBase Connection Established !!!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
			
		}
		
	}
	
	@AfterSuite
	public void tearDown() {
		
		quit();
		
	}
	
}
