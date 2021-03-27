package org.afTestNG.tests;

import org.afTestNG.base.BaseTest;
import org.afTestNG.pages.SauceDemoInventoryPage;
import org.afTestNG.pages.SauceDemoLogin;
import org.afTestNG.util.ExcelRead;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SauceDemoLoginTest extends BaseTest {

	SauceDemoLogin loginPage;

	// Log4j configuration
	private static final Logger log = LogManager.getLogger(SauceDemoLoginTest.class);

	@Test(retryAnalyzer = org.afTestNG.util.RetryAnalyzer.class)
	public void loginTest() {

		log.info("Verifying successful login.");
		loginPage = new SauceDemoLogin(driver);
		//It is a known issue. Added 1 in the username to validate the retryanalyzer functionality
		SauceDemoInventoryPage inventoryPage = loginPage.login("standard_user1", "secret_sauce");
		String expectedProductLabel = "Products";
		String actualProductLabel = inventoryPage.getProductLabel();

		log.info("expectedProductLabel-" + expectedProductLabel + " and actualProductLabel - " + actualProductLabel);
		Assert.assertEquals(expectedProductLabel, actualProductLabel);
	}

	@DataProvider(name = "getDataFromExcel")
	public Object[][] getDataFromExcel() {
		ExcelRead readExcel = new ExcelRead();
		String[][] testdata = readExcel.getDatafromExcel(readProperties.getProperty("excelFileLocation"));
		return testdata;
	}

	@Test(dataProvider = "getDataFromExcel")
	public void loginTestFromExcel(String username, String password) {

		log.info("Verifying successfull login from excel");

		loginPage = new SauceDemoLogin(driver);

		// Should fetch the username and password from external test data files
		SauceDemoInventoryPage inventoryPage = loginPage.login(username, password);
		String expectedProductLabel = "Products";
		String actualProductLabel = inventoryPage.getProductLabel();

		log.info("expectedProductLabel-" + expectedProductLabel + " and actualProductLabel - " + actualProductLabel);
		Assert.assertEquals(expectedProductLabel, actualProductLabel);
	}
}
