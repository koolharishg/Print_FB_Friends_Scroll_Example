package com.dice.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PrintFBFriends {

	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void FBFriends() {
		ChromeOptions cr = new ChromeOptions();
		cr.addArguments("--disable-notifications");
		driver = new ChromeDriver(cr);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		// wait = new WebDriverWait(driver, 120);

		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("koolharishg@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("koolharishg@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("test123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//div[text()='Harish Ghorpade']")).click();

		// get friends count
		String frinedsCount = driver.findElement(By.xpath("//*[@data-tab-key='friends']")).getText().substring(7);
		int count = Integer.parseInt(frinedsCount);

		// click on friends tab
		driver.findElement(By.xpath("//a[@data-tab-key='friends']")).click();

		List<WebElement> frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
		int found = frineds.size();
		System.out.println(found);

		while (found <= count) {

			// scroll to the last friend found from the current loaded friend list
			Coordinates coordinate = ((Locatable) frineds.get(found - 1)).getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();
			frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
			found = frineds.size();

			// break and print frined list if the condition found frineds = count of frined
			// list
			if (found == count) {
				System.out.println(found);
				System.out.println("---Printing FriendList---");
				for (WebElement e : frineds) {
					System.out.println(e.getText());
				}
				break;
			}

		}
	}
}