package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.TimesheetsPage;

public class Base {
	
	public WebDriver driver;
	public ResourceBundle rb;
	public Logger logger;
	
	@BeforeClass
	public void  setUP()
	{
		
		rb=ResourceBundle.getBundle("config");
		logger=LogManager.getLogger(this.getClass());
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		logger.info("Launched Chrome Browser");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+".png");
		FileUtils.copyFile(source, target);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
