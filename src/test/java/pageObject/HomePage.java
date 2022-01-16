package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[normalize-space()='View']")
	WebElement lnkView;
	
	@FindBy(xpath = "//a[@id='viewMenu_3_timeSheets_link']")
	WebElement lnkTimesheet;
	
	public void clickViewLink()
	{
		lnkView.click();
	}
	
	public void clickTimesheetLink()
	{
		lnkTimesheet.click();
	}

}
