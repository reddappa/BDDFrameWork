package com.bdd.Reporting;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;


public class Screenshot {
	
	public static void takeSnapShot(WebDriver webdriver) throws Exception{

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+timeStamp+".png";

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

                File DestFile=new File(screenshotPath); 

                FileUtils.copyFile(SrcFile, DestFile);

    }
	public static void takeSnapShotAndEmbeded(WebDriver webdriver,Scenario scenario) throws Exception{
		byte[] screenshot = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
		System.out.println("Screenshot embeded");
		
    }


}
