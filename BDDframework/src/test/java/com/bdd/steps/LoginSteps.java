package com.bdd.steps;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.bdd.Reporting.Screenshot;
import com.bdd.framework.utility.BaseUtilities;
import com.bdd.framework.utility.ExcelReader;
import com.bdd.framework.utility.ReadConfig;
import com.bdd.pages.LoginPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends BaseUtilities {

	public WebDriver driver;
	BaseUtilities baseUtil = new BaseUtilities();
	ReadConfig config = new ReadConfig();
	LoginPage p = new LoginPage();

	@Given("^Open a browser and Enter URL$")
	public void open_a_browser_and_Enter_URL() throws Throwable {

		baseUtil.BaseUrl();
	}

	@When("^Enter valid username and password$")
	public void Enter_valid_username_and_password() throws Exception {
		String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
		ExcelReader excelreader = new ExcelReader(path);
		String Username = excelreader.getCellData("Sheet1", "UserName", 2);
		String Password = excelreader.getCellData("Sheet1", "Password", 2);
		p.Credentials(Username, Password);
		
	}

	@And("^Click on loginbtn$")
	public void click_on_login() throws Throwable {

		p.ClickLogin();
		
	}

	@Then("^verify Page title$")
	public void verify_Page_Title() throws Throwable {

		p.Title();
	

	}

	@When("^Enter valid username\"([^\"]*)\" and password \"([^\"]*)\"$")
	public void enter_valid_username_and_password(String Username, String Password) throws Throwable {
		p.Credentials(Username, Password);
	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// baseUtil.tearDown();
	}

}
