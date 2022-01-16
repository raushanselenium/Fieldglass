package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pageObject.LoginPage;
import utilities.LoginDataProvider;

public class TC_001_LoginTC extends Base {
	
	@Test(dataProvider="LoginData", dataProviderClass=LoginDataProvider.class)
	public void test_Login(String username,String password,String fromDate,String toDate)
	{
		try 
		{
			logger.info("*****STARTED TC_001_LoginTC*****");
			driver.get(rb.getString("url"));
			logger.info("URL is opened");
			driver.manage().window().maximize();
			logger.info("Window is maximized");
			LoginPage lp=new LoginPage(driver);
			lp.setUsername(username);
			logger.info("Username entered");
			lp.setPassword(password);
			logger.info("Password entered");
			lp.clickSignIn();
			logger.info("Clicked on SignIn button");
			boolean isLogin=lp.isLoginSuccessful();
			if(isLogin)
			{
				logger.info("Yay!!! Login is successful");
				Assert.assertTrue(true);
			}
			else {
				captureScreen(driver, "test_Login");
				logger.error("Ufff!!! Login failed");
				Assert.assertTrue(false);
			}
			logger.info("*****Finished TC_001_LoginTC*****");
		}
		
		catch(Exception e)
		{
			logger.fatal("Exception occured");
			Assert.fail();
		}
		
	}

}
