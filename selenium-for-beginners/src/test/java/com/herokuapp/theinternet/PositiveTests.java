package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {

		System.out.println("** Starting loginTest **");

//		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize window size
		driver.manage().window().maximize();

//		open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("** Page is opened! **");

//		enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
//		enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");

//		click login btn
		WebElement logInBtn = driver.findElement(By.tagName("button"));
		logInBtn.click();

//		verifications:
//		new url 
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl , "Actual page url is not the same as expected");
		
//		logout btn is visible
		WebElement logOutBtn = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutBtn.isDisplayed(), "Log out btn is not displayed");

//		succesful login msg
		WebElement successMsg = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMsg = "You logged into a secure area!";
		String actualMsg = successMsg.getText();
		//Assert.assertEquals(actualMsg, expectedMsg , "Actual msg is not the same as expected");
		Assert.assertTrue(actualMsg.contains(expectedMsg), "Actual message does not contain expected message.\nActual Message:"
				+ actualMsg + "\n Expected Message:" + expectedMsg );
//		close browser
		driver.quit();
	}

}
