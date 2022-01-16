package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='usernameId_new']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@id='passwordId_new']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[normalize-space()='Sign In']")
	WebElement btnSignIn;
	
	@FindBy(xpath = "//h1[normalize-space()='Welcome Raushan Kumar']")
	WebElement nameAfterLogin;
	
	public void setUsername(String un)
	{
		//txtUserName.clear();
		txtUserName.sendKeys(un);
	}
	
	public void setPassword(String pwd)
	{
		//txtUserName.clear();
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSignIn()
	{
		btnSignIn.click();
	}
	
	public boolean isLoginSuccessful()
	{
		try {
		return (nameAfterLogin.isDisplayed());
		}
		catch(Exception e)
		{
			return (false);
		}
	}
	
}
