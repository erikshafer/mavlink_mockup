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

public class T3_ChangeMajors {
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
	public void test3ChangeMajorsErik() throws Exception {
		// open | /mavlink/logout |
		driver.get("http://localhost:8080/mavlink/logout");
		// open | /mavlink/index |
		driver.get(baseUrl + "/mavlink/index");
		// type | id=username | eshafer
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("eshafer");
		// type | id=password | pass
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pass");
		// click | css=button.btn.btn-cta |
		driver.findElement(By.cssSelector("button.btn.btn-cta")).click();
		// open | /mavlink/profile |
		driver.get(baseUrl + "/mavlink/profile");
		// type | id=major | Computer Science, concentration: Internet
		// Technologies
		driver.findElement(By.id("major")).clear();
		driver.findElement(By.id("major")).sendKeys("Computer Science, concentration: Internet Technologies");
		// clickAt | //button[@type='submit'] |
		// ERROR: Caught exception [ERROR: Unsupported command [clickAt |
		// //button[@type='submit'] | ]]
		// click | link=Home |
		driver.findElement(By.linkText("Home")).click();
		// click | link=Me |
		driver.findElement(By.linkText("Me")).click();
		// click | link=Profile |
		driver.findElement(By.linkText("Profile")).click();
		// assertText | css=body | *Computer Science*
		assertTrue(
		        driver.findElement(By.cssSelector("body")).getText().matches("^[\\s\\S]*Computer Science[\\s\\S]*$"));
		// Change back to Computer Science without concentration
		// type | id=major | Computer Science
		driver.findElement(By.id("major")).clear();
		driver.findElement(By.id("major")).sendKeys("Computer Science");
		// click | //button[@type='submit'] |
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// assertText | css=body | *Computer Science*
		assertTrue(
		        driver.findElement(By.cssSelector("body")).getText().matches("^[\\s\\S]*Computer Science[\\s\\S]*$"));
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
