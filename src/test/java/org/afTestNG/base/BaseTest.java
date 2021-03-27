package org.afTestNG.base;

import java.io.File;
import java.io.IOException;
import org.afTestNG.util.ReadPropertiesFile;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	protected static WebDriver driver;

	private static String url;

	private static int screenshotcounter = 001;

	private static final String BROWSER = System.getProperty("browser", "chrome");

	protected ReadPropertiesFile readProperties = ReadPropertiesFile.getInstance();

	@BeforeSuite
	public void suiteSetup() {
		System.out.println(BROWSER);
		if (BROWSER.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		logger.debug("Maximize the driver");
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void openURL() {
		url = readProperties.getProperty("url");
		logger.info("URL is " + url);
		driver.get(url);
	}

	@AfterMethod
	public void takeScreenshotAtFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			ReadPropertiesFile propertiesFileReader = ReadPropertiesFile.getInstance();
			FileUtils.copyFile(srcFile, new File(propertiesFileReader.getProperty("screenshotsLocation")
					+ testResult.getName() + "-" + screenshotcounter++ + ".jpg"));
		}
		driver.manage().deleteAllCookies();
	}

	@AfterSuite
	public void suiteClose() {
		logger.info("Browser closed");
		driver.quit();
	}
}
