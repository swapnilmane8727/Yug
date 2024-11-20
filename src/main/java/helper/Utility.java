
package helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

public class Utility {

	
	public static WebDriver startBrowser(String browserName, String ApplicationURL)
	{
		WebDriver driver=null;
		
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
	
	
	public static void clickElement(WebDriver driver, By locator)
	{		
		try 
		{
			driver.findElement(locator).click();
		} 
		catch (Exception e) 
		{			   
			try 
			{
				System.out.println("trying with Actions class");
				Actions act=new Actions(driver);
				act.moveToElement(driver.findElement(locator)).build().perform();
			} 
			catch (Exception e1) 
			{
				System.out.println("trying with Javascipt click");
				JavascriptExecutor js=(JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", driver.findElement(locator));				
			}		
		}
		
	}
	
	public static WebElement highlightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		//highlight element yellow color
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border:2px solid red')", element);  
		
		waitForSeconds(1);
		
		//Unhighlight element white color
		js.executeScript("arguments[0].setAttribute('style', 'background: white;')", element);
		
		return element;
		
		
	}
	
	public static WebElement highlightElement(WebDriver driver, By locator)

	{
		WebElement element=driver.findElement(locator);
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		//highlight element yellow color
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border:2px solid red')", element);  
		
		waitForSeconds(1);
		
		//Unhighlight element white color
		js.executeScript("arguments[0].setAttribute('style', 'background: white;')", element);
		
		return element;
		
		
	}
	
    public static Alert  waitForAlert(WebDriver driver)
	{
		  Alert alt=null;
	  	  
		  for(int i=0; i<=15; i++)
		  {		  
			  try 
			  {
				 alt=driver.switchTo().alert(); 
				  break;
		   	  } 
			  catch (NoAlertPresentException e) 
			  {
				System.out.println("Alert not found, waiting......");
				waitForSeconds(1);
			  } 		  
		  }
		  return alt;
	}
		
	public static Alert  waitForAlert(WebDriver driver, int time)
	{
		  Alert alt=null;
	  	  
		  for(int i=0; i<=time; i++)
		  {		  
			  try 
			  {
				 alt=driver.switchTo().alert(); 
				  break;
		   	  } 
			  catch (NoAlertPresentException e) 
			  {
				System.out.println("Alert not found, waiting......");
				waitForSeconds(1);
			  } 		  
		  }
		  return alt;
	}
		  
    public static void waitForSeconds(int seconds)
		  {
			try 
			{
				Thread.sleep(seconds*1000);
			} 
			catch (InterruptedException e) 
			{
				
			}  
		  }
    
    
    public static String captureScreenShotInBase64(WebDriver driver)
    {
    	TakesScreenshot ts=(TakesScreenshot)driver;
    	
        String base64=ts.getScreenshotAs(OutputType.BASE64);
        
        return base64;
        
    
    }
    
    
		
    public static void captureScreenShot(WebDriver driver)    
    {
    	
    	try 
   	 {
   		org.openqa.selenium.io.FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./ScreenShots/Screenshot_"+getCurrentTime()+".png"));
   	 } 
   	 catch (IOException e) 
    	{
   		 System.out.println("Something went wrong "+e.getMessage());
   	 }
    	
    	
    }
    	
    public static String getCurrentTime()

    
    {
    	String date=new SimpleDateFormat("HH-mm-ss_DD_MM_YYYY").format(new Date());
    	return date;
    }

    public static void captureScreenShotOfWebElement(WebDriver driver, WebElement element)
    {
    	
    	try 
    	{
    	File src=((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);

    	FileHandler.copy(src, new File("./ElementScreenShots/Element_"+Utility.getCurrentTime()+".png"));
    	} 
    	catch (IOException e) 
    	{
    		
    		System.out.println("error capturing SS" +e.getMessage());
    	}
    	
    }


}
	
	
