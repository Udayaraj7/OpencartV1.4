package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_login() throws InterruptedException
	{
		logger.info("********* Starting TC002_LoginTest*****;***8");
		
		try {
			
		
		
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(p.getProperty("email"));
		loginPage.setPassword(p.getProperty("password"));
		loginPage.clickLogin();
		
		MyAccount a = new MyAccount(driver);
		Assert.assertEquals(true, a.isMyAccountPageExists());
		
		} catch (Exception e) {
			Assert.fail();
			
		}
		
		
		
		logger.info("********* Finished TC002_LoginTest**************8");
	}
}
