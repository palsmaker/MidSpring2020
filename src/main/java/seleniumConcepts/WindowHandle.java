package seleniumConcepts;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle {
	
	WebDriver driver;

	@Before

	public void launchBrowser() {
		// Setting up the property
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		// Creating the Web driver instance
		driver = new ChromeDriver();

		// Maximizing Browser
		driver.manage().window().maximize();
		
		//Clean up the cache and cookies from your browser
		driver.manage().deleteAllCookies();

		// Get to the site
		driver.get("https://www.yahoo.com/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void loginTest() throws InterruptedException {

		//System.out.println(driver.getTitle()); // Yahoo
		System.out.println(driver.getWindowHandle()); //CDwindow-CBB3DEF60B2C10466A06ED5279A0ACB3
		
		
		// Identifying the password field pass password
		driver.findElement(By.xpath("//input[@id='header-search-input']")).sendKeys("xpath");
		driver.findElement(By.xpath("//button[@id='header-desktop-search-button']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("XPath Tutorial - W3Schools")).click();
		
		//List of window handles in array so we use for each loop to move the driver to each window i)
		//CDwindow-CBB3DEF60B2C10466A06ED5279A0ACB3 - Yahoo web page
		//CDwindow-EE45DEA325879709C5E118F8F22B5942 - w3 schools Xpath tutorial web page
		// Switch your window from one window to another
		for(String i : driver.getWindowHandles()) {
			System.out.println(i); // used for testing purpose only we print to see where our handle is
			driver.switchTo().window(i);
		}
		
		//System.out.println(driver.getTitle()); // XPath Tutorial
		driver.findElement(By.linkText("CSS")).click();


	}

		

}

