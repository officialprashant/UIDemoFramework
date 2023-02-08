package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ElementUtils;

public class LoginPage {

	private WebDriver driver;
	ElementUtils eUtils;	

	public LoginPage(WebDriver driver){
		this.driver = driver;
		eUtils = new ElementUtils(driver);
	}

	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginBut = By.id("login-button");
	private By inValidLoginErrorMessage = By.xpath("//h3[@data-test='error']");

	public void doLogin(String uname, String pwd) {

		eUtils.doClear(username);
		eUtils.doSendKeys(username, uname);
		eUtils.doClear(password);
		eUtils.doSendKeys(password, pwd);
		eUtils.doCLick(loginBut);
	}

	public String getLoginPageTitle() {
		return eUtils.getPageTitle();
	}

	public String getErrorMessage() {

		if(eUtils.isDisplayed(inValidLoginErrorMessage)) {
			return eUtils.getText(inValidLoginErrorMessage);
		} else {
			return null;
		}
	}
	
	public String getHomePageUrl() {
		return eUtils.getPageUrl();
	}
	
	public String getLoginButtonColor() {
		return eUtils.getCSSValue(loginBut, "background-color");
	}
	
	public String getUsernamePlaceholder() {
		return eUtils.getAttribute(username, "placeholder");
	}
	
	public String getPasswordPlaceholder() {
		return eUtils.getAttribute(password, "placeholder");
	}
}