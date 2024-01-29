package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "(//input[@type=\"text\"])[2]")
	WebElement txtEmailAddress;
	
	@FindBy(xpath = "//input[@type=\"password\"]")
	WebElement txtpassword;

	@FindBy(xpath = "//input[@type=\"submit\"]")
	WebElement btnLogin;
	
	
	public void setEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	public void clickLogin()
	{
		btnLogin.click();
		
	}
}
