package browserFactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

public class BrowserFactory {
	
	static WebDriver driver=null;
	
	public static WebDriver getBrowserInstance()
	{
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName, String ApplicationURL)
	{
		//WebDriver driver=null;
		
		if (browserName.contains("Chrome")) 
		{
		   driver=new ChromeDriver();
		   System.out.println("Chrome browser started");
		}
		else if (browserName.contains("FireFox")) 
		{
			driver=new FirefoxDriver();
			System.out.println("FireFox browser started");
		}
		else if (browserName.contains("Edge"))
		{
			driver=new EdgeDriver();
			System.out.println("Edge browser started");
		}
		else if (browserName.contains("Safari")) 
		{
			driver=new SafariDriver();
			System.out.println("chrome browser started");
		}
		else 
		{
			Reporter.log("Sorry "+browserName+ "not supported, running in deafault browser", true);
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
		
		driver.get(ApplicationURL);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}

}
