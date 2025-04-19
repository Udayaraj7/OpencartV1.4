package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static  WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@Parameters({"os","browser"})
	@BeforeClass(groups={"Sanity","Master","Regression"})
	public void  setup(String os,String browser) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		
		p=new Properties();
		 FileReader f = new FileReader("./src/test/resources/config.properties");
		 p.load(f);
		 
		 if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		 {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				
				capabilities.setPlatform(Platform.MAC);
			}
		
			else if(os.equalsIgnoreCase("linux"))
				{
					
					capabilities.setPlatform(Platform.LINUX);
				}
			else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			if(browser.equalsIgnoreCase("chrome"))
			{
				
				capabilities.setBrowserName("chrome");
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				
				capabilities.setBrowserName("MicrosoftEdge");
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				
				capabilities.setBrowserName("firefox");
			}
			else {
				System.out.println("no matching browser");
				return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		 }
		 
		 if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		 {
		 
			if(browser.equals("chrome"))
			{
				
				driver=new ChromeDriver();
			}
			else if(browser.equals("edge")){
				driver=new EdgeDriver();
			}
			else if(browser.equals("firefox")){
				driver=new FirefoxDriver();
			}
			else {
				System.out.println("Invalid browser name");
				return;
			}
		
		 }
		 
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//System.out.println(p.getProperty("url"));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Master","Regression"})
	public void teardown()
	{
		driver.quit();
	}
	
	

	
		public String randomString() {
		
		String rstring = RandomStringUtils.randomAlphabetic(5);
		
		return rstring;
	}
	
		public String randomNumber() {
		
		String rstring = RandomStringUtils.randomNumeric(10);
		
		return rstring;
	}
    
    	public String randomAlphaNumeric() {
		
		String rstring = RandomStringUtils.randomAlphanumeric(5);
		
		return rstring;
	}

    	/*public String captureScreen(String tname) throws IOException {

    		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

    		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname+"_"+timeStamp+".png";
    		File targetFile=new File(targetFilePath);

    		sourceFile.renameTo(targetFile);

    		return targetFilePath;

    	}*/
    	
    	public static String captureScreen( String tname) throws IOException {
            // Generate timestamp in 12-hour format with AM/PM
            String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmssa").format(new Date());

            // Take screenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            // to take screen shot of web element 
//              LoginPage loginPage = new LoginPage(driver);
//              File sourceFile = loginPage.txtEmailAddress.getScreenshotAs(OutputType.FILE);

            // Define the target file path
            String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
            File targetFile = new File(targetFilePath);

           
                // Use FileUtils to copy the file reliably
                FileUtils.copyFile(sourceFile, targetFile);
              //  System.out.println("Screenshot saved: " + targetFilePath);
        

            return targetFilePath;
        }
}
