package org.afTestNG.tests;

import org.afTestNG.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ChromeTest extends BaseTest {

	@Test
	public void ChromeTestMethod() {
		// Initialize the chrome driver
		System.out.println("The thread ID for Chrome is " + Thread.currentThread().getId());
		driver.get("https://www.demoqa.com");
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[1]")).click();
	}
}
