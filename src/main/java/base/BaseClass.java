package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.ElementUtils;

public class BaseClass {

	public static WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	ElementUtils utils;
	
	public WebDriver initialize() {

		driver = new ChromeDriver();
		// driver.get("https://www.saucedemo.com/");
		//tlDriver.set(new ChromeDriver());
		tlDriver.set(driver);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}
}
