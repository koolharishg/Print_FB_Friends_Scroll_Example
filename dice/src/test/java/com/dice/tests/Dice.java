package com.dice.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Dice {

	WebDriver driver = new ChromeDriver();
	Alert al;
	WebDriverWait wait;

	@Test
	public void searchSelenium() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);

		driver.get("https://www.dice.com/");

		try {
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			System.out.println("unexpected alert not present");
		}

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Selenium");
		WebElement job;
		job = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-cy-value='selenium webdriver']")));
		job.click();
		driver.findElement(By.xpath("//input[@id='google-location-search']")).sendKeys("New York");
		WebElement city;
		city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New York, USA']")));
		city.click();
		List<WebElement> links = driver.findElements(By.xpath("//a[@class='card-title-link bold']"));
		for (WebElement e : links) {
			System.out.println(e.getText());
		}
	}

}
