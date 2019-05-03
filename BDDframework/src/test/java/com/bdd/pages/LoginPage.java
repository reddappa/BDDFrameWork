package com.bdd.pages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.bdd.framework.utility.BaseUtilities;
import com.bdd.framework.utility.ExcelReader;

public class LoginPage extends BaseUtilities {

	BaseUtilities butils = new BaseUtilities();

	public static String path = System.getProperty("user.dir") + "/ObjectRepository/Elements.xlsx";
	public static ExcelReader excelreader = new ExcelReader(path);
	public static String uid = excelreader.getCellData("Sheet1", "ElementName", 2);
	public static String Pswd = excelreader.getCellData("Sheet1", "ElementName", 3);
	public static String btnLogin = excelreader.getCellData("Sheet1", "ElementName", 4);

	public void Credentials(String uname, String upassword) {
		driver.findElement(By.name(uid)).sendKeys(uname);
		driver.findElement(By.name(Pswd)).sendKeys(upassword);

	}

	public void ClickLogin() {

		WebElement loginbtn = driver.findElement(By.name(btnLogin));
		loginbtn.click();
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}

	public void Title() throws IOException, InterruptedException {
		String tiltle = "Guru99 Bank Manager HomePage";
		try {
			Assert.assertEquals(tiltle, driver.getTitle());
		} catch (Exception e) {
		}
	}

}
