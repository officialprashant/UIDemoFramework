package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseClass;
import io.qameta.allure.Attachment;


public class AllureReportListeners implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	public void onTestStart(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
	}

	public void onTestFailure(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		Object testClass = iTestResult.getInstance();
		// WebDriver driver = BasePage.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		WebDriver driver;
		if (BaseClass.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveFailureScreenShot(BaseClass.getDriver());
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// TODO Auto-generated method stub
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onStart method " + iTestContext.getName());
	}

	public void onFinish(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		System.out.println("I am in onFinish method " + iTestContext.getName());
	}

}
