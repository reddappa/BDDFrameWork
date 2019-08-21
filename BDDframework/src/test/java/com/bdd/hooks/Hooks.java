package com.bdd.hooks;

import java.util.Collection;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.bdd.Reporting.Reporter;
import com.bdd.Reporting.Screenshot;
import com.bdd.framework.utility.BaseUtilities;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	BaseUtilities butil = new BaseUtilities();
	public static String ScenarioName;
	public static String FeaturefileName;
	public static Collection<String> ScenarioTag;
	public static String ScenarioStatus;
	public static Scenario sc;

	@Before
	public void Before(Scenario sc) {

		BaseUtilities.InitilizeDriverSetup();
		
		ScenarioName=sc.getName();
		FeaturefileName=sc.getId();
		ScenarioStatus=sc.getStatus();
		ScenarioTag=sc.getSourceTagNames();
		System.out.println("***************Before Scenario executed*****************");

	}

	@After
	public void tearDown(Scenario scenario) throws Exception {
		
		if(scenario.getStatus().equals("passed"))
		{
			Thread.sleep(3000);
			Screenshot.takeSnapShot(BaseUtilities.getDriver());
			Screenshot.takeSnapShotAndEmbeded(BaseUtilities.getDriver(), scenario);
			
			
		}
		else if(scenario.isFailed())
		{
			//Thread.sleep(10000);
			Screenshot.takeSnapShot(BaseUtilities.getDriver());
			Screenshot.takeSnapShotAndEmbeded(BaseUtilities.getDriver(), scenario);
			
		  		}

		BaseUtilities.tearDown();
		System.out.println("***************After Scenario executed*****************");
	}

	@After
	public void onStart() {
		Reporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		
		System.out.println("***************Report Data completed*****************");
	}
}
