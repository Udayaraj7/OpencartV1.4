package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	
	@FindBy(xpath="//span[text()='My Account']")
	 WebElement  lnkmyAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement  lnkregister;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement  lnklogin;
	
	
	public void  clickMyAccount()
	{
		lnkmyAccount.click();
	}
	
	public void  clickRegister()
	{
		lnkregister.click();
	}
	
	public void clickLogin()
	{
		lnklogin.click();	
		}

}
