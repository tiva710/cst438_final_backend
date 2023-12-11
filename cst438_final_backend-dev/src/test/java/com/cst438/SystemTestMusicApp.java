package com.cst438;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SystemTestMusicApp {

	public static final String CHROME_DRIVER_FILE_LOCATION = "/Users/tiki/Documents/Software engineering/chromedriver-mac-x64/chromedriver";
	public static final String URL = "http://localhost:3000/MusicApp";
	public static final String ALIAS_NAME = "test";
	public static final int SLEEP_DURATION = 1000; // 1 second.

	ChromeDriver driver;

	@BeforeEach
	public void testSetup() throws Exception {
		// if you are not using Chrome,
		// the following lines will be different.
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(ops);

		driver.get(URL);
		// must have a short wait to allow time for the page to download
		Thread.sleep(SLEEP_DURATION);
	}

	@Test
	public void goodSearch() throws InterruptedException {
		// sign in
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.id("submit"));
		username.sendKeys("user");
		password.sendKeys("user");
		login.click();
		Thread.sleep(SLEEP_DURATION);
		
		// Authorize
		WebElement authorize = driver.findElement(By.id("authorize"));
		authorize.click();
		
		// enter a search
		WebElement search = driver.findElement(By.id("searchBar"));
		search.sendKeys("blah");
		Thread.sleep(10000);

		// submit 
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();
		submit.click();
		Thread.sleep(10000);

		// verify the results
		List<WebElement> results = driver.findElements(By.xpath("//td"));
		assertThat(results.size()).isGreaterThanOrEqualTo(5);

	}

	@AfterEach
	public void cleanup() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}
}
