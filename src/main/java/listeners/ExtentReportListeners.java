package listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


import base.BaseClass;

public class ExtentReportListeners implements ITestListener {

	private static final String folder_name = "./Reports/";
	private static final String file_name = "TestExtentReport.html";

	ExtentReports extent = init();
	ExtentSparkReporter spark = new ExtentSparkReporter(folder_name + file_name);
	static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	private static ExtentReports init() {

		Path path = Paths.get(folder_name);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}

		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(folder_name + file_name);
		reporter.config().setReportName("Sauce Labs Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "Windows");
		extentReports.setSystemInfo("Author", "Prashant Gupta");
		extentReports.setSystemInfo("Build#", "1.0");
		extentReports.setSystemInfo("Customer Name", "Sauce Labs");

		// extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

		return extentReports;
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		//test.get().pass(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		String methodName = result.getMethod().getMethodName();

		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(BaseClass.getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		String methodName = result.getMethod().getMethodName();
		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(BaseClass.getScreenshot()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

}
