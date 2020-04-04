package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class NegativeTests {

	public void negativeUsernameTest() {
		System.out.println("Starting Negative Username Test");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// maximize window
		driver.manage().window().maximize();

		// get url
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opening");

		// Enter invalid username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("invalid");

		// Enter valid password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

		// Click login btn
		WebElement logInBtn = driver.findElement(By.xpath("//form[@id='login']//i[@class='fa fa-2x fa-sign-in']"));
		logInBtn.click();

		// Verifications
		WebElement errorMsg = driver.findElement(By.id("flash"));
		String expectedErrorMsg = "Your username is invalid!";
		String actualErrorMsg = errorMsg.getText();

		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg),
				"Actual Error Msg does not contain Expected Msg\nActual Error Message:" + actualErrorMsg
						+ "\nExpected Error Message:" + expectedErrorMsg);

		// Closing browser
		driver.quit();
	}

}
