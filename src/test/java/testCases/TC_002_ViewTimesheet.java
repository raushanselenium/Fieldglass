package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.TimesheetsPage;
import utilities.DateFormatter;
import utilities.XLUtility;

public class TC_002_ViewTimesheet extends Base {
	
	@Test(dataProvider = "LoginData")
	public void view_Timesheet(String username,String password,String fromDate,String toDate)
	{
		try 
		{
			logger.info("*****STARTED TC_002_ViewTimesheet*****");
			String[] fdate=fromDate.split("\\.");
			String dayF=fdate[0];
			String monthF=fdate[1];
			String yearF=fdate[2];
			
			String[] edate=toDate.split("\\.");
			String dayE=edate[0];
			String monthE=edate[1];
			String yearE=edate[2];
			
			String path=rb.getString("path_output");
			XLUtility xlutil=new XLUtility(path);
			
			driver.get(rb.getString("url"));
			logger.info("URL is opened");
			driver.manage().window().maximize();
			logger.info("Browser is maximized");
			
			LoginPage lp=new LoginPage(driver);
			lp.setUsername(username);
			logger.info("Username is entered");
			lp.setPassword(password);
			logger.info("Password is entered");
			lp.clickSignIn();
			logger.info("Clicked SignIn button");
			
			HomePage hp=new HomePage(driver);
			logger.info("Home Page is displayed");
			hp.clickViewLink();
			logger.info("Clicked View Link");
			hp.clickTimesheetLink();
			logger.info("Clicked Timesheet link");
			
			TimesheetsPage tsp=new TimesheetsPage(driver);
			logger.info("Timesheet page is displayed");
			tsp.setFromDate(dayF,monthF,yearF);
			logger.info("From Date is selected");
			tsp.setToDate(dayE, monthE, yearE);
			logger.info("To Date is selected");
			tsp.clickApplyFilter();
			logger.info("Clicked on Apply filter");
			Thread.sleep(3000);
			tsp.setDataInExcel(username, xlutil);
			logger.info("Data is fetched into excel successfully!!!");
			
		}
		
		catch(Exception e)
		{
			logger.error("Exception occured");
			e.printStackTrace();
			Assert.fail();
		}
		
		
		logger.info("*****FINISHED TC_002_ViewTimesheet*****");
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String inputPath=rb.getString("path_input");
		XLUtility xlutil=new XLUtility(inputPath);
		
		int totalRows=xlutil.getRowCount("sheet1");
		int totalCols=xlutil.getCellCount("sheet1",0);
		System.out.println("Rows= "+totalRows+"--->Cols= "+totalCols);
		
		String loginData[][]=new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalCols;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("sheet1", i, j);
			}
		}
		
		return loginData;
	}

}
