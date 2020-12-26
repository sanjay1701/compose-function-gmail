package com.composefunction.stepdef;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	static WebDriver driver;
	static JavascriptExecutor js;
	static String url = "https://gmail.com/";
	static WebDriverWait wait;

	public static void setUp(){
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		options.addArguments("--start-maximized");
		options.addArguments("--no-proxy-server");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.setProperty("webdriver.chrome.driver", "E:\\software\\chromedriver_win\\chromedriver.exe");
		driver = new ChromeDriver(capabilities);
		js = ((JavascriptExecutor) driver);

		driver.manage().window().maximize();	// maximize window
		driver.manage().deleteAllCookies();		// delete all cookies

		driver.get(url);

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testmail232020@gmail.com");
		WebElement next = driver.findElement(By.xpath("//div[@class='VfPpkd-Jh9lGc']"));
		js.executeScript("arguments[0].click()", next);
		wait = new WebDriverWait(driver, 35);
		WebElement passwordElement = wait.until(
				ExpectedConditions.elementToBeClickable(By.name("password")));
		passwordElement.click();
		passwordElement.clear();
		passwordElement.sendKeys("Testing@123");
		WebElement nextBtn = driver.findElement(By.xpath("//div[@class='VfPpkd-RLmnJb']"));
		js.executeScript("arguments[0].click()", nextBtn);

	}

}
