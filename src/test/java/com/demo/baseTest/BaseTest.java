package com.demo.baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import base.BaseClass;
import pages.LoginPage;


public class BaseTest {

	BaseClass bs = new BaseClass();
	protected WebDriver driver;
	protected LoginPage lpage;

	@BeforeTest
	public void setup() {

		driver = bs.initialize();
		lpage = new LoginPage(driver);
		driver.get("https://www.saucedemo.com/");
		System.out.println("BeforeTest");
	}

	@AfterTest
	public void tearDown() {
		System.out.println("AfteTest");
		driver.quit();
	}

}
