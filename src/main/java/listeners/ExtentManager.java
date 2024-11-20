package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.Utility;

public class ExtentManager 
{
   
	public static ExtentReports extent;
	
	
	public static ExtentReports getInstance()
	{		
		if (extent==null) 
		{
			extent=createInstance();
			return extent;
		}	
		else 
		{
			return extent;
		}			
	}
	
	
	public static ExtentReports createInstance()
	{
		
		ExtentSparkReporter sparkReportor=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/Automation_"+Utility.getCurrentTime()+".html");
		sparkReportor.config().setTheme(Theme.DARK);
		sparkReportor.config().setReportName("Automation Report");
		sparkReportor.config().setDocumentTitle("Sprint 1 Report");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(sparkReportor);
		
		return extent;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
