package org.afTestNG.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoLogin {

	WebDriver driver;

	private static final Logger logger = LogManager.getLogger(SauceDemoLogin.class);

	@FindBy(xpath = "//input[@id='user-name']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//input[@id='login-button']")
	private WebElement loginButton;

	public SauceDemoLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUsername(String uname) {
		username.sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public SauceDemoInventoryPage login(String username, String password) {
		logger.info("Logging with username - " + username + "  and password - " + password);
		setUsername(username);
		setPassword(password);
		clickLoginButton();
		return new SauceDemoInventoryPage(driver);
	}

}
