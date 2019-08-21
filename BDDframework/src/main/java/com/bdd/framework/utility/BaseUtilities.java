package com.bdd.framework.utility;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;

public class BaseUtilities {
	
static ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static String br=readconfig.getBrowser();
	public static WebDriver driver;
	
	public static Logger logger;
	public static String SeleniumGridHub;
	

	
	public static void InitilizeDriverSetup()
	{			
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
			driver.manage().window().maximize();

		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		
	}
	
	public void BaseUrl()
	{
		driver.get(baseURL);
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	 
	  public static void embedScreenshot(Scenario scenario ) {
		  
	    if (scenario.getStatus()=="passed") {
	      try {
	        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png");
	      } catch (WebDriverException wde) {
	        System.err.println(wde.getMessage());
	      } catch (ClassCastException cce) {
	        cce.printStackTrace();
	      }
	    }
	  }
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	public static void tearDown()
	{
		if (driver !=null)
		{
		    driver.close();
		    
		}
		
	}
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public void FeatureData()
	{
		Scenario sc = null;
		System.out.println("Name of the Feature File:"+ sc.getId());
		System.out.println("Name of the Scenario : "+sc.getName());
		System.out.println("Status of the Scenario :"+sc.getStatus());
		System.out.println("Tagname :"+sc.getSourceTagNames());
		
	}

}
