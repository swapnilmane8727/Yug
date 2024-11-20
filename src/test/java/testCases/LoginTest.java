package testCases;

import org.testng.annotations.Test;

import base.BaseClass;
import dataProvider.CustomDataProvider;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	
	
	@Test(dataProvider = "LoginDetails", dataProviderClass = CustomDataProvider.class)
	public void loginToApplication(String uname, String pass)
	{
	    LoginPage login=new LoginPage(driver);
		login.loginToApplication(uname, pass);
	
	}

}
