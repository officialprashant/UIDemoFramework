package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.baseTest.BaseTest;
import customExceptions.UnexpectedValueException;
import listeners.AllureReportListeners;

@Listeners(AllureReportListeners.class)
public class LoginPageTest extends BaseTest {

	@Test(groups = { "regression" })
	public void verifyInvalidLoginErrorMessage() {

		String errorMessage = "Epic sadface: Username and password do not match any user in this service";
		/*
		driver.findElement(By.id("user-name")).sendKeys("randomUsername");
		driver.findElement(By.id("password")).sendKeys("randomPassword");
		driver.findElement(By.id("login-button")).click();
		System.out.println("Thread id is:" + Thread.currentThread().getId());
		 */		
		
		lpage.doLogin("uname","password");
		String eMsg = lpage.getErrorMessage();
				
		Assert.assertEquals(eMsg, errorMessage);
		
		//throw new UnexpectedValueException("Invalid Login Error Message Not Displayed");
	}
	
	@Test(groups = { "regression" })
	public void verifySuccessfulLogin() throws InterruptedException {

		lpage.doLogin("standard_user","secret_sauce");
		
		Thread.sleep(2000);
		//Asserting homepage URL
		Assert.assertEquals(lpage.getHomePageUrl(), "https://www.saucedemo.com/inventory.html");				
	}
	
}
