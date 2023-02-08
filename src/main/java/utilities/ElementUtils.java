package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		return element;
	}

	public void doSendKeys(By locator, String text) {
		getElement(locator).click();
		getElement(locator).sendKeys(text);
	}

	public void doCLick(By locator) {
		getElement(locator).click();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}

	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String getText(By locator) {
		return getElement(locator).getText();	
	}
	
	// ================================================
	public String getPageTitle() {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));
		//if(wait.until(ExpectedConditions.titleIs(""))) {
		return driver.getTitle();
		//} else {
		//		System.out.println("re");
		//return null;
	}

	public String getPageUrl() {
		
		return driver.getCurrentUrl();
	}
	
	public String getAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}
	
	public String getCSSValue(By locator, String attributeName) {
		return getElement(locator).getCssValue(attributeName);
	}
}

