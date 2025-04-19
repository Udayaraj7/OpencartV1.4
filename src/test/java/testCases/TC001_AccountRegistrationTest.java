package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest  extends BaseClass{


	 
	@Test(groups={"Regression","Master"})
	public void  verify_account_registration() {
		logger.info("***** Starting  TC001_AccountRegistrationTest*****");
		
		try {
		HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		homePage.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer setails......");
		registrationPage.setTxtFirstname(randomString().toUpperCase());
		registrationPage.setTxtLastsname(randomString().toUpperCase());
		registrationPage.setTxtEmail(randomString()+"us77@gmail.com"); // randomly generated the email 
		registrationPage.setTxtTelephone(randomNumber());
		
		String alphaNumeric = randomAlphaNumeric();
		
		registrationPage.setTxtPassword(alphaNumeric);
		registrationPage.setTxtConifrmPassword(alphaNumeric);
		registrationPage.ClickChkdPolicy();
		registrationPage.ClickBtnContinue();
		
		
		logger.info("Validating expected message..... ");
		String confirmation = registrationPage.getMsgConfirmation();
		
		if(confirmation.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			throw new Exception();
		}
		
		//Assert.assertEquals(confirmation, "Your Account Has Been Created!");
		}
		catch (Exception e) {
		   
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("***** Finished  TC001_AccountRegistrationTest*****");
	}
	
}
