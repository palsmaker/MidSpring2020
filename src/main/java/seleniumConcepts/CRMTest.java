package seleniumConcepts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//Modular framework we are using with same methods, not used anymore

public class CRMTest {
	
	WebDriver driver;
	String browser = null;
	
	public void readConfig() {
		//4 methods to read any file
		//Input Stream
		//BufferedReader
		//FileReader
		//Scanner
		
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("./src/main/java/config/config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			// Setting up the property
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			// Creating the Web driver instance
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FireFoxDriver();
		}
		
		
		// Setting up the property
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		// Creating the Web driver instance
		driver = new ChromeDriver();
		
		//Creating web driver with Firefox
		//System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		//driver = new FireFoxDriver();
		

		// Maximizing Browser
		driver.manage().window().maximize();
		
		//Clean up the cache and cookies from your browser
		driver.manage().deleteAllCookies();

		// Get to the site
		driver.get("http:\\www.techfios.com/ibilling/?ng=admin/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	@Test
	public void loginTest() throws InterruptedException {
		
		//Element Library with WebElement and BY
		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.name("Login");
		
		//Data
		String loginID = "demo@techfios.com";
		String password = "abc123";
		
		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginID);
		driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityofElementLocated(DASHBOARD_BUTTON_LOCATOR));
		
		waitForElement(driver, 3, DASHBOARD_BUTTON_LOCATOR);
		
		String dashboardValidationTest = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
		Assert.assertEquals("Dashboard", dashboardValidationTest, "Wrong Page!!!");

	}
	
		@AfterMethod
		public void tearDown() {
			driver.close();
			driver.quit();
		}
		
		public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		//Test Data
		String fullName = "Test Spring";
		String companyName = "Techfios";
		String emailAddress ="spring@gmail.com";
		String phoneNumber = "2678909875";
		
		//Login In
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(loginId);
		driver.findElement(SIGNIN_FIELD).sendKeys(loginId);
		
		//Validate Dashboard Page
		waitForElement(driver, 3, DASHBOARD_BUTTON);
		String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON).getText();
		Assert.assertEquals("Dashboard", dashboardValidationText, "Wrong Page!!!");
		
		driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		waitForElement(driver, 3, ADD_CONTACT_LOCATOR);
		
		//Generate Random Number
		Random rnd = new Random();
		
		//Fill out add customers form
		driver.findElement(FULL_NAME_FIELD).sendkeys(fullName);
		
		
		
		
		
		
				;
		
		//XPath Axes relationship for compiling and getting to delete
		////a[contains(text(),'231')]/parent::td/following-sibling::td[6]/child::a[2]
		////a[contains(text(),'231')]/ancestor::tr/child::td[7]/child::a[2]
		////a[contains(text(),'231')]/ancestor::tbody/descendant::td[7]/child::a[2]
}


