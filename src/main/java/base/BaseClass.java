package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import browserFactory.BrowserFactory;
import dataProvider.ConfigReader;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setupBrowser()
	{
	   System.out.println("LOG:Info - Setting up browser");
	   
	   // 1 - Config - Does not suite for cross browser
	   driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browser"), ConfigReader.getProperty("URL"));
	   
	   System.out.println("LOG:Info - Application is Up & running");

	}
	
	@AfterMethod
	public void closeBrowser()
	{
	   driver.quit();
	   
	   System.out.println("LOG:Info - Closing the browser and application");

	}
	

}
