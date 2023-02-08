package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.baseTest.BaseTest;
import base.BaseClass;
import listeners.AllureReportListeners;

@Listeners(AllureReportListeners.class)
//@Listeners(ExtentReportListeners.class)
public class loginPageUI extends BaseTest {


	@Test(groups = { "regression", "sanity" }, enabled = false)
	public void loginButtonColor() {
		
		System.out.println("Thread id is:"+Thread.currentThread().getId());
		String bColor = lpage.getLoginButtonColor(); //driver.findElement(By.id("login-button")).getCssValue("background-color");
		Assert.assertEquals(bColor, "#e2231a", "color is different");
	}

	@Test(groups = { "regression", "sanity" })
	public void verifyUsernamePlaceholder() {

		System.out.println("Thread id is:"+Thread.currentThread().getId());
		String userNamePlaceholder = lpage.getUsernamePlaceholder();
		Assert.assertEquals(userNamePlaceholder, "Username","Place holder for username is different " + userNamePlaceholder);
	}

	@Test(groups = { "regression", "sanity" })
	public void verifyPasswordPlaceholder() {

		System.out.println("Thread id is:"+Thread.currentThread().getId());
		String passwordPlaceholder = lpage.getPasswordPlaceholder();
		Assert.assertEquals(passwordPlaceholder, "Password","Place holder for username is different " + passwordPlaceholder);
	}
}