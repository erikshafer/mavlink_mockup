package edu.unomaha.mavlink.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class T6_UserAlreadyExists {
	private WebDriver driver;
	private String baseUrl = "http://localhost:8080";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private final static String driverLocation = "lib/chromedriver.exe";

	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.chrome.driver", driverLocation);
	}

	@Before
	public void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-web-security");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("binary", driverLocation);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void test6UserAlreadyExists() throws Exception {
		// open | /mavlink/logout |
		driver.get("http://localhost:8080/mavlink/logout");
		// click | link=Me |
		driver.findElement(By.linkText("Me")).click();
		// click | link=Login |
		driver.findElement(By.linkText("Login")).click();
		// click | link=Sign Up for MavLink |
		driver.findElement(By.linkText("Sign Up for MavLink")).click();
		// type | id=firstName | Erik
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Erik");
		// type | id=lastName | Shafer
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Shafer");
		// type | id=major | Computer Science
		driver.findElement(By.id("major")).clear();
		driver.findElement(By.id("major")).sendKeys("Computer Science");
		// type | id=email | eshafer@unomaha.edu
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("eshafer@unomaha.edu");
		// type | id=username | eshafer
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("eshafer");
		// type | id=password | pass
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pass");
		// click | //button[@type='submit'] |
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// assertText | css=body | *Username already exists*
		assertTrue(driver.findElement(By.cssSelector("body")).getText()
		        .matches("^[\\s\\S]*Username already exists[\\s\\S]*$"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
