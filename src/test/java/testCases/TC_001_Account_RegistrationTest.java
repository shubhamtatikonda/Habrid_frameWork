package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_Account_RegistrationTest extends BaseClass
{

	
	@Test(groups = {"Regreassion","master"})
	public void verify_account_registration()
	{
		logger.info("***Starting Test case number 1st***");
		logger.debug("Application Log Start.....");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("***clickMyAccount***");
		hp.clickRegister();
		logger.info("***clickRegister***");
		
		logger.info("***AccountRegistrationPage***");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("***click on continue***");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("test passed...");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test failed...");
			Assert.fail();
		}
		}
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
		}
		logger.debug("Application Log finish.....");
		logger.info("***finish Test case number 1st***");
		
	}
	
	
	
	
}








