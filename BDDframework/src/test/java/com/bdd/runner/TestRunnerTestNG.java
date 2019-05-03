package com.bdd.runner;

import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "D:\\Selenium\\BDDframework\\src\\test\\java\\com\\bdd\\featurefiles", glue = {
		"com.bdd.steps",
		"com.bdd.hooks" }, 
monochrome = true, tags = {
				"@Verify_Login_Functionality" })

public class TestRunnerTestNG {
	
    private TestNGCucumberRunner testNGCucumberRunner;
    
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {    	
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

}
